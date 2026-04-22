package com.zohopayments.model.virtualaccount;

import java.util.Collections;
import java.util.List;

import com.zohopayments.model.MetaData;
// Virtual account (Collect) resource.
public final class VirtualAccount
{
	private String virtualAccountId;
	private String accountNumber;
	private String ifscCode;
	private String beneficiaryName;
	private String description;
	private String customerId;
	private String referenceNumber;
	private String status;
	private String expiresAt;
	private Long createdTime;
	private Long lastModifiedTime;
	private List<MetaData> metaData;
	private Double minimumAmount;
	private Double maximumAmount;
	private Double amountPaid;

	VirtualAccount() {}

	public String getVirtualAccountId() { return virtualAccountId; }
	public String getAccountNumber() { return accountNumber; }
	public String getIfscCode() { return ifscCode; }
	public String getBeneficiaryName() { return beneficiaryName; }
	public String getDescription() { return description; }
	public String getCustomerId() { return customerId; }
	public String getReferenceNumber() { return referenceNumber; }
	public String getStatus() { return status; }
	public String getExpiresAt() { return expiresAt; }
	public Long getCreatedTime() { return createdTime; }
	public Long getLastModifiedTime() { return lastModifiedTime; }

	public List<MetaData> getMetaData()
	{
		return metaData != null ? Collections.unmodifiableList(metaData) : Collections.emptyList();
	}

	public Double getMinimumAmount() { return minimumAmount; }
	public Double getMaximumAmount() { return maximumAmount; }
	public Double getAmountPaid() { return amountPaid; }
}
