package com.zohopayments;

import com.google.gson.JsonObject;
import com.zohopayments.model.mandate.Mandate;
import com.zohopayments.model.mandate.MandateNotification;
import com.zohopayments.model.mandate.MandatePayment;
import com.zohopayments.model.paymentsession.PaymentSession;
import com.zohopayments.param.mandate.MandateEnrollmentSessionCreateParams;
import com.zohopayments.param.mandate.MandateExecuteParams;
import com.zohopayments.param.mandate.MandateExecutionSessionCreateParams;
import com.zohopayments.param.mandate.MandateNotifyParams;

// Zoho Payments Mandates API ({@code /mandates/*}). Requires {@link Edition#IN}.
public final class MandateService
{
	private final ZohoHttpClient client;

	MandateService(ZohoHttpClient client)
	{
		this.client = client;
	}

	// {@code POST /paymentsessions} with mandate enrollment body.
	public PaymentSession createEnrollmentSession(MandateEnrollmentSessionCreateParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/paymentsessions", body, PaymentSession.class, "payments_session"); //No I18N
	}

	// {@code POST /paymentsessions} with mandate execution body.
	public PaymentSession createExecutionSession(MandateExecutionSessionCreateParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/paymentsessions", body, PaymentSession.class, "payments_session"); //No I18N
	}

	// {@code POST /mandates/notify}.
	public MandateNotification sendNotification(MandateNotifyParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/mandates/notify", body, MandateNotification.class, "mandate_notification"); //No I18N
	}

	// {@code POST /mandates/execute}.
	public MandatePayment execute(MandateExecuteParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/mandates/execute", body, MandatePayment.class, "payment"); //No I18N
	}

	// {@code GET /mandates/notifications/{mandate_notification_id}}.
	public MandateNotification getNotification(String mandateNotificationId)
	{
		if (mandateNotificationId == null || mandateNotificationId.isEmpty())
		{
			throw new IllegalArgumentException("mandateNotificationId is required"); //No I18N
		}
		return client.get("/mandates/notifications/" + ZohoHttpClient.encodePath(mandateNotificationId), //No I18N
				MandateNotification.class, "mandate_notification"); //No I18N
	}

	// {@code GET /mandates/{mandate_id}}.
	public Mandate get(String mandateId)
	{
		if (mandateId == null || mandateId.isEmpty())
		{
			throw new IllegalArgumentException("mandateId is required"); //No I18N
		}
		return client.get("/mandates/" + ZohoHttpClient.encodePath(mandateId), //No I18N
				Mandate.class, "mandate"); //No I18N
	}
}
