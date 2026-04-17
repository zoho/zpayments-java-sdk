package com.zoho.payments;

import com.google.gson.JsonObject;
import com.zoho.payments.model.paymentmethod.PaymentMethodSession;
import com.zoho.payments.param.paymentmethod.PaymentMethodSessionCreateParams;

// Payment Method Session API ({@code /paymentmethodsessions}).
public final class PaymentMethodSessionService
{
	private final ZohoHttpClient client;

	PaymentMethodSessionService(ZohoHttpClient client)
	{
		this.client = client;
	}

	public PaymentMethodSession create(PaymentMethodSessionCreateParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/paymentmethodsessions", body, PaymentMethodSession.class, "payment_method_session"); //No I18N
	}

	public PaymentMethodSession get(String paymentMethodSessionId)
	{
		if (paymentMethodSessionId == null || paymentMethodSessionId.isEmpty())
		{
			throw new IllegalArgumentException("paymentMethodSessionId is required"); //No I18N
		}
		return client.get("/paymentmethodsessions/" + ZohoHttpClient.encodePath(paymentMethodSessionId), //No I18N
				PaymentMethodSession.class, "payment_method_session"); //No I18N
	}
}
