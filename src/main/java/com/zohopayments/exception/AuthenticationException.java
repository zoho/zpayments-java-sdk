package com.zohopayments.exception;

// Thrown when the API returns HTTP 401 — invalid or expired credentials.
public class AuthenticationException extends ZohoPaymentsAPIException
{
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String codeString, String errorMessage)
	{
		super(401, codeString, errorMessage);
	}
}
