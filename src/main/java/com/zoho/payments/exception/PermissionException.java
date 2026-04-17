package com.zoho.payments.exception;

// Thrown when the API returns HTTP 403 — insufficient permissions or scope.
public class PermissionException extends ZohoPaymentsAPIException
{
	private static final long serialVersionUID = 1L;

	public PermissionException(String codeString, String errorMessage)
	{
		super(403, codeString, errorMessage);
	}
}
