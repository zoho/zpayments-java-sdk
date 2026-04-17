package com.zoho.payments.model.paymentmethod;

// Payment method snapshot returned inside a Payment Method Session response.
public final class PaymentMethodSessionDetail
{
	private String paymentMethodId;
	private String status;
	private Long createdTime;
	private String type;

	PaymentMethodSessionDetail() {}

	public String getPaymentMethodId() { return paymentMethodId; }
	public String getStatus() { return status; }
	public Long getCreatedTime() { return createdTime; }
	public String getType() { return type; }
}
