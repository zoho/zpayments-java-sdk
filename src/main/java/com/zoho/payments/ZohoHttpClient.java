package com.zoho.payments;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.zoho.payments.exception.AuthenticationException;
import com.zoho.payments.exception.InvalidRequestException;
import com.zoho.payments.exception.PermissionException;
import com.zoho.payments.exception.RateLimitException;
import com.zoho.payments.exception.ResourceNotFoundException;
import com.zoho.payments.exception.ZohoPaymentsAPIException;
import com.zoho.payments.exception.ZohoPaymentsException;
import com.zoho.payments.model.ListResponse;
import com.zoho.payments.net.HttpClientInterface;
import com.zoho.payments.net.RequestMethod;
import com.zoho.payments.net.ZohoRequest;
import com.zoho.payments.net.ZohoResponse;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

// Internal HTTP client between service classes and {@link HttpClientInterface}.
final class ZohoHttpClient
{
	private static final int MAX_ERROR_BODY_SNIPPET = 500;
	private static final String USER_AGENT = ZohoPayments.SDK_NAME + "/" + ZohoPayments.SDK_VERSION; //No I18N

	private final HttpClientInterface transport;
	private final TokenManager tokenManager;
	private final Edition edition;
	private final String accountId;
	private final Duration requestTimeout;
	private final Map<String, String> defaultHeaders;
	private final AtomicBoolean closed = new AtomicBoolean(false);

	ZohoHttpClient(HttpClientInterface transport, TokenManager tokenManager,
			Edition edition, String accountId, Duration requestTimeout,
			Map<String, String> defaultHeaders)
	{
		this.transport = transport;
		this.tokenManager = tokenManager;
		this.edition = edition;
		this.accountId = accountId;
		this.requestTimeout = requestTimeout;
		this.defaultHeaders = defaultHeaders != null
				? Collections.unmodifiableMap(new LinkedHashMap<>(defaultHeaders))
				: Collections.emptyMap();
	}

	ApiResponse request(RequestMethod method, String path, QueryParams queryParams, JsonObject body)
	{
		if (closed.get())
		{
			throw new IllegalStateException("ZohoPaymentsClient is closed — no further API calls may be made"); //No I18N
		}
		QueryParams merged = new QueryParams();
		if (queryParams != null)
		{
			merged.addAll(queryParams);
		}
		merged.add("account_id", accountId); //No I18N

		String url = edition.getBaseUrl() + path;
		String qs = merged.toQueryString();
		if (!qs.isEmpty())
		{
			url += "?" + qs; //No I18N
		}

		ZohoRequest.Builder reqBuilder = ZohoRequest.builder()
				.method(method)
				.url(url)
				.timeout(requestTimeout);

		for (Map.Entry<String, String> h : defaultHeaders.entrySet())
		{
			reqBuilder.header(h.getKey(), h.getValue());
		}

		// SDK-managed headers always override any user-supplied default of the same name.
		reqBuilder.setHeader("User-Agent", USER_AGENT); //No I18N
		reqBuilder.setHeader("Authorization", "Zoho-oauthtoken " + tokenManager.getAccessToken()); //No I18N
		reqBuilder.setHeader("Accept", "application/json"); //No I18N

		if (body != null)
		{
			reqBuilder.setHeader("Content-Type", "application/json"); //No I18N
			reqBuilder.body(body.toString());
		}

		ZohoRequest request = reqBuilder.build();

		ZohoResponse rawResponse = transport.execute(request);

		return parseAndValidate(rawResponse, method, path);
	}

	private ApiResponse parseAndValidate(ZohoResponse rawResponse, RequestMethod method, String path)
	{
		String rawBody = rawResponse.getBody();

		JsonObject responseBody;
		if (rawBody == null || rawBody.isBlank())
		{
			responseBody = new JsonObject();
		}
		else
		{
			try
			{
				responseBody = JsonUtil.parseObject(rawBody);
				if (responseBody == null)
				{
					responseBody = new JsonObject();
				}
			}
			catch (JsonSyntaxException e)
			{
				throw new ZohoPaymentsException(
						"Unexpected non-JSON response body [HTTP " + rawResponse.getStatusCode() //No I18N
								+ " " + method + "]. Raw body: " + truncateForMessage(rawBody), e);
			}
		}

		ApiResponse apiResponse = new ApiResponse(rawResponse.getStatusCode(), responseBody);

		if (!apiResponse.isSuccess())
		{
			throwTypedException(rawResponse.getStatusCode(), apiResponse.getCodeString(), apiResponse.getMessage());
		}
		return apiResponse;
	}

	private void throwTypedException(int statusCode, String codeString, String message)
	{
		switch (statusCode)
		{
			case 400:
			case 422:
				throw new InvalidRequestException(statusCode, codeString, message);
			case 401:
				throw new AuthenticationException(codeString, message);
			case 403:
				throw new PermissionException(codeString, message);
			case 404:
				throw new ResourceNotFoundException(codeString, message);
			case 429:
				throw new RateLimitException(codeString, message);
			default:
				throw new ZohoPaymentsAPIException(statusCode, codeString, message);
		}
	}

	void close()
	{
		if (closed.compareAndSet(false, true))
		{
			transport.close();
		}
	}

	ApiResponse get(String path)
    {
        return request(RequestMethod.GET, path, null, null);
    }

	ApiResponse get(String path, QueryParams params)
    {
        return request(RequestMethod.GET, path, params, null);
    }

	ApiResponse post(String path, JsonObject body)
    {
        return request(RequestMethod.POST, path, null, body);
    }

	ApiResponse put(String path, JsonObject body)
    {
        return request(RequestMethod.PUT, path, null, body);
    }

	ApiResponse put(String path)
    {
        return request(RequestMethod.PUT, path, null, null);
    }

	ApiResponse delete(String path)
    {
        return request(RequestMethod.DELETE, path, null, null);
    }

	<T> T get(String path, Class<T> type, String... envelopeKeys)
	{
		return JsonUtil.unwrap(get(path), type, envelopeKeys);
	}

	<T> T post(String path, JsonObject body, Class<T> type, String... envelopeKeys)
	{
		return JsonUtil.unwrap(post(path, body), type, envelopeKeys);
	}

	<T> T put(String path, JsonObject body, Class<T> type, String... envelopeKeys)
	{
		return JsonUtil.unwrap(put(path, body), type, envelopeKeys);
	}

	<T> T put(String path, Class<T> type, String... envelopeKeys)
	{
		return JsonUtil.unwrap(put(path), type, envelopeKeys);
	}

	// Issues a list-style GET and returns a {@link ListResponse} with items and page context.
	<T> ListResponse<T> list(String path, QueryParams queryParams, Type listType, String... envelopeKeys)
	{
		JsonObject body = get(path, queryParams).getBody();
		List<T> items = JsonUtil.listFromJson(body, listType, envelopeKeys);
		return new ListResponse<>(items, JsonUtil.readPageContext(body));
	}

	// Percent-encodes a URL path segment using UTF-8.
	static String encodePath(String segment)
	{
		return URLEncoder.encode(segment, StandardCharsets.UTF_8).replace("+", "%20"); //No I18N
	}

	private static String truncateForMessage(String body)
	{
		if (body == null)
		{
			return ""; //No I18N
		}
		String t = body.strip();
		if (t.length() <= MAX_ERROR_BODY_SNIPPET)
		{
			return t;
		}
		return t.substring(0, MAX_ERROR_BODY_SNIPPET) + "..."; //No I18N
	}
}
