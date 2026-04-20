package com.zoho.payments;

import com.google.gson.JsonObject;
import com.zoho.payments.model.paymentmethod.PaymentMethod;
import com.zoho.payments.param.paymentmethod.PaymentMethodUpdateParams;

// Payment Methods API ({@code /paymentmethods}) — retrieve, update, or delete a saved method.
public final class PaymentMethodService
{
	private final ZohoHttpClient client;

	PaymentMethodService(ZohoHttpClient client)
	{
		this.client = client;
	}

	public PaymentMethod get(String paymentMethodId)
	{
		if (paymentMethodId == null || paymentMethodId.isEmpty())
		{
			throw new IllegalArgumentException("paymentMethodId is required"); //No I18N
		}
		return client.get("/paymentmethods/" + ZohoHttpClient.encodePath(paymentMethodId), //No I18N
				PaymentMethod.class, "payment_method"); //No I18N
	}

	public PaymentMethod update(String paymentMethodId, PaymentMethodUpdateParams params)
	{
		if (paymentMethodId == null || paymentMethodId.isEmpty())
		{
			throw new IllegalArgumentException("paymentMethodId is required"); //No I18N
		}
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.put("/paymentmethods/" + ZohoHttpClient.encodePath(paymentMethodId), //No I18N
				body, PaymentMethod.class, "payment_method"); //No I18N
	}

	public void delete(String paymentMethodId)
	{
		if (paymentMethodId == null || paymentMethodId.isEmpty())
		{
			throw new IllegalArgumentException("paymentMethodId is required"); //No I18N
		}
		client.delete("/paymentmethods/" + ZohoHttpClient.encodePath(paymentMethodId)); //No I18N
	}
}
