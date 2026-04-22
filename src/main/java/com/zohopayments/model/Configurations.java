package com.zohopayments.model;

import com.zohopayments.param.ConfigurationsParams;
import java.util.Collections;
import java.util.List;

/**
 * Configurations returned on a payment session or payment link resource.
 *
 * <p>Response-only type. For request payloads, use
 * {@link ConfigurationsParams}.
 */
public final class Configurations
{
	private List<String> allowedPaymentMethods;
	private HostedPageResponse hostedPageParameters;

	Configurations() {}

	// Returns the list of allowed payment methods, or an empty list if none were configured.
	public List<String> getAllowedPaymentMethods()
	{
		return allowedPaymentMethods != null
				? Collections.unmodifiableList(allowedPaymentMethods)
				: Collections.emptyList();
	}

	public HostedPageResponse getHostedPageParameters() { return hostedPageParameters; }

	// Hosted page parameters returned in the response.
	public static final class HostedPageResponse
	{
		private String description;
		private String successUrl;
		private String failureUrl;
		private String name;
		private String email;
		private String phone;
		private String phoneCountryCode;
		private String udf1;
		private String udf2;
		private String udf3;
		private String udf4;
		private String udf5;

		HostedPageResponse() {}

		public String getDescription() { return description; }
		public String getSuccessUrl() { return successUrl; }
		public String getFailureUrl() { return failureUrl; }
		public String getName() { return name; }
		public String getEmail() { return email; }
		public String getPhone() { return phone; }
		public String getPhoneCountryCode() { return phoneCountryCode; }
		public String getUdf1() { return udf1; }
		public String getUdf2() { return udf2; }
		public String getUdf3() { return udf3; }
		public String getUdf4() { return udf4; }
		public String getUdf5() { return udf5; }
	}
}
