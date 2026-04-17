package com.zoho.payments.model.paymentmethod;

// Postal address nested under a payment or payment method (shipping / billing address).
public final class AddressDetail
{
	private String name;
	private String addressId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private String country;

	AddressDetail() {}

	public String getName() { return name; }
	public String getAddressId() { return addressId; }
	public String getAddressLine1() { return addressLine1; }
	public String getAddressLine2() { return addressLine2; }
	public String getCity() { return city; }
	public String getState() { return state; }
	public String getPostalCode() { return postalCode; }
	public String getCountry() { return country; }
}
