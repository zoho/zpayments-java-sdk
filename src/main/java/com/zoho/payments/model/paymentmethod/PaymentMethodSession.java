package com.zoho.payments.model.paymentmethod;

// A payment method collection session ({@code /paymentmethodsessions}).
public final class PaymentMethodSession
{
	private String paymentMethodSessionId;
	private String customerId;
	private String description;
	private Long createdTime;
	private String status;
	private PaymentMethodSessionDetail paymentMethod;

	PaymentMethodSession() {}

	public String getPaymentMethodSessionId() { return paymentMethodSessionId; }
	public String getCustomerId() { return customerId; }
	public String getDescription() { return description; }
	public Long getCreatedTime() { return createdTime; }
	public String getStatus() { return status; }
	public PaymentMethodSessionDetail getPaymentMethod() { return paymentMethod; }
}
