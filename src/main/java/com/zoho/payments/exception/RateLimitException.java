package com.zoho.payments.exception;

// Thrown when the API returns HTTP 429 — too many requests.
public class RateLimitException extends ZohoPaymentsAPIException
{
	private static final long serialVersionUID = 1L;

	public RateLimitException(String codeString, String errorMessage)
	{
		super(429, codeString, errorMessage);
	}
}
