package com.zoho.payments.param.mandate;

import com.zoho.payments.param.HostedPageParams;

// Configurations payload for mandate enrollment session create requests.
public final class MandateConfigurationsParams
{
	private final HostedPageParams hostedPageParameters;

	private MandateConfigurationsParams(Builder b)
	{
		this.hostedPageParameters = b.hostedPageParameters;
	}

	public HostedPageParams getHostedPageParameters() { return hostedPageParameters; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private HostedPageParams hostedPageParameters;

		private Builder() {}

		public Builder hostedPageParameters(HostedPageParams hostedPageParameters) { this.hostedPageParameters = hostedPageParameters; return this; }

		public MandateConfigurationsParams build()
		{
			return new MandateConfigurationsParams(this);
		}
	}
}
