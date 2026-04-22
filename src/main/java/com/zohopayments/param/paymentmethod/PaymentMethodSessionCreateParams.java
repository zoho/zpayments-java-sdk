package com.zohopayments.param.paymentmethod;

import com.zohopayments.param.ParamValidator;
// Request body for {@code POST /paymentmethodsessions}.
public final class PaymentMethodSessionCreateParams
{
	private final String customerId;
	private final String description;

	private PaymentMethodSessionCreateParams(Builder b)
	{
		this.customerId = b.customerId;
		this.description = b.description;
	}

	public String getCustomerId() { return customerId; }
	public String getDescription() { return description; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private String customerId;
		private String description;

		private Builder() {}

		public Builder customerId(String customerId) { this.customerId = customerId; return this; }
		public Builder description(String description) { this.description = description; return this; }

		public PaymentMethodSessionCreateParams build()
		{
			if (customerId == null || customerId.isEmpty())
			{
				throw new IllegalArgumentException("customerId is required"); //No I18N
			}
			ParamValidator.validateDescription(description);
			return new PaymentMethodSessionCreateParams(this);
		}
	}
}
