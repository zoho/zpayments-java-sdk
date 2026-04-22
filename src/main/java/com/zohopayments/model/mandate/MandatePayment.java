package com.zohopayments.model.mandate;

import com.zohopayments.model.payment.Payment;

/**
 * Payment response returned by the Execute Mandate API.
 * Contains a limited subset of fields compared to {@link Payment}.
 */
public final class MandatePayment
{
	private String paymentsSessionId;
	private String invoiceNumber;
	private String customerId;
	private String amount;
	private String currency;
	private String status;
	private String statementDescriptor;
	private String description;
	private String referenceNumber;
	private Long date;
	private PaymentMethod paymentMethod;

	MandatePayment() {}

	public String getPaymentsSessionId() { return paymentsSessionId; }
	public String getInvoiceNumber() { return invoiceNumber; }
	public String getCustomerId() { return customerId; }
	public String getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public String getStatus() { return status; }
	public String getStatementDescriptor() { return statementDescriptor; }
	public String getDescription() { return description; }
	public String getReferenceNumber() { return referenceNumber; }
	public Long getDate() { return date; }
	public PaymentMethod getPaymentMethod() { return paymentMethod; }

	public static final class PaymentMethod
	{
		private String type;

		PaymentMethod() {}

		public String getType() { return type; }
	}
}
