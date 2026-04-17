package com.zoho.payments.param.mandate;

import com.zoho.payments.param.ParamValidator;

// Body for {@code POST /mandates/notify}.

public final class MandateNotifyParams
{
	private final String mandateId;
	private final Double amount;
	private final String executionDate;
	private final String description;
	private final String invoiceNumber;

	private MandateNotifyParams(Builder b)
	{
		this.mandateId = b.mandateId;
		this.amount = b.amount;
		this.executionDate = b.executionDate;
		this.description = b.description;
		this.invoiceNumber = b.invoiceNumber;
	}

	public String getMandateId() { return mandateId; }
	public Double getAmount() { return amount; }
	public String getExecutionDate() { return executionDate; }
	public String getDescription() { return description; }
	public String getInvoiceNumber() { return invoiceNumber; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private String mandateId;
		private Double amount;
		private String executionDate;
		private String description;
		private String invoiceNumber;

		private Builder() {}

		public Builder mandateId(String mandateId) { this.mandateId = mandateId; return this; }
		public Builder amount(Double amount) { this.amount = amount; return this; }
		public Builder executionDate(String executionDate) { this.executionDate = executionDate; return this; }
		public Builder description(String description) { this.description = description; return this; }
		public Builder invoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; return this; }

		public MandateNotifyParams build()
		{
			if (mandateId == null || mandateId.isEmpty())
			{
				throw new IllegalArgumentException("mandateId is required"); //No I18N
			}
			if (amount == null)
			{
				throw new IllegalArgumentException("amount is required"); //No I18N
			}
			if (executionDate == null || executionDate.isEmpty())
			{
				throw new IllegalArgumentException("executionDate is required"); //No I18N
			}
			if (description == null || description.isEmpty())
			{
				throw new IllegalArgumentException("description is required"); //No I18N
			}
			if (invoiceNumber == null || invoiceNumber.isEmpty())
			{
				throw new IllegalArgumentException("invoiceNumber is required"); //No I18N
			}
			ParamValidator.validateDescription(description);
			ParamValidator.validateInvoiceNumber(invoiceNumber);
			return new MandateNotifyParams(this);
		}
	}
}
