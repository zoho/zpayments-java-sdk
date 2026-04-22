package com.zohopayments;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.zohopayments.auth.OAuthToken;
import com.zohopayments.exception.ConnectionException;
import com.zohopayments.exception.ZohoPaymentsException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

// Exchanges a Zoho OAuth refresh token for a new access token.
final class OAuthTokenRefresher
{
	private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(30);
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(60);
	private static final int MAX_ERROR_BODY_SNIPPET = 500;

	private OAuthTokenRefresher() {}

	static OAuthToken generateAccessToken(String refreshToken, String clientId,
			String clientSecret, String redirectUri, Edition edition)
	{
		if (refreshToken == null || refreshToken.isEmpty())
		{
			throw new IllegalArgumentException("refreshToken is required"); //No I18N
		}
		if (clientId == null || clientId.isEmpty())
		{
			throw new IllegalArgumentException("clientId is required"); //No I18N
		}
		if (clientSecret == null || clientSecret.isEmpty())
		{
			throw new IllegalArgumentException("clientSecret is required"); //No I18N
		}
		if (redirectUri == null || redirectUri.isEmpty())
		{
			throw new IllegalArgumentException("redirectUri is required"); //No I18N
		}
		if (edition == null)
		{
			throw new IllegalArgumentException("edition is required"); //No I18N
		}

		HttpClient httpClient = HttpClient.newBuilder()
				.connectTimeout(CONNECT_TIMEOUT)
				.build();
		try
		{
			String tokenUrl = edition.getAccountsUrl() + "/oauth/v2/token"; //No I18N
			String body = "refresh_token=" + encode(refreshToken) //No I18N
					+ "&client_id=" + encode(clientId) //No I18N
					+ "&client_secret=" + encode(clientSecret) //No I18N
					+ "&redirect_uri=" + encode(redirectUri) //No I18N
					+ "&grant_type=refresh_token"; //No I18N

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(tokenUrl))
					.timeout(REQUEST_TIMEOUT)
					.header("Content-Type", "application/x-www-form-urlencoded") //No I18N
					.POST(HttpRequest.BodyPublishers.ofString(body))
					.build();

			HttpResponse<String> response = httpClient
					.send(request, HttpResponse.BodyHandlers.ofString());

			int statusCode = response.statusCode();
			String rawBody = response.body();
			if (rawBody == null || rawBody.isBlank())
			{
				throw new ZohoPaymentsException(
						"Token refresh failed: empty response body [HTTP " + statusCode + "]"); //No I18N
			}

			final JsonObject json;
			try
			{
				json = JsonUtil.parseObject(rawBody);
			}
			catch (JsonSyntaxException e)
			{
				throw new ZohoPaymentsException(
						"Token refresh failed: response is not valid JSON [HTTP " + statusCode + "]: " //No I18N
								+ truncateForMessage(rawBody),
						e);
			}

			if (json == null)
			{
				throw new ZohoPaymentsException(
						"Token refresh failed: expected a JSON object [HTTP " + statusCode + "]: " //No I18N
								+ truncateForMessage(rawBody));
			}

			// IAM returns HTTP 200 with an "error" field on failure.
			if (json.has("error") && !json.get("error").isJsonNull()) //No I18N
			{
				throw new ZohoPaymentsException(
						"Token refresh failed: " + json.get("error").getAsString()); //No I18N
			}

			if (statusCode < 200 || statusCode >= 300)
			{
				throw new ZohoPaymentsException(
						"Token refresh failed [HTTP " + statusCode + "]: " //No I18N
								+ truncateForMessage(rawBody));
			}

			if (!json.has("access_token") || json.get("access_token").isJsonNull()) //No I18N
			{
				throw new ZohoPaymentsException(
						"Token refresh failed: access_token not found in response"); //No I18N
			}

			String newAccessToken = json.get("access_token").getAsString(); //No I18N
			// Prefer "expires_in_sec" when present; fall back to "expires_in" otherwise.
			// Both keys are treated as seconds.
			long expiresIn;
			if (json.has("expires_in_sec") && !json.get("expires_in_sec").isJsonNull()) //No I18N
			{
				expiresIn = json.get("expires_in_sec").getAsLong(); //No I18N
			}
			else if (json.has("expires_in") && !json.get("expires_in").isJsonNull()) //No I18N
			{
				expiresIn = json.get("expires_in").getAsLong(); //No I18N
			}
			else
			{
				expiresIn = 3600L;
			}

			return new OAuthToken(newAccessToken, expiresIn);
		}
		catch (InterruptedException e)
		{
			Thread.currentThread().interrupt();
			throw new ZohoPaymentsException("Token refresh request interrupted", e); //No I18N
		}
		catch (IOException e)
		{
			throw new ConnectionException("Token refresh request failed", e); //No I18N
		}
		finally
		{
			closeQuietly(httpClient);
		}
	}

	// Closes the JDK {@link HttpClient} when {@link AutoCloseable} is supported (JDK 21+).
	private static void closeQuietly(HttpClient client)
	{
		if (client instanceof AutoCloseable)
		{
			try
			{
				((AutoCloseable) client).close();
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

	private static String encode(String value)
	{
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
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
