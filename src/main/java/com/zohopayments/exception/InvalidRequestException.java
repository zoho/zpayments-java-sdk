package com.zohopayments.exception;

// Thrown when the API returns HTTP 400 or 422 — malformed request or validation failure.
public class InvalidRequestException extends ZohoPaymentsAPIException
{
	private static final long serialVersionUID = 1L;

	public InvalidRequestException(int httpStatusCode, String codeString, String errorMessage)
	{
		super(httpStatusCode, codeString, errorMessage);
	}
}
