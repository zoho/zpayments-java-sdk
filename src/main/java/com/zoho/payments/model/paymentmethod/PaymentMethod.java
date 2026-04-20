package com.zoho.payments.model.paymentmethod;

// A saved payment method ({@code /paymentmethods}).
public final class PaymentMethod
{
	private String paymentMethodId;
	private String customerId;
	private String customerName;
	private String customerEmail;
	private String type;
	private String status;
	private String currency;
	private String source;
	private Long createdTime;
	private Long lastModifiedTime;
	private SavedCardDetail card;
	private AchDebitDetail achDebit;
	private AddressDetail billingAddress;

	PaymentMethod() {}

	public String getPaymentMethodId() { return paymentMethodId; }
	public String getCustomerId() { return customerId; }
	public String getCustomerName() { return customerName; }
	public String getCustomerEmail() { return customerEmail; }
	public String getType() { return type; }
	public String getStatus() { return status; }
	public String getCurrency() { return currency; }
	public String getSource() { return source; }
	public Long getCreatedTime() { return createdTime; }
	public Long getLastModifiedTime() { return lastModifiedTime; }
	public SavedCardDetail getCard() { return card; }
	public AchDebitDetail getAchDebit() { return achDebit; }
	public AddressDetail getBillingAddress() { return billingAddress; }
}
