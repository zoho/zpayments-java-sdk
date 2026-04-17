package com.zoho.payments.param;

/**
 * Customer notification preferences for request payloads (payment link create/update).
 *
 * <p>On the response side, see {@link com.zoho.payments.model.NotifyCustomer}.
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
