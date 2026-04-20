package com.zoho.payments.model.virtualaccount;

// Payment summary returned in the virtual account payments list.
public final class VirtualAccountPayment
{
	private String paymentId;
	private String customerId;
	private String virtualAccountId;
	private String customerName;
	private String customerEmail;
	private String amount;
	private String receiptEmail;
	private String dialingCode;
	private String phone;
	private String referenceNumber;
	private String transactionReferenceNumber;
	private String paymentType;
	private String currency;
	private String balance;
	private String amountCaptured;
	private String amountRefunded;
	private String feeAmount;
	private String status;
	private String transactionType;
	private String fraudAlert;
	private String failureReason;
	private String failureCategory;
	private String nextAction;
	private String tip;
	private Long date;
	private PaymentMethod paymentMethod;

	VirtualAccountPayment() {}

	public String getPaymentId() { return paymentId; }
	public String getCustomerId() { return customerId; }
	public String getVirtualAccountId() { return virtualAccountId; }
	public String getCustomerName() { return customerName; }
	public String getCustomerEmail() { return customerEmail; }
	public String getAmount() { return amount; }
	public String getReceiptEmail() { return receiptEmail; }
	public String getDialingCode() { return dialingCode; }
	public String getPhone() { return phone; }
	public String getReferenceNumber() { return referenceNumber; }
	public String getTransactionReferenceNumber() { return transactionReferenceNumber; }
	public String getPaymentType() { return paymentType; }
	public String getCurrency() { return currency; }
	public String getBalance() { return balance; }
	public String getAmountCaptured() { return amountCaptured; }
	public String getAmountRefunded() { return amountRefunded; }
	public String getFeeAmount() { return feeAmount; }
	public String getStatus() { return status; }
	public String getTransactionType() { return transactionType; }
	public String getFraudAlert() { return fraudAlert; }
	public String getFailureReason() { return failureReason; }
	public String getFailureCategory() { return failureCategory; }
	public String getNextAction() { return nextAction; }
	public String getTip() { return tip; }
	public Long getDate() { return date; }
	public PaymentMethod getPaymentMethod() { return paymentMethod; }

	public static final class PaymentMethod
	{
		private String paymentMethodId;
		private String type;

		PaymentMethod() {}

		public String getPaymentMethodId() { return paymentMethodId; }
		public String getType() { return type; }
	}
}
