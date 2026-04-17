package com.zoho.payments.param.paymentsession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zoho.payments.param.ConfigurationsParams;
import com.zoho.payments.param.MetaDataParams;
import com.zoho.payments.param.MetaDataValidator;
import com.zoho.payments.param.ParamValidator;

// Request body for {@code POST /paymentsessions}.
public final class PaymentSessionCreateParams
{
	private final Double amount;
	private final String currency;
	private final Integer expiresIn;
	private final List<MetaDataParams> metaData;
	private final String description;
	private final String invoiceNumber;
	private final String referenceNumber;
	private final Integer maxRetryCount;
	private final ConfigurationsParams configurations;

	private PaymentSessionCreateParams(Builder b)
	{
		this.amount = b.amount;
		this.currency = b.currency;
		this.expiresIn = b.expiresIn;
		this.metaData = b.metaData != null ? Collections.unmodifiableList(new ArrayList<>(b.metaData)) : null;
		this.description = b.description;
		this.invoiceNumber = b.invoiceNumber;
		this.referenceNumber = b.referenceNumber;
		this.maxRetryCount = b.maxRetryCount;
		this.configurations = b.configurations;
	}

	public Double getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public Integer getExpiresIn() { return expiresIn; }
	public List<MetaDataParams> getMetaData() { return metaData; }
	public String getDescription() { return description; }
	public String getInvoiceNumber() { return invoiceNumber; }
	public String getReferenceNumber() { return referenceNumber; }
	public Integer getMaxRetryCount() { return maxRetryCount; }
	public ConfigurationsParams getConfigurations() { return configurations; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private Double amount;
		private String currency;
		private Integer expiresIn;
		private List<MetaDataParams> metaData;
		private String description;
		private String invoiceNumber;
		private String referenceNumber;
		private Integer maxRetryCount;
		private ConfigurationsParams configurations;

		private Builder() {}

		public Builder amount(Double amount) { this.amount = amount; return this; }
		public Builder currency(String currency) { this.currency = currency; return this; }
		public Builder expiresIn(Integer expiresIn) { this.expiresIn = expiresIn; return this; }
		public Builder metaData(List<MetaDataParams> metaData) { this.metaData = metaData; return this; }
		public Builder description(String description) { this.description = description; return this; }
		public Builder invoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; return this; }
		public Builder referenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; return this; }
		public Builder maxRetryCount(Integer maxRetryCount) { this.maxRetryCount = maxRetryCount; return this; }
		public Builder configurations(ConfigurationsParams configurations) { this.configurations = configurations; return this; }

		public PaymentSessionCreateParams build()
		{
			if (amount == null)
			{
				throw new IllegalArgumentException("amount is required"); //No I18N
			}
			if (currency == null || currency.isEmpty())
			{
				throw new IllegalArgumentException("currency is required"); //No I18N
			}
			if (description == null || description.isEmpty())
			{
				throw new IllegalArgumentException("description is required"); //No I18N
			}
			ParamValidator.validateDescription(description);
			ParamValidator.validateInvoiceNumber(invoiceNumber);
			ParamValidator.validateReferenceNumber(referenceNumber);
			if (expiresIn != null && (expiresIn < 300 || expiresIn > 900))
			{
				throw new IllegalArgumentException("expiresIn must be between 300 and 900 (seconds)"); //No I18N
			}
			if (maxRetryCount != null && (maxRetryCount < 1 || maxRetryCount > 5))
			{
				throw new IllegalArgumentException("maxRetryCount must be between 1 and 5"); //No I18N
			}
			MetaDataValidator.validate(metaData);
			return new PaymentSessionCreateParams(this);
		}
	}

}
