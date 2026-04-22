package com.zohopayments.model.paymentmethod;

// ACH Direct Debit details nested under a payment method in API responses.
public final class AchDebitDetail
{
	private String accountHolderName;
	private String lastFourDigits;
	private String accountHolderType;
	private String accountType;
	private String bankName;
	private String routingNumber;

	AchDebitDetail() {}

	public String getAccountHolderName() { return accountHolderName; }
	public String getLastFourDigits() { return lastFourDigits; }
	public String getAccountHolderType() { return accountHolderType; }
	public String getAccountType() { return accountType; }
	public String getBankName() { return bankName; }
	public String getRoutingNumber() { return routingNumber; }
}
