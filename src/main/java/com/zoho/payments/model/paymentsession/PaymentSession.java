package com.zoho.payments.model.paymentsession;

import java.util.Collections;
import java.util.List;

import com.zoho.payments.model.Configurations;
import com.zoho.payments.model.MetaData;

// A payment initiation session ({@code payments_session} object in API responses).
public final class PaymentSession
{
	private String paymentsSessionId;
	private String accessKey;
	private String currency;
	private String amount;
	private String status;
	private Long createdTime;
	private Long expiryTime;
	private List<PaymentSessionPayment> payments;
	private List<MetaData> metaData;
	private String description;
	private String invoiceNumber;
	private String referenceNumber;
	private Integer maxRetryCount;
	private Configurations configurations;

	PaymentSession() {}

	public String getPaymentsSessionId() { return paymentsSessionId; }
	public String getAccessKey() { return accessKey; }
	public String getCurrency() { return currency; }
	public String getAmount() { return amount; }
	public String getStatus() { return status; }
	public Long getCreatedTime() { return createdTime; }
	public Long getExpiryTime() { return expiryTime; }
	public List<PaymentSessionPayment> getPayments()
	{
		return payments != null ? Collections.unmodifiableList(payments) : Collections.emptyList();
	}
	public List<MetaData> getMetaData()
	{
		return metaData != null ? Collections.unmodifiableList(metaData) : Collections.emptyList();
	}
	public String getDescription() { return description; }
	public String getInvoiceNumber() { return invoiceNumber; }
	public String getReferenceNumber() { return referenceNumber; }
	public Integer getMaxRetryCount() { return maxRetryCount; }
	public Configurations getConfigurations() { return configurations; }
}
