package com.zohopayments;

import com.zohopayments.auth.OAuthToken;
import com.zohopayments.net.DefaultHttpClient;
import com.zohopayments.net.HttpClientInterface;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class ZohoPayments
{
	static final String SDK_NAME = "zoho-payments-java-sdk"; //No I18N
	static final String SDK_VERSION = "1.0.0"; //No I18N

	private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(30);
	private static final Duration DEFAULT_REQUEST_TIMEOUT = Duration.ofSeconds(60);

	private ZohoPayments()
	{
	}

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder
	{
		// Header names reserved by the SDK; rejected by {@link #addDefaultHeader(String, String)}.
		private static final Set<String> RESERVED_HEADERS = Set.of(
				"authorization",   //No I18N
				"user-agent",      //No I18N
				"accept",          //No I18N
				"content-type",    //No I18N
				"content-length",  //No I18N
				"host");           //No I18N

		private String accountId;
		private Edition edition;
		private String accessToken;
		private HttpClientInterface httpClient;
		private Duration connectTimeout;
		private Duration requestTimeout;
		private final Map<String, String> defaultHeaders = new LinkedHashMap<>();
		private boolean consumed;

		private Builder()
		{
		}

		public Builder accountId(String accountId)
		{
			checkNotConsumed();
			this.accountId = accountId;
			return this;
		}

		public Builder edition(Edition edition)
		{
			checkNotConsumed();
			this.edition = edition;
			return this;
		}

		// Sets the OAuth access token as a plain string.
		public Builder oauthToken(String accessToken)
		{
			checkNotConsumed();
			if (accessToken == null || accessToken.isBlank())
			{
				throw new IllegalArgumentException("oauthToken is required"); //No I18N
			}
			this.accessToken = accessToken;
			return this;
		}

		// Sets the OAuth access token from an {@link OAuthToken}.
		public Builder oauthToken(OAuthToken token)
		{
			checkNotConsumed();
			if (token == null || token.getAccessToken() == null || token.getAccessToken().isBlank())
			{
				throw new IllegalArgumentException("oauthToken is required"); //No I18N
			}
			this.accessToken = token.getAccessToken();
			return this;
		}

		/**
		 * Supplies a custom HTTP transport. Cannot be combined with
		 * {@link #connectTimeout(Duration)} — a custom transport controls its own timeout.
		 */
		public Builder httpClient(HttpClientInterface httpClient)
		{
			checkNotConsumed();
			this.httpClient = httpClient;
			return this;
		}

		// TCP connect timeout for the default transport. Default: 30 seconds.
		public Builder connectTimeout(Duration connectTimeout)
		{
			checkNotConsumed();
			this.connectTimeout = connectTimeout;
			return this;
		}

		// Per-request read timeout. Default: 60 seconds.
		public Builder requestTimeout(Duration requestTimeout)
		{
			checkNotConsumed();
			this.requestTimeout = requestTimeout;
			return this;
		}

		/**
		 * Adds a default header sent with every request. SDK-managed headers
		 * ({@code Authorization}, {@code User-Agent}, {@code Accept}, {@code Content-Type},
		 * {@code Content-Length}, {@code Host}) are rejected.
		 */
		public Builder addDefaultHeader(String name, String value)
		{
			checkNotConsumed();
			if (name == null || name.isBlank())
			{
				throw new IllegalArgumentException("header name is required"); //No I18N
			}
			if (value == null)
			{
				throw new IllegalArgumentException(
						"header value is required (name=" + name + ")"); //No I18N
			}
			if (RESERVED_HEADERS.contains(name.toLowerCase(Locale.ROOT)))
			{
				throw new IllegalArgumentException(
						"Header '" + name + "' is managed by the SDK and cannot be set via addDefaultHeader"); //No I18N
			}
			this.defaultHeaders.put(name, value);
			return this;
		}

		/**
		 * Builds a {@link ZohoPaymentsClient}. The builder is single-use; further calls
		 * throw {@link IllegalStateException}.
		 */
		public ZohoPaymentsClient build()
		{
			checkNotConsumed();
			if (accountId == null || accountId.isEmpty())
			{
				throw new IllegalArgumentException("accountId is required"); //No I18N
			}
			if (edition == null)
			{
				throw new IllegalArgumentException("edition is required (Edition.IN, Edition.IN_SANDBOX, or Edition.US)"); //No I18N
			}
			if (accessToken == null || accessToken.isEmpty())
			{
				throw new IllegalArgumentException("oauthToken is required"); //No I18N
			}
			if (httpClient != null && connectTimeout != null)
			{
				throw new IllegalArgumentException(
						"connectTimeout cannot be combined with a custom httpClient — " //No I18N
								+ "configure the connect timeout on your custom transport instead"); //No I18N
			}

			TokenManager tokenManager = new TokenManager(accessToken);

			Duration rt = requestTimeout != null ? requestTimeout : DEFAULT_REQUEST_TIMEOUT;

			HttpClientInterface transport = this.httpClient;
			if (transport == null)
			{
				Duration ct = connectTimeout != null ? connectTimeout : DEFAULT_CONNECT_TIMEOUT;
				transport = new DefaultHttpClient(ct, rt);
			}

			ZohoHttpClient zohoHttpClient = new ZohoHttpClient(
					transport, tokenManager, edition, accountId, rt, defaultHeaders);

			consumed = true;
			return new ZohoPaymentsClient(zohoHttpClient, tokenManager, edition);
		}

		private void checkNotConsumed()
		{
			if (consumed)
			{
				throw new IllegalStateException(
						"This Builder has already produced a client; " //No I18N
								+ "create a new builder via ZohoPayments.builder()."); //No I18N
			}
		}
	}

	/**
	 * Generates a new OAuth access token by exchanging the supplied refresh token.
	 * The SDK does not refresh tokens automatically — callers push the new token
	 * into a running client via {@link ZohoPaymentsClient#updateToken}.
	 */
	public static OAuthToken generateAccessToken(String refreshToken, String clientId,
			String clientSecret, String redirectUri, Edition edition)
	{
		return OAuthTokenRefresher.generateAccessToken(refreshToken, clientId, clientSecret, redirectUri, edition);
	}
}
