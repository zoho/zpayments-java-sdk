package com.zohopayments.model.paymentmethod;

/**
 * Card details returned under a saved payment method.
 * Adds {@code country} and {@code card_checks} over {@link CardDetail}.
 */
public final class SavedCardDetail
{
	private String cardHolderName;
	private String lastFourDigits;
	private String expiryMonth;
	private String expiryYear;
	private String brand;
	private String funding;
	private String country;
	private CardChecks cardChecks;

	SavedCardDetail() {}

	public String getCardHolderName() { return cardHolderName; }
	public String getLastFourDigits() { return lastFourDigits; }
	public String getExpiryMonth() { return expiryMonth; }
	public String getExpiryYear() { return expiryYear; }
	public String getBrand() { return brand; }
	public String getFunding() { return funding; }
	public String getCountry() { return country; }
	public CardChecks getCardChecks() { return cardChecks; }

	// Processor verification checks (address, postal code, CVC).
	public static final class CardChecks
	{
		private String addressLineCheck;
		private String postalCodeCheck;
		private String cvcCheck;

		CardChecks() {}

		public String getAddressLineCheck() { return addressLineCheck; }
		public String getPostalCodeCheck() { return postalCodeCheck; }
		public String getCvcCheck() { return cvcCheck; }
	}
}
