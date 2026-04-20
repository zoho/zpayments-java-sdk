package com.zoho.payments.param.paymentmethod;

import com.zoho.payments.param.PostalAddressParams;
// Request body for {@code PUT /paymentmethods/{payment_method_id}}.
public final class PaymentMethodUpdateParams
{
	private final String type;
	private final CardUpdate card;
	private final AchDebitUpdate achDebit;
	private final PostalAddressParams billingAddress;

	private PaymentMethodUpdateParams(Builder b)
	{
		this.type = b.type;
		this.card = b.card;
		this.achDebit = b.achDebit;
		this.billingAddress = b.billingAddress;
	}

	public String getType() { return type; }
	public CardUpdate getCard() { return card; }
	public AchDebitUpdate getAchDebit() { return achDebit; }
	public PostalAddressParams getBillingAddress() { return billingAddress; }

	public static Builder builder() { return new Builder(); }

	public static final class CardUpdate
	{
		private final String expiryMonth;
		private final String expiryYear;

		private CardUpdate(Builder b)
		{
			this.expiryMonth = b.expiryMonth;
			this.expiryYear = b.expiryYear;
		}

		public String getExpiryMonth() { return expiryMonth; }
		public String getExpiryYear() { return expiryYear; }

		public static Builder builder() { return new Builder(); }

		public static final class Builder
		{
			private String expiryMonth;
			private String expiryYear;

			private Builder() {}

			public Builder expiryMonth(String expiryMonth) { this.expiryMonth = expiryMonth; return this; }
			public Builder expiryYear(String expiryYear) { this.expiryYear = expiryYear; return this; }

			public CardUpdate build()
			{
				return new CardUpdate(this);
			}
		}
	}

	// ACH debit fields that can be updated on a payment method.
	public static final class AchDebitUpdate
	{
		private final String accountHolderType;

		private AchDebitUpdate(Builder b)
		{
			this.accountHolderType = b.accountHolderType;
		}

		public String getAccountHolderType() { return accountHolderType; }

		public static Builder builder() { return new Builder(); }

		public static final class Builder
		{
			private String accountHolderType;

			private Builder() {}

			public Builder accountHolderType(String accountHolderType)
            {
                this.accountHolderType = accountHolderType;
                return this;
            }

			public AchDebitUpdate build()
			{
				return new AchDebitUpdate(this);
			}
		}
	}

	public static final class Builder
	{
		private String type;
		private CardUpdate card;
		private AchDebitUpdate achDebit;
		private PostalAddressParams billingAddress;

		private Builder() {}

		public Builder type(String type) { this.type = type; return this; }
		public Builder card(CardUpdate card) { this.card = card; return this; }
		public Builder achDebit(AchDebitUpdate achDebit) { this.achDebit = achDebit; return this; }
		public Builder billingAddress(PostalAddressParams billingAddress) { this.billingAddress = billingAddress; return this; }

		public PaymentMethodUpdateParams build()
		{
			if (type == null || type.isEmpty())
			{
				throw new IllegalArgumentException("type is required"); //No I18N
			}
			return new PaymentMethodUpdateParams(this);
		}
	}
}
