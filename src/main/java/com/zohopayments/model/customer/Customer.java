package com.zohopayments.model.customer;

import java.util.Collections;
import java.util.List;

import com.zohopayments.model.MetaData;
/**
 * A customer in Zoho Payments.
 *
 * <p>Populated by create ({@code POST /customers}) and retrieve ({@code GET /customers/{id}})
 * responses. For the trimmed view returned by the list API, see {@link CustomerSummary}.
 *
 * <p>{@link #getPaymentMethods()} is populated on US edition retrieve responses only.
 */
public final class Customer
{
	private String customerId;
	private String name;
	private String email;
	private String phone;
	private String dialingCode;
	private Long createdTime;
	private Long lastModifiedTime;
	private List<MetaData> metaData;

	/* US retrieve response only */
	private List<CustomerPaymentMethod> paymentMethods;

	Customer() {}

	public String getCustomerId() { return customerId; }
	public String getName() { return name; }
	public String getEmail() { return email; }
	public String getPhone() { return phone; }
	public String getDialingCode() { return dialingCode; }
	public Long getCreatedTime() { return createdTime; }
	public Long getLastModifiedTime() { return lastModifiedTime; }

	public List<MetaData> getMetaData()
	{
		return metaData != null ? Collections.unmodifiableList(metaData) : Collections.emptyList();
	}

	public List<CustomerPaymentMethod> getPaymentMethods()
	{
		return paymentMethods != null ? Collections.unmodifiableList(paymentMethods) : Collections.emptyList();
	}
}
