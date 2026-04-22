package com.zohopayments;

public enum Edition
{
	IN("https://payments.zoho.in/api/v1", "https://accounts.zoho.in"), //No I18N
	IN_SANDBOX("https://paymentssandbox.zoho.in/api/v1", "https://accounts.zoho.in"), //No I18N
	US("https://payments.zoho.com/api/v1", "https://accounts.zoho.com"); //No I18N

	private final String baseUrl;
	private final String accountsUrl;

	Edition(String baseUrl, String accountsUrl)
	{
		this.baseUrl = baseUrl;
		this.accountsUrl = accountsUrl;
	}

	public String getBaseUrl()
	{
		return baseUrl;
	}

	public String getAccountsUrl()
	{
		return accountsUrl;
	}

	public boolean isUS()
	{
		return this == US;
	}

	public boolean isIN()
	{
		return this == IN || this == IN_SANDBOX;
	}
}
