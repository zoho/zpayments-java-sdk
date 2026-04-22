package com.zohopayments.auth;

public final class OAuthToken
{
	private final String accessToken;
	private final long expiresIn; // seconds

	public OAuthToken(String accessToken, long expiresIn)
	{
		if (accessToken == null || accessToken.isEmpty())
		{
			throw new IllegalArgumentException("access Token is required"); //No I18N
		}
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() { return accessToken; }

	public long getExpiresIn() { return expiresIn; }
}
