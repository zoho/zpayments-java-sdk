package com.zohopayments;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.zohopayments.model.ListResponse;
import com.zohopayments.model.virtualaccount.VirtualAccount;
import com.zohopayments.model.virtualaccount.VirtualAccountPayment;
import com.zohopayments.param.virtualaccount.VirtualAccountCreateParams;
import com.zohopayments.param.virtualaccount.VirtualAccountPaymentListParams;
import com.zohopayments.param.virtualaccount.VirtualAccountUpdateParams;

import java.lang.reflect.Type;
import java.util.List;

// Zoho Payments Collect API — virtual accounts ({@code /virtualaccounts}).
public final class CollectService
{
	private static final Type PAYMENT_LIST_TYPE = new TypeToken<List<VirtualAccountPayment>>() {}.getType();

	private final ZohoHttpClient client;

	CollectService(ZohoHttpClient client)
	{
		this.client = client;
	}

	public VirtualAccount create(VirtualAccountCreateParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/virtualaccounts", body, VirtualAccount.class, "virtual_account"); //No I18N
	}

	public VirtualAccount update(String virtualAccountId, VirtualAccountUpdateParams params)
	{
		if (virtualAccountId == null || virtualAccountId.isEmpty())
		{
			throw new IllegalArgumentException("virtualAccountId is required"); //No I18N
		}
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.put("/virtualaccounts/" + ZohoHttpClient.encodePath(virtualAccountId), //No I18N
				body, VirtualAccount.class, "virtual_account"); //No I18N
	}

	public VirtualAccount get(String virtualAccountId)
	{
		if (virtualAccountId == null || virtualAccountId.isEmpty())
		{
			throw new IllegalArgumentException("virtualAccountId is required"); //No I18N
		}
		return client.get("/virtualaccounts/" + ZohoHttpClient.encodePath(virtualAccountId), //No I18N
				VirtualAccount.class, "virtual_account"); //No I18N
	}

	public ListResponse<VirtualAccountPayment> listPayments(String virtualAccountId)
	{
		return listPayments(virtualAccountId, null);
	}

	public ListResponse<VirtualAccountPayment> listPayments(String virtualAccountId, VirtualAccountPaymentListParams params)
	{
		if (virtualAccountId == null || virtualAccountId.isEmpty())
		{
			throw new IllegalArgumentException("virtualAccountId is required"); //No I18N
		}
		QueryParams q = null;
		if (params != null)
		{
			q = new QueryParams()
					.add("status", params.getStatus()) //No I18N
					.add("per_page", params.getPerPage()) //No I18N
					.add("page", params.getPage()) //No I18N
					.add("sort_column", params.getSortColumn()) //No I18N
					.add("sort_order", params.getSortOrder()); //No I18N
		}
		String path = "/virtualaccounts/" + ZohoHttpClient.encodePath(virtualAccountId) + "/payments"; //No I18N
		return client.list(path, q, PAYMENT_LIST_TYPE, "payments"); //No I18N
	}

	public void close(String virtualAccountId)
	{
		if (virtualAccountId == null || virtualAccountId.isEmpty())
		{
			throw new IllegalArgumentException("virtualAccountId is required"); //No I18N
		}
		client.put("/virtualaccounts/" + ZohoHttpClient.encodePath(virtualAccountId) + "/close"); //No I18N
	}
}
