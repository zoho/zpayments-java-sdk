package com.zohopayments.model.paymentmethod;

// Card details nested under a payment method in API responses.
public final class CardDetail
{
	private String cardHolderName;
	private String lastFourDigits;
	private String expiryMonth;
	private String expiryYear;
	private String brand;
	private String funding;

	CardDetail() {}

	public String getCardHolderName() { return cardHolderName; }
	public String getLastFourDigits() { return lastFourDigits; }
	public String getExpiryMonth() { return expiryMonth; }
	public String getExpiryYear() { return expiryYear; }
	public String getBrand() { return brand; }
	public String getFunding() { return funding; }
}
