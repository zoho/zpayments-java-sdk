package com.zohopayments.param;

// Address payload for payment and payment-method APIs ({@code shipping_address}, {@code billing_address}).
public final class PostalAddressParams
{
	private final String name;
	private final String addressLine1;
	private final String addressLine2;
	private final String city;
	private final String state;
	private final String country;
	private final String postalCode;

	private PostalAddressParams(Builder b)
	{
		this.name = b.name;
		this.addressLine1 = b.addressLine1;
		this.addressLine2 = b.addressLine2;
		this.city = b.city;
		this.state = b.state;
		this.country = b.country;
		this.postalCode = b.postalCode;
	}

	public String getName() { return name; }
	public String getAddressLine1() { return addressLine1; }
	public String getAddressLine2() { return addressLine2; }
	public String getCity() { return city; }
	public String getState() { return state; }
	public String getCountry() { return country; }
	public String getPostalCode() { return postalCode; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private String name;
		private String addressLine1;
		private String addressLine2;
		private String city;
		private String state;
		private String country;
		private String postalCode;

		private Builder() {}

		public Builder name(String name) { this.name = name; return this; }
		public Builder addressLine1(String addressLine1) { this.addressLine1 = addressLine1; return this; }
		public Builder addressLine2(String addressLine2) { this.addressLine2 = addressLine2; return this; }
		public Builder city(String city) { this.city = city; return this; }
		public Builder state(String state) { this.state = state; return this; }
		public Builder country(String country) { this.country = country; return this; }
		public Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }

		public PostalAddressParams build()
		{
			if (country == null || country.isEmpty())
			{
				throw new IllegalArgumentException("country is required for address"); //No I18N
			}
			return new PostalAddressParams(this);
		}
	}
}
