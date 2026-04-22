package com.zohopayments.model.customer;

import com.zohopayments.CustomerService;

/**
 * Customer resource returned in list responses ({@code GET /customers}).
 *
 * <p>This is a summary view returned by the list API only. Field names use the
 * {@code customer_*} prefix as returned by the API (e.g. {@code customer_name},
 * {@code customer_email}), which differs from the unprefixed names on the full
 * {@link Customer} retrieve response.
 *
 * <p>For the complete customer object including metadata and saved payment methods, use
 * {@link CustomerService#get(String)}.
 */
public final class CustomerSummary
{
	private String customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private String customerStatus;
	private Long createdTime;
	private Long lastModifiedTime;

	CustomerSummary() {}

	public String getCustomerId() { return customerId; }
	public String getCustomerName() { return customerName; }
	public String getCustomerEmail() { return customerEmail; }
	public String getCustomerPhone() { return customerPhone; }
	public String getCustomerStatus() { return customerStatus; }
	public Long getCreatedTime() { return createdTime; }
	public Long getLastModifiedTime() { return lastModifiedTime; }
}
