package com.zoho.payments.param.mandate;

import com.zoho.payments.param.ParamValidator;

// Mandate configuration embedded in a mandate enrollment payment session.
public final class MandateDetailsParams
{
	private final String paymentMethodType;
	private final String frequency;
	private final String description;
	private final String amountRule;
	private final Double maxAmount;
	private final String startDate;
	private final String endDate;
	private final Integer debitDay;
	private final String debitRule;

	private MandateDetailsParams(Builder b)
	{
		this.paymentMethodType = b.paymentMethodType;
		this.frequency = b.frequency;
		this.description = b.description;
		this.amountRule = b.amountRule;
		this.maxAmount = b.maxAmount;
		this.startDate = b.startDate;
		this.endDate = b.endDate;
		this.debitDay = b.debitDay;
		this.debitRule = b.debitRule;
	}

	public String getPaymentMethodType() { return paymentMethodType; }
	public String getFrequency() { return frequency; }
	public String getDescription() { return description; }
	public String getAmountRule() { return amountRule; }
	public Double getMaxAmount() { return maxAmount; }
	public String getStartDate() { return startDate; }
	public String getEndDate() { return endDate; }
	public Integer getDebitDay() { return debitDay; }
	public String getDebitRule() { return debitRule; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private String paymentMethodType;
		private String frequency;
		private String description;
		private String amountRule;
		private Double maxAmount;
		private String startDate;
		private String endDate;
		private Integer debitDay;
		private String debitRule;

		private Builder() {}

		public Builder paymentMethodType(String paymentMethodType) { this.paymentMethodType = paymentMethodType; return this; }
		public Builder frequency(String frequency) { this.frequency = frequency; return this; }
		public Builder description(String description) { this.description = description; return this; }
		public Builder amountRule(String amountRule) { this.amountRule = amountRule; return this; }
		public Builder maxAmount(Double maxAmount) { this.maxAmount = maxAmount; return this; }
		public Builder startDate(String startDate) { this.startDate = startDate; return this; }
		public Builder endDate(String endDate) { this.endDate = endDate; return this; }
		public Builder debitDay(Integer debitDay) { this.debitDay = debitDay; return this; }
		public Builder debitRule(String debitRule) { this.debitRule = debitRule; return this; }

		public MandateDetailsParams build()
		{
			if (paymentMethodType == null || paymentMethodType.isEmpty())
			{
				throw new IllegalArgumentException("paymentMethodType is required"); //No I18N
			}
			if (frequency == null || frequency.isEmpty())
			{
				throw new IllegalArgumentException("frequency is required"); //No I18N
			}
			if (description == null || description.isEmpty())
			{
				throw new IllegalArgumentException("description is required"); //No I18N
			}
			if (amountRule == null || amountRule.isEmpty())
			{
				throw new IllegalArgumentException("amountRule is required"); //No I18N
			}
			if ("variable".equals(amountRule) && maxAmount == null) //No I18N
			{
				throw new IllegalArgumentException("maxAmount is required when amountRule is variable"); //No I18N
			}
			ParamValidator.validateDescription(description);
			return new MandateDetailsParams(this);
		}
	}
}
