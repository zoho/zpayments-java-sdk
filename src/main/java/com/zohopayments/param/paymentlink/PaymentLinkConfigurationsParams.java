package com.zohopayments.param.paymentlink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Configurations payload for payment link create and update requests.
public final class PaymentLinkConfigurationsParams
{
	private final List<String> allowedPaymentMethods;

	private PaymentLinkConfigurationsParams(Builder b)
	{
		this.allowedPaymentMethods = b.allowedPaymentMethods != null
				? Collections.unmodifiableList(new ArrayList<>(b.allowedPaymentMethods))
				: null;
	}

	public List<String> getAllowedPaymentMethods()
	{
		return allowedPaymentMethods != null ? allowedPaymentMethods : Collections.emptyList();
	}

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private List<String> allowedPaymentMethods;

		private Builder() {}

		public Builder allowedPaymentMethods(List<String> allowedPaymentMethods) { this.allowedPaymentMethods = allowedPaymentMethods; return this; }

		public PaymentLinkConfigurationsParams build()
		{
			return new PaymentLinkConfigurationsParams(this);
		}
	}
}
