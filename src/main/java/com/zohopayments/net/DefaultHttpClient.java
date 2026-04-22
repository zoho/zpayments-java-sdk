package com.zohopayments.net;

import com.zohopayments.exception.ConnectionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Default {@link HttpClientInterface} implementation backed by {@code java.net.http.HttpClient}.
public final class DefaultHttpClient implements HttpClientInterface
{
	private final HttpClient httpClient;
	private final Duration defaultTimeout;

	public DefaultHttpClient(Duration connectTimeout, Duration defaultTimeout)
	{
		this.defaultTimeout = defaultTimeout;
		this.httpClient = HttpClient.newBuilder()
				.connectTimeout(connectTimeout)
				.build();
	}

	@Override
	public ZohoResponse execute(ZohoRequest request)
	{
		Duration effectiveTimeout = request.getTimeout() != null ? request.getTimeout() : defaultTimeout;
		HttpRequest.Builder builder = HttpRequest.newBuilder()
				.uri(URI.create(request.getUrl()))
				.timeout(effectiveTimeout);

		for (Map.Entry<String, List<String>> h : request.getHeaders().entrySet())
		{
			for (String value : h.getValue())
			{
				builder.header(h.getKey(), value);
			}
		}

		switch (request.getMethod())
		{
			case GET:
				builder.GET();
				break;
			case POST:
				builder.POST(bodyPublisher(request.getBody()));
				break;
			case PUT:
				builder.PUT(bodyPublisher(request.getBody()));
				break;
			case DELETE:
				builder.DELETE();
				break;
			default:
				throw new UnsupportedOperationException("Unsupported HTTP method: " + request.getMethod()); //No I18N
		}

		try
		{
			HttpResponse<String> response = httpClient.send(builder.build(), HttpResponse.BodyHandlers.ofString());

			Map<String, List<String>> responseHeaders = new LinkedHashMap<>();
			response.headers().map().forEach((k, v) ->
			{
				if (v != null && !v.isEmpty())
				{
					responseHeaders.put(k, v);
				}
			});

			return new ZohoResponse(response.statusCode(), responseHeaders, response.body());
		}
		catch (IOException e)
		{
			throw new ConnectionException("HTTP request failed: " + request.getMethod(), e); //No I18N
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			throw new ConnectionException("HTTP request interrupted: " + request.getMethod(), e); //No I18N
		}
	}

	// Releases the underlying {@link HttpClient} when it implements {@link AutoCloseable} (JDK 21+).
	@Override
	public void close()
	{
		if (httpClient instanceof AutoCloseable)
		{
			try
			{
				((AutoCloseable) httpClient).close();
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			catch (Exception ignored)
			{
			}
		}
	}

	private HttpRequest.BodyPublisher bodyPublisher(String body)
	{
		if (body == null || body.isEmpty())
		{
			return HttpRequest.BodyPublishers.noBody();
		}
		return HttpRequest.BodyPublishers.ofString(body);
	}
}
