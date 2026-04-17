package com.zoho.payments.model.paymentsession;

// Minimal payment summary returned under a payment session.
public final class PaymentSessionPayment
{
	private String paymentId;
	private String status;
	private Long createdTime;

	PaymentSessionPayment() {}

	public String getPaymentId() { return paymentId; }
	public String getStatus() { return status; }
	public Long getCreatedTime() { return createdTime; }
}

