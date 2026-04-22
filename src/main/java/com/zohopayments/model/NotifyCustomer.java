package com.zohopayments.model;

import com.zohopayments.param.NotifyCustomerParams;

/**
 * Customer notification preferences returned on a resource.
 *
 * <p>Response-only type. For request payloads, use
 * {@link NotifyCustomerParams}.
 */
public final class NotifyCustomer
{
	private Boolean email;
	private Boolean sms;

	NotifyCustomer() {}

	public Boolean getEmail() { return email; }
	public Boolean getSms() { return sms; }
}
