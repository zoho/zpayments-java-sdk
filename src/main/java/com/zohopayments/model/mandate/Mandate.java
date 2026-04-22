package com.zohopayments.model.mandate;

// A recurring mandate resource.
public final class Mandate
{
	private String mandateId;
	private String customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private String amount;
	private String currency;
	private String amountRule;
	private String frequency;
	private Integer debitDay;
	private String debitRule;
	private Long startDate;
	private Long endDate;
	private String status;
	private String description;
	private MandatePaymentMethod paymentMethod;

	Mandate() {}

	public String getMandateId() { return mandateId; }
	public String getCustomerId() { return customerId; }
	public String getCustomerName() { return customerName; }
	public String getCustomerEmail() { return customerEmail; }
	public String getCustomerPhone() { return customerPhone; }
	public String getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public String getAmountRule() { return amountRule; }
	public String getFrequency() { return frequency; }
	public Integer getDebitDay() { return debitDay; }
	public String getDebitRule() { return debitRule; }
	public Long getStartDate() { return startDate; }
	public Long getEndDate() { return endDate; }
	public String getStatus() { return status; }
	public String getDescription() { return description; }
	public MandatePaymentMethod getPaymentMethod() { return paymentMethod; }
}
