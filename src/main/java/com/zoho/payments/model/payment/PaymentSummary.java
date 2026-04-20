package com.zoho.payments.model.payment;

import com.zoho.payments.PaymentService;

/**
 * Payment resource returned in list responses.
 * For the full object, use {@link PaymentService#get(String)}.
 */
public final class PaymentSummary
{
	private String paymentId;
	private String amount;
	private String currency;
	private String receiptEmail;
	private String referenceNumber;
	private String amountCaptured;
	private String amountRefunded;
	private String feeAmount;
	private String netAmount;
	private String status;
	private Long date;
	private PaymentMethod paymentMethod;

	PaymentSummary() {}

	public String getPaymentId() { return paymentId; }
	public String getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public String getReceiptEmail() { return receiptEmail; }
	public String getReferenceNumber() { return referenceNumber; }
	public String getAmountCaptured() { return amountCaptured; }
	public String getAmountRefunded() { return amountRefunded; }
	public String getFeeAmount() { return feeAmount; }
	public String getNetAmount() { return netAmount; }
	public String getStatus() { return status; }
	public Long getDate() { return date; }
	public PaymentMethod getPaymentMethod() { return paymentMethod; }

	// Payment method snapshot returned in list responses.
	public static final class PaymentMethod
	{
		private String paymentMethodId;
		private String type;

		PaymentMethod() {}

		public String getPaymentMethodId() { return paymentMethodId; }
		public String getType() { return type; }
	}
}
