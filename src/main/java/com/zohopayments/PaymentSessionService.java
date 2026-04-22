package com.zohopayments;

import com.google.gson.JsonObject;
import com.zohopayments.model.paymentsession.PaymentSession;
import com.zohopayments.param.paymentsession.PaymentSessionCreateParams;

// Zoho Payments Payment Session API ({@code /paymentsessions}).
public final class PaymentSessionService
{
	private final ZohoHttpClient client;

	PaymentSessionService(ZohoHttpClient client)
	{
		this.client = client;
	}

	public PaymentSession create(PaymentSessionCreateParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/paymentsessions", body, PaymentSession.class, "payments_session"); //No I18N
	}

	public PaymentSession get(String paymentSessionId)
	{
		if (paymentSessionId == null || paymentSessionId.isEmpty())
		{
			throw new IllegalArgumentException("paymentSessionId is required"); //No I18N
		}
		return client.get("/paymentsessions/" + ZohoHttpClient.encodePath(paymentSessionId), //No I18N
				PaymentSession.class, "payments_session"); //No I18N
	}
}
