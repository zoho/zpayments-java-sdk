package com.zoho.payments.param.paymentlink;

import com.zoho.payments.param.NotifyCustomerParams;
import com.zoho.payments.param.ParamValidator;
/**
 * Parameters for creating a new payment link.
 *
 * <p>Required: {@code amount}, {@code currency}, {@code description}.
 */
public final class PaymentLinkCreateParams
{
	private final Double amount;
	private final String currency;
	private final String description;
	private final String email;
	private final String phone;
	private final String phoneCountryCode;
	private final String expiresAt;
	private final String referenceId;
	private final String returnUrl;
	private final NotifyCustomerParams notifyCustomer;
	private final PaymentLinkConfigurationsParams configurations;

	private PaymentLinkCreateParams(Builder b)
	{
		this.amount = b.amount;
		this.currency = b.currency;
		this.description = b.description;
		this.email = b.email;
		this.phone = b.phone;
		this.phoneCountryCode = b.phoneCountryCode;
		this.expiresAt = b.expiresAt;
		this.referenceId = b.referenceId;
		this.returnUrl = b.returnUrl;
		this.notifyCustomer = b.notifyCustomer;
		this.configurations = b.configurations;
	}

	public Double getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public String getDescription() { return description; }
	public String getEmail() { return email; }
	public String getPhone() { return phone; }
	public String getPhoneCountryCode() { return phoneCountryCode; }
	public String getExpiresAt() { return expiresAt; }
	public String getReferenceId() { return referenceId; }
	public String getReturnUrl() { return returnUrl; }
	public NotifyCustomerParams getNotifyCustomer() { return notifyCustomer; }
	public PaymentLinkConfigurationsParams getConfigurations() { return configurations; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private Double amount;
		private String currency;
		private String description;
		private String email;
		private String phone;
		private String phoneCountryCode;
		private String expiresAt;
		private String referenceId;
		private String returnUrl;
		private NotifyCustomerParams notifyCustomer;
		private PaymentLinkConfigurationsParams configurations;

		private Builder() {}

		public Builder amount(Double amount) { this.amount = amount; return this; }
		public Builder currency(String currency) { this.currency = currency; return this; }
		public Builder description(String description) { this.description = description; return this; }
		public Builder email(String email) { this.email = email; return this; }
		public Builder phone(String phone) { this.phone = phone; return this; }
		public Builder phoneCountryCode(String phoneCountryCode) { this.phoneCountryCode = phoneCountryCode; return this; }
		public Builder expiresAt(String expiresAt) { this.expiresAt = expiresAt; return this; }
		public Builder referenceId(String referenceId) { this.referenceId = referenceId; return this; }
		public Builder returnUrl(String returnUrl) { this.returnUrl = returnUrl; return this; }
		public Builder notifyCustomer(NotifyCustomerParams notifyCustomer) { this.notifyCustomer = notifyCustomer; return this; }
		public Builder configurations(PaymentLinkConfigurationsParams configurations) { this.configurations = configurations; return this; }

		public PaymentLinkCreateParams build()
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
			ParamValidator.validateReferenceNumber(referenceId);
			return new PaymentLinkCreateParams(this);
		}
	}

}
