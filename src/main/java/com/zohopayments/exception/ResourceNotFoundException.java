package com.zohopayments.exception;

// Thrown when the API returns HTTP 404 — requested resource does not exist.
public class ResourceNotFoundException extends ZohoPaymentsAPIException
{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String codeString, String errorMessage)
	{
		super(404, codeString, errorMessage);
	}
}
