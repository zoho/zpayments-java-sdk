package com.zoho.payments.param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Configurations payload for payment session create/update requests.
public final class ConfigurationsParams
{
	private final List<String> allowedPaymentMethods;
	private final HostedPageParams hostedPageParameters;

	private ConfigurationsParams(Builder b)
	{
		this.allowedPaymentMethods = b.allowedPaymentMethods != null
				? new ArrayList<>(b.allowedPaymentMethods)
				: null;
		this.hostedPageParameters = b.hostedPageParameters;
	}

	public List<String> getAllowedPaymentMethods()
	{
		return allowedPaymentMethods != null
				? Collections.unmodifiableList(allowedPaymentMethods)
				: Collections.emptyList();
	}

	public HostedPageParams getHostedPageParameters() { return hostedPageParameters; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private List<String> allowedPaymentMethods;
		private HostedPageParams hostedPageParameters;

		private Builder() {}

		public Builder allowedPaymentMethods(List<String> allowedPaymentMethods) { this.allowedPaymentMethods = allowedPaymentMethods; return this; }
		public Builder hostedPageParameters(HostedPageParams hostedPageParameters) { this.hostedPageParameters = hostedPageParameters; return this; }

		public ConfigurationsParams build()
		{
			return new ConfigurationsParams(this);
		}
	}
}
