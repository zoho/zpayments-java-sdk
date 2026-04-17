package com.zoho.payments.model.payment;

import java.util.Collections;
import java.util.List;

import com.zoho.payments.model.MetaData;
import com.zoho.payments.model.paymentmethod.PaymentMethodDetail;

// A payment transaction resource (retrieve response).
public final class Payment
{
	private String paymentId;
	private String phone;
	private String amount;
	private String currency;
	private String paymentsSessionId;
	private String receiptEmail;
	private String referenceNumber;
	private String transactionReferenceNumber;
	private String invoiceNumber;
	private String amountCaptured;
	private String amountRefunded;
	private String feeAmount;
	private String netTaxAmount;
	private String totalFeeAmount;
	private String netAmount;
	private String status;
	private Double exchangeRate;
	private String statementDescriptor;
	private String description;
	private Long date;
	private PaymentMethodDetail paymentMethod;
	private List<MetaData> metaData;

	Payment() {}

	public String getPaymentId() { return paymentId; }
	public String getPhone() { return phone; }
	public String getAmount() { return amount; }
	public String getCurrency() { return currency; }
	public String getPaymentsSessionId() { return paymentsSessionId; }
	public String getReceiptEmail() { return receiptEmail; }
	public String getReferenceNumber() { return referenceNumber; }
	public String getTransactionReferenceNumber() { return transactionReferenceNumber; }
	public String getInvoiceNumber() { return invoiceNumber; }
	public String getAmountCaptured() { return amountCaptured; }
	public String getAmountRefunded() { return amountRefunded; }
	public String getFeeAmount() { return feeAmount; }
	public String getNetTaxAmount() { return netTaxAmount; }
	public String getTotalFeeAmount() { return totalFeeAmount; }
	public String getNetAmount() { return netAmount; }
	public String getStatus() { return status; }
	public Double getExchangeRate() { return exchangeRate; }
	public String getStatementDescriptor() { return statementDescriptor; }
	public String getDescription() { return description; }
	public Long getDate() { return date; }
	public PaymentMethodDetail getPaymentMethod() { return paymentMethod; }

	public List<MetaData> getMetaData()
	{
		return metaData != null ? Collections.unmodifiableList(metaData) : Collections.emptyList();
	}
}
