package com.zohopayments.param;

/**
 * Shared length and range validation for param builders.
 * Centralises API field constraints so every builder enforces
 * the same limits documented in the Zoho Payments API.
 */
public final class ParamValidator
{
	static final int MAX_DESCRIPTION_LENGTH = 500;
	static final int MAX_INVOICE_NUMBER_LENGTH = 50;
	static final int MAX_REFERENCE_LENGTH = 50;

	private ParamValidator() {}

	public static void validateDescription(String description)
	{
		if (description != null && description.length() > MAX_DESCRIPTION_LENGTH)
		{
			throw new IllegalArgumentException(
					"description must be at most " + MAX_DESCRIPTION_LENGTH + " characters"); //No I18N
		}
	}

	public static void validateInvoiceNumber(String invoiceNumber)
	{
		if (invoiceNumber != null && invoiceNumber.length() > MAX_INVOICE_NUMBER_LENGTH)
		{
			throw new IllegalArgumentException(
					"invoiceNumber must be at most " + MAX_INVOICE_NUMBER_LENGTH + " characters"); //No I18N
		}
	}

	public static void validateReferenceNumber(String referenceNumber)
	{
		if (referenceNumber != null && referenceNumber.length() > MAX_REFERENCE_LENGTH)
		{
			throw new IllegalArgumentException(
					"referenceNumber must be at most " + MAX_REFERENCE_LENGTH + " characters"); //No I18N
		}
	}
}
