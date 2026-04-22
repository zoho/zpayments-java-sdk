package com.zohopayments.param.virtualaccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zohopayments.param.MetaDataParams;
import com.zohopayments.param.MetaDataValidator;
import com.zohopayments.param.ParamValidator;

// Body for {@code POST /virtualaccounts}.
public final class VirtualAccountCreateParams
{
	private final String description;
	private final String customerId;
	private final Double minimumAmount;
	private final Double maximumAmount;
	private final String expiresAt;
	private final String referenceNumber;
	private final List<MetaDataParams> metaData;

	private VirtualAccountCreateParams(Builder b)
	{
		this.description = b.description;
		this.customerId = b.customerId;
		this.minimumAmount = b.minimumAmount;
		this.maximumAmount = b.maximumAmount;
		this.expiresAt = b.expiresAt;
		this.referenceNumber = b.referenceNumber;
		this.metaData = b.metaData != null ? Collections.unmodifiableList(new ArrayList<>(b.metaData)) : null;
	}

	public String getDescription() { return description; }
	public String getCustomerId() { return customerId; }
	public Double getMinimumAmount() { return minimumAmount; }
	public Double getMaximumAmount() { return maximumAmount; }
	public String getExpiresAt() { return expiresAt; }
	public String getReferenceNumber() { return referenceNumber; }
	public List<MetaDataParams> getMetaData() { return metaData; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private String description;
		private String customerId;
		private Double minimumAmount;
		private Double maximumAmount;
		private String expiresAt;
		private String referenceNumber;
		private List<MetaDataParams> metaData;

		private Builder() {}

		public Builder description(String description) { this.description = description; return this; }
		public Builder customerId(String customerId) { this.customerId = customerId; return this; }
		public Builder minimumAmount(Double minimumAmount) { this.minimumAmount = minimumAmount; return this; }
		public Builder maximumAmount(Double maximumAmount) { this.maximumAmount = maximumAmount; return this; }
		public Builder expiresAt(String expiresAt) { this.expiresAt = expiresAt; return this; }
		public Builder referenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; return this; }
		public Builder metaData(List<MetaDataParams> metaData) { this.metaData = metaData; return this; }

		public VirtualAccountCreateParams build()
		{
			if (description == null || description.isEmpty())
			{
				throw new IllegalArgumentException("description is required"); //No I18N
			}
			ParamValidator.validateDescription(description);
			ParamValidator.validateReferenceNumber(referenceNumber);
			MetaDataValidator.validate(metaData);
			return new VirtualAccountCreateParams(this);
		}
	}
}
