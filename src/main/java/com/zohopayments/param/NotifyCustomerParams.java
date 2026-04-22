package com.zohopayments.param;

import com.zohopayments.model.NotifyCustomer;

/**
 * Customer notification preferences for request payloads (payment link create/update).
 *
 * <p>On the response side, see {@link NotifyCustomer}.
 */
public final class NotifyCustomerParams
{
	private final Boolean email;
	private final Boolean sms;

	public NotifyCustomerParams(Boolean email, Boolean sms)
	{
		this.email = email;
		this.sms = sms;
	}

	public Boolean getEmail() { return email; }
	public Boolean getSms() { return sms; }
}
