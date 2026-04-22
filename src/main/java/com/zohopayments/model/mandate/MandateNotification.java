package com.zohopayments.model.mandate;

// Mandate notification resource (send/retrieve).
public final class MandateNotification
{
	private String mandateId;
	private String mandateNotificationId;
	private String customerId;
	private String mandateAmount;
	private String currency;
	private String amountRule;
	private String notificationAmount;
	private String notificationStatus;
	private String description;
	private String invoiceNumber;
	private Long notificationDate;
	private Long executionDate;
	private String amount;
	private MandatePaymentMethod paymentMethod;

	MandateNotification() {}

	public String getMandateId() { return mandateId; }
	public String getMandateNotificationId() { return mandateNotificationId; }
	public String getCustomerId() { return customerId; }
	public String getMandateAmount() { return mandateAmount; }
	public String getCurrency() { return currency; }
	public String getAmountRule() { return amountRule; }
	public String getNotificationAmount() { return notificationAmount; }
	public String getNotificationStatus() { return notificationStatus; }
	public String getDescription() { return description; }
	public String getInvoiceNumber() { return invoiceNumber; }
	public Long getNotificationDate() { return notificationDate; }
	public Long getExecutionDate() { return executionDate; }
	public String getAmount() { return amount; }
	public MandatePaymentMethod getPaymentMethod() { return paymentMethod; }
}
