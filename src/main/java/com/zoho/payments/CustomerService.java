package com.zoho.payments;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.zoho.payments.model.customer.Customer;
import com.zoho.payments.model.customer.CustomerSummary;
import com.zoho.payments.model.ListResponse;
import com.zoho.payments.param.customer.CustomerCreateParams;
import com.zoho.payments.param.customer.CustomerListParams;

import java.lang.reflect.Type;
import java.util.List;

// Zoho Payments Customers API ({@code /customers}).
public final class CustomerService
{
	private static final Type CUSTOMER_LIST_TYPE = new TypeToken<List<CustomerSummary>>() {}.getType();

	private final ZohoHttpClient client;
	private final Edition edition;

	CustomerService(ZohoHttpClient client, Edition edition)
	{
		this.client = client;
		this.edition = edition;
	}

	public Customer create(CustomerCreateParams params)
	{
		if (params == null)
		{
			throw new IllegalArgumentException("params is required"); //No I18N
		}
		JsonObject body = JsonUtil.gson().toJsonTree(params).getAsJsonObject();
		return client.post("/customers", body, Customer.class, "customer"); //No I18N
	}

	public Customer get(String customerId)
	{
		if (customerId == null || customerId.isEmpty())
		{
			throw new IllegalArgumentException("customerId is required"); //No I18N
		}
		return client.get("/customers/" + ZohoHttpClient.encodePath(customerId), //No I18N
				Customer.class, "customer"); //No I18N
	}

	public ListResponse<CustomerSummary> list()
	{
		return list((CustomerListParams) null);
	}

	public ListResponse<CustomerSummary> list(CustomerListParams params)
	{
		if (!edition.isUS())
		{
			throw new UnsupportedOperationException("Customer list is only available for Edition.US"); //No I18N
		}
		QueryParams q = null;
		if (params != null)
		{
			q = new QueryParams()
					.add("filter_by", params.getFilterBy()) //No I18N
					.add("from_date", params.getFromDate()) //No I18N
					.add("to_date", params.getToDate()) //No I18N
					.add("per_page", params.getPerPage()) //No I18N
					.add("page", params.getPage()); //No I18N
		}
		return client.list("/customers", q, CUSTOMER_LIST_TYPE, "customers"); //No I18N
	}

	// Requires {@link Edition#US}.
	public void delete(String customerId)
	{
		if (!edition.isUS())
		{
			throw new UnsupportedOperationException("Customer delete is only available for Edition.US"); //No I18N
		}
		if (customerId == null || customerId.isEmpty())
		{
			throw new IllegalArgumentException("customerId is required"); //No I18N
		}
		client.delete("/customers/" + ZohoHttpClient.encodePath(customerId)); //No I18N
	}
}
