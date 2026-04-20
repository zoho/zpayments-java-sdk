package com.zoho.payments.model.customer;

// Payment method summary returned inside a retrieved customer (US edition).
public final class CustomerPaymentMethod
{
	private String paymentMethodId;
	private String type;
	private String brand;
	private String lastFourDigits;
	private String expiryMonth;
	private String expiryYear;
	private Card card;
	private AchDebit achDebit;

	CustomerPaymentMethod() {}

	public String getPaymentMethodId() { return paymentMethodId; }
	public String getType() { return type; }
	public String getBrand() { return brand; }
	public String getLastFourDigits() { return lastFourDigits; }
	public String getExpiryMonth() { return expiryMonth; }
	public String getExpiryYear() { return expiryYear; }
	public Card getCard() { return card; }
	public AchDebit getAchDebit() { return achDebit; }

	// Card details nested within a customer payment method.
	public static final class Card
	{
		private String cardHolderName;
		private String lastFourDigits;
		private String expiryMonth;
		private String expiryYear;

		Card() {}

		public String getCardHolderName() { return cardHolderName; }
		public String getLastFourDigits() { return lastFourDigits; }
		public String getExpiryMonth() { return expiryMonth; }
		public String getExpiryYear() { return expiryYear; }
	}

	// ACH Direct Debit details nested within a customer payment method.
	public static final class AchDebit
	{
		private String accountHolderName;
		private String lastFourDigits;
		private String accountHolderType;
		private String accountType;
		private String bankName;
		private String routingNumber;

		AchDebit() {}

		public String getAccountHolderName() { return accountHolderName; }
		public String getLastFourDigits() { return lastFourDigits; }
		public String getAccountHolderType() { return accountHolderType; }
		public String getAccountType() { return accountType; }
		public String getBankName() { return bankName; }
		public String getRoutingNumber() { return routingNumber; }
	}
}
