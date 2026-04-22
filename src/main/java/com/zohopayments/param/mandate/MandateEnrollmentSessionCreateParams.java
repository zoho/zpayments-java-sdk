package com.zohopayments.param.mandate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zohopayments.param.MetaDataParams;
import com.zohopayments.param.MetaDataValidator;
import com.zohopayments.param.ParamValidator;

// Body for {@code POST /paymentsessions} with {@code type = mandate_enrollment}.
public final class MandateEnrollmentSessionCreateParams
{
	private final Double amount;
	private final String currency;
	private final String customerId;
	private final String type;
	private final String description;
	private final String invoiceNumber;
	private final Integer maxRetryCount;
	private final MandateDetailsParams mandateDetails;
	private final List<MetaDataParams> metaData;
	private final MandateConfigurationsParams configurations;

	private MandateEnrollmentSessionCreateParams(Builder b)
	{
		this.amount = b.amount;
		this.currency = b.currency;
		this.customerId = b.customerId;
		this.type = "mandate_enrollment"; //No I18N
		this.description = b.description;
		this.invoiceNumber = b.invoiceNumber;
		this.maxRetryCount = b.maxRetryCount;
		this.mandateDetails = b.mandateDetails;
		this.metaData = b.metaData != null ? Collections.unmodifiableList(new ArrayList<>(b.metaData)) : null;
		this.configurations = b.configurations;
	}

	public Double getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public String getCustomerId() { return customerId; }
	public String getType() { return type; }
	public String getDescription() { return description; }
	public String getInvoiceNumber() { return invoiceNumber; }
	public Integer getMaxRetryCount() { return maxRetryCount; }
	public MandateDetailsParams getMandateDetails() { return mandateDetails; }
	public List<MetaDataParams> getMetaData() { return metaData; }
	public MandateConfigurationsParams getConfigurations() { return configurations; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private Double amount;
		private String currency;
		private String customerId;
		private String description;
		private String invoiceNumber;
		private Integer maxRetryCount;
		private MandateDetailsParams mandateDetails;
		private List<MetaDataParams> metaData;
		private MandateConfigurationsParams configurations;

		private Builder() {}

		public Builder amount(Double amount) { this.amount = amount; return this; }
		public Builder currency(String currency) { this.currency = currency; return this; }
		public Builder customerId(String customerId) { this.customerId = customerId; return this; }
		public Builder description(String description) { this.description = description; return this; }
		public Builder invoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; return this; }
		public Builder maxRetryCount(Integer maxRetryCount) { this.maxRetryCount = maxRetryCount; return this; }
		public Builder mandateDetails(MandateDetailsParams mandateDetails) { this.mandateDetails = mandateDetails; return this; }
		public Builder metaData(List<MetaDataParams> metaData) { this.metaData = metaData; return this; }
		public Builder configurations(MandateConfigurationsParams configurations) { this.configurations = configurations; return this; }

		public MandateEnrollmentSessionCreateParams build()
		{
			if (amount == null)
			{
				throw new IllegalArgumentException("amount is required"); //No I18N
			}
			if (currency == null || currency.isEmpty())
			{
				throw new IllegalArgumentException("currency is required"); //No I18N
			}
			if (customerId == null || customerId.isEmpty())
			{
				throw new IllegalArgumentException("customerId is required"); //No I18N
			}
			if (description == null || description.isEmpty())
			{
				throw new IllegalArgumentException("description is required"); //No I18N
			}
			if (mandateDetails == null)
			{
				throw new IllegalArgumentException("mandateDetails is required"); //No I18N
			}
			if (maxRetryCount != null && (maxRetryCount < 1 || maxRetryCount > 3))
			{
				throw new IllegalArgumentException("maxRetryCount must be between 1 and 3"); //No I18N
			}
			ParamValidator.validateDescription(description);
			ParamValidator.validateInvoiceNumber(invoiceNumber);
			MetaDataValidator.validate(metaData);
			return new MandateEnrollmentSessionCreateParams(this);
		}
	}
}
