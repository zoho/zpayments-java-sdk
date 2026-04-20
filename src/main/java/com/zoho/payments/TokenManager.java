package com.zoho.payments;

// Thread-safe holder for the active OAuth access token.
final class TokenManager
{
	private volatile String accessToken;

	TokenManager(String accessToken)
	{
		if (accessToken == null || accessToken.isEmpty())
		{
			throw new IllegalArgumentException("accessToken must not be null or empty"); //No I18N
		}
		this.accessToken = accessToken;
	}

	String getAccessToken()
	{
		return accessToken;
	}

	void updateToken(String newAccessToken)
	{
		if (newAccessToken == null || newAccessToken.isEmpty())
		{
			throw new IllegalArgumentException("newAccessToken must not be null or empty"); //No I18N
		}
		this.accessToken = newAccessToken;
	}
}
