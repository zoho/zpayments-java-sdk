package com.zohopayments;

import com.google.gson.JsonObject;
import com.zohopayments.model.refund.Refund;
import com.zohopayments.param.refund.RefundCreateParams;

// Zoho Payments Refunds API ({@code /refunds}).
public final class RefundService
{
	private final ZohoHttpClient client;

	RefundService(ZohoHttpClient client)
	{
		this.client = client;
	}

	public Refund create(String paymentId, RefundCreateParams params)
	{
		if (paymentId == null || paymentId.isEmpty())
		{
			throw new IllegalArgumentException("paymentId is required"); //No I18N
		}
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		String path = "/payments/" + ZohoHttpClient.encodePath(paymentId) + "/refunds"; //No I18N
		return client.post(path, body, Refund.class, "refund"); //No I18N
	}

	public Refund get(String refundId)
	{
		if (refundId == null || refundId.isEmpty())
		{
			throw new IllegalArgumentException("refundId is required"); //No I18N
		}
		return client.get("/refunds/" + ZohoHttpClient.encodePath(refundId), Refund.class, "refund"); //No I18N
	}

}
