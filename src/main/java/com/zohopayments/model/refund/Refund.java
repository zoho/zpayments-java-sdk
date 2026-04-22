package com.zohopayments.model.refund;

import java.util.Collections;
import java.util.List;

import com.zohopayments.model.MetaData;

// Refund resource.
public final class Refund
{
	private String refundId;
	private String paymentId;
	private String referenceNumber;
	private String amount;
	private String defaultCurrencyAmount;
	private String type;
	private String reason;
	private String description;
	private String status;
	private String networkReferenceNumber;
	private String failureReason;
	private Long date;
	private List<MetaData> metaData;

	Refund() {}

	public String getRefundId() { return refundId; }
	public String getPaymentId() { return paymentId; }
	public String getReferenceNumber() { return referenceNumber; }
	public String getAmount() { return amount; }
	public String getDefaultCurrencyAmount() { return defaultCurrencyAmount; }
	public String getType() { return type; }
	public String getReason() { return reason; }
	public String getDescription() { return description; }
	public String getStatus() { return status; }
	public String getNetworkReferenceNumber() { return networkReferenceNumber; }
	public String getFailureReason() { return failureReason; }
	public Long getDate() { return date; }

	public List<MetaData> getMetaData()
	{
		return metaData != null ? Collections.unmodifiableList(metaData) : Collections.emptyList();
	}
}
