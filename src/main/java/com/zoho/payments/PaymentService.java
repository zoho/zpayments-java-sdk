package com.zoho.payments;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.zoho.payments.model.ListResponse;
import com.zoho.payments.model.payment.Payment;
import com.zoho.payments.model.payment.PaymentSummary;
import com.zoho.payments.param.payment.PaymentCreateParams;
import com.zoho.payments.param.payment.PaymentListParams;

import java.lang.reflect.Type;
import java.util.List;

// Zoho Payments Payments API ({@code /payments}).
public final class PaymentService
{
	private static final Type PAYMENT_LIST_TYPE = new TypeToken<List<PaymentSummary>>() {}.getType();

	private final ZohoHttpClient client;
	private final Edition edition;

	PaymentService(ZohoHttpClient client, Edition edition)
	{
		this.client = client;
		this.edition = edition;
	}

	// Creates a payment using a saved payment method. Requires {@link Edition#US}.
	public Payment create(PaymentCreateParams params)
	{
		if (!edition.isUS())
		{
			throw new UnsupportedOperationException("Payment.create is only available for Edition.US"); //No I18N
		}
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/payments", body, Payment.class, "payment"); //No I18N
	}

	public Payment get(String paymentId)
	{
		if (paymentId == null || paymentId.isEmpty())
		{
			throw new IllegalArgumentException("paymentId is required"); //No I18N
		}
		return client.get("/payments/" + ZohoHttpClient.encodePath(paymentId), //No I18N
				Payment.class, "payment"); //No I18N
	}

	public ListResponse<PaymentSummary> list()
	{
		return list((PaymentListParams) null);
	}

	public ListResponse<PaymentSummary> list(PaymentListParams params)
	{
		QueryParams q = null;
		if (params != null)
		{
			q = new QueryParams()
					.add("status", params.getStatus()) //No I18N
					.add("search_text", params.getSearchText()) //No I18N
					.add("filter_by", params.getFilterBy()) //No I18N
					.add("from_date", params.getFromDate()) //No I18N
					.add("to_date", params.getToDate()) //No I18N
					.add("payment_method_type", params.getPaymentMethodType()) //No I18N
					.add("per_page", params.getPerPage()) //No I18N
					.add("page", params.getPage()); //No I18N
		}
		return client.list("/payments", q, PAYMENT_LIST_TYPE, "payments"); //No I18N
	}
}
