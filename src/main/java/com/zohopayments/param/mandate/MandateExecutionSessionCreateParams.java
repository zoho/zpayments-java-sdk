package com.zohopayments.param.mandate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zohopayments.param.MetaDataParams;
import com.zohopayments.param.MetaDataValidator;
import com.zohopayments.param.ParamValidator;
/**
 * Body for {@code POST /paymentsessions} with {@code type = mandate_execution}.
 *
 * <p>{@code invoice_number} is required and links the execution to a specific invoice.
 */
public final class MandateExecutionSessionCreateParams
{
	private final Double amount;
	private final String currency;
	private final String customerId;
	private final String type;
	private final String description;
	private final String invoiceNumber;
	private final Integer maxRetryCount;
	private final List<MetaDataParams> metaData;

	private MandateExecutionSessionCreateParams(Builder b)
	{
		this.amount = b.amount;
		this.currency = b.currency;
		this.customerId = b.customerId;
		this.type = "mandate_execution"; //No I18N
		this.description = b.description;
		this.invoiceNumber = b.invoiceNumber;
		this.maxRetryCount = b.maxRetryCount;
		this.metaData = b.metaData != null ? Collections.unmodifiableList(new ArrayList<>(b.metaData)) : null;
	}

	public Double getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public String getCustomerId() { return customerId; }
	public String getType() { return type; }
	public String getDescription() { return description; }
	public String getInvoiceNumber() { return invoiceNumber; }
	public Integer getMaxRetryCount() { return maxRetryCount; }
	public List<MetaDataParams> getMetaData() { return metaData; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private Double amount;
		private String currency;
		private String customerId;
		private String description;
		private String invoiceNumber;
		private Integer maxRetryCount;
		private List<MetaDataParams> metaData;

		private Builder() {}

		public Builder amount(Double amount) { this.amount = amount; return this; }
		public Builder currency(String currency) { this.currency = currency; return this; }
		public Builder customerId(String customerId) { this.customerId = customerId; return this; }
		public Builder description(String description) { this.description = description; return this; }
		public Builder invoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; return this; }
		public Builder maxRetryCount(Integer maxRetryCount) { this.maxRetryCount = maxRetryCount; return this; }
		public Builder metaData(List<MetaDataParams> metaData) { this.metaData = metaData; return this; }

		public MandateExecutionSessionCreateParams build()
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
			if (invoiceNumber == null || invoiceNumber.isEmpty())
			{
				throw new IllegalArgumentException("invoiceNumber is required"); //No I18N
			}
			if (maxRetryCount != null && (maxRetryCount < 1 || maxRetryCount > 3))
			{
				throw new IllegalArgumentException("maxRetryCount must be between 1 and 3"); //No I18N
			}
			ParamValidator.validateDescription(description);
			ParamValidator.validateInvoiceNumber(invoiceNumber);
			MetaDataValidator.validate(metaData);
			return new MandateExecutionSessionCreateParams(this);
		}
	}
}
