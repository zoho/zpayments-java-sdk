package com.zohopayments.exception;

public class ZohoPaymentsAPIException extends ZohoPaymentsException
{
	private static final long serialVersionUID = 1L;

	private final int httpStatusCode;
	private final String codeString;
	private final String errorMessage;

	public ZohoPaymentsAPIException(int httpStatusCode, String codeString, String errorMessage)
	{
		super("API error (HTTP " + httpStatusCode + "): code=" + codeString + ", message=" + errorMessage); //No I18N
		this.httpStatusCode = httpStatusCode;
		this.codeString = codeString;
		this.errorMessage = errorMessage;
	}

	public int getHttpStatusCode()
	{
		return httpStatusCode;
	}

	// Returns the raw code string from the API response (e.g. "invalid_request", "feature_not_enabled").
	public String getCodeString()
	{
		return codeString;
	}

	public String getApiErrorMessage()
	{
		return errorMessage;
	}
}
