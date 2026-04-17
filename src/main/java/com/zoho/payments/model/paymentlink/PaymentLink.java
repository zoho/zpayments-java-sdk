package com.zoho.payments.model.paymentlink;

import java.util.Collections;
import java.util.List;

import com.zoho.payments.model.Configurations;

// Represents a Zoho Payments payment link resource.
public final class PaymentLink
{
	private String paymentLinkId;
	private String url;
	private String expiresAt;
	private String amount;
	private String amountPaid;
	private String currency;
	private String status;
	private String email;
	private String referenceId;
	private String description;
	private String returnUrl;
	private String phone;
	private String phoneCountryCode;
	private Long createdTime;
	private String createdById;
	private String createdBy;
	private String lastModifiedById;
	private String lastModified;
	private Configurations configurations;
	private List<PaymentLinkPayment> payments;

	PaymentLink() {}

	public String getPaymentLinkId() { return paymentLinkId; }
	public String getUrl() { return url; }
	public String getExpiresAt() { return expiresAt; }
	public String getAmount() { return amount; }
	public String getAmountPaid() { return amountPaid; }
	public String getCurrency() { return currency; }
	public String getStatus() { return status; }
	public String getEmail() { return email; }
	public String getReferenceId() { return referenceId; }
	public String getDescription() { return description; }
	public String getReturnUrl() { return returnUrl; }
	public String getPhone() { return phone; }
	public String getPhoneCountryCode() { return phoneCountryCode; }
	public Long getCreatedTime() { return createdTime; }
	public String getCreatedById() { return createdById; }
	public String getCreatedBy() { return createdBy; }
	public String getLastModifiedById() { return lastModifiedById; }
	public String getLastModified() { return lastModified; }
	public Configurations getConfigurations() { return configurations; }

	public List<PaymentLinkPayment> getPayments()
	{
		return payments != null ? Collections.unmodifiableList(payments) : Collections.emptyList();
	}

	// Summary of a payment associated with this payment link.
	public static final class PaymentLinkPayment
	{
		private String paymentId;
		private String amount;
		private String type;
		private String status;
		private Long date;

		PaymentLinkPayment() {}

		public String getPaymentId() { return paymentId; }
		public String getAmount() { return amount; }
		public String getType() { return type; }
		public String getStatus() { return status; }
		public Long getDate() { return date; }
	}
}
