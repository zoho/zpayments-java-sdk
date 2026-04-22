package com.zohopayments;

import com.google.gson.JsonObject;
import com.zohopayments.model.paymentlink.PaymentLink;
import com.zohopayments.param.paymentlink.PaymentLinkCreateParams;
import com.zohopayments.param.paymentlink.PaymentLinkUpdateParams;

// Zoho Payments Payment Links API ({@code /paymentlinks}).
public final class PaymentLinkService
{
	private final ZohoHttpClient client;

	PaymentLinkService(ZohoHttpClient client)
	{
		this.client = client;
	}

	public PaymentLink create(PaymentLinkCreateParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/paymentlinks", body, PaymentLink.class, "payment_links"); //No I18N
	}

	public PaymentLink update(String paymentLinkId, PaymentLinkUpdateParams params)
	{
		if (paymentLinkId == null || paymentLinkId.isEmpty())
		{
			throw new IllegalArgumentException("paymentLinkId is required"); //No I18N
		}
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.put("/paymentlinks/" + ZohoHttpClient.encodePath(paymentLinkId), //No I18N
				body, PaymentLink.class, "payment_links"); //No I18N
	}

	public PaymentLink get(String paymentLinkId)
	{
		if (paymentLinkId == null || paymentLinkId.isEmpty())
		{
			throw new IllegalArgumentException("paymentLinkId is required"); //No I18N
		}
		return client.get("/paymentlinks/" + ZohoHttpClient.encodePath(paymentLinkId), //No I18N
				PaymentLink.class, "payment_links"); //No I18N
	}

	public PaymentLink cancel(String paymentLinkId)
	{
		if (paymentLinkId == null || paymentLinkId.isEmpty())
		{
			throw new IllegalArgumentException("paymentLinkId is required"); //No I18N
		}
		return client.put("/paymentlinks/" + ZohoHttpClient.encodePath(paymentLinkId) + "/cancel", //No I18N
				PaymentLink.class, "payment_links"); //No I18N
	}
}
