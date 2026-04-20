package com.zoho.payments.exception;

// Thrown when the SDK cannot reach the Zoho Payments API due to network or I/O issues.
public class ConnectionException extends ZohoPaymentsException
{
	private static final long serialVersionUID = 1L;

	public ConnectionException(String message)
	{
		super(message);
	}

	public ConnectionException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
