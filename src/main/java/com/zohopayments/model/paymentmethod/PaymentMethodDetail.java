package com.zohopayments.model.paymentmethod;

// Payment method snapshot on a payment or mandate response.
public final class PaymentMethodDetail
{
	private String type;
	private String mandateId;

	private CardDetail card;
	private AchDebitDetail achDebit;

	private Upi upi;
	private NetBanking netBanking;
	private BankTransfer bankTransfer;

	PaymentMethodDetail() {}

	public String getType() { return type; }
	public String getMandateId() { return mandateId; }
	public CardDetail getCard() { return card; }
	public AchDebitDetail getAchDebit() { return achDebit; }
	public Upi getUpi() { return upi; }
	public NetBanking getNetBanking() { return netBanking; }
	public BankTransfer getBankTransfer() { return bankTransfer; }

	// UPI payment method details (IN only).
	public static final class Upi
	{
		private String upiId;
		private String channel;
		private String accountType;

		Upi() {}

		public String getUpiId() { return upiId; }
		public String getChannel() { return channel; }
		public String getAccountType() { return accountType; }
	}

	// Net banking payment method details (IN only).
	public static final class NetBanking
	{
		private String bankName;

		NetBanking() {}

		public String getBankName() { return bankName; }
	}

	// Bank transfer payment method details (IN only).
	public static final class BankTransfer
	{
		private String virtualAccountNumber;
		private String mode;
		private String payerName;
		private String payerAccountNo;
		private String payerIfscCode;

		BankTransfer() {}

		public String getVirtualAccountNumber() { return virtualAccountNumber; }
		public String getMode() { return mode; }
		public String getPayerName() { return payerName; }
		public String getPayerAccountNo() { return payerAccountNo; }
		public String getPayerIfscCode() { return payerIfscCode; }
	}
}
