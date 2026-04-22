package com.zohopayments.param.payment;

import com.zohopayments.param.PaginationParams;

// Query parameters for {@code GET /payments}.
public final class PaymentListParams implements PaginationParams
{
	private final String status;
	private final String searchText;
	private final String filterBy;
	private final String fromDate;
	private final String toDate;
	private final String paymentMethodType;
	private final Integer perPage;
	private final Integer page;

	private PaymentListParams(Builder b)
	{
		this.status = b.status;
		this.searchText = b.searchText;
		this.filterBy = b.filterBy;
		this.fromDate = b.fromDate;
		this.toDate = b.toDate;
		this.paymentMethodType = b.paymentMethodType;
		this.perPage = b.perPage;
		this.page = b.page;
	}

	public String getStatus() { return status; }
	public String getSearchText() { return searchText; }
	public String getFilterBy() { return filterBy; }
	public String getFromDate() { return fromDate; }
	public String getToDate() { return toDate; }
	public String getPaymentMethodType() { return paymentMethodType; }
	public Integer getPerPage() { return perPage; }
	public Integer getPage() { return page; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private String status;
		private String searchText;
		private String filterBy;
		private String fromDate;
		private String toDate;
		private String paymentMethodType;
		private Integer perPage;
		private Integer page;

		private Builder() {}

		public Builder status(String status) { this.status = status; return this; }
		public Builder searchText(String searchText) { this.searchText = searchText; return this; }
		public Builder filterBy(String filterBy) { this.filterBy = filterBy; return this; }
		public Builder fromDate(String fromDate) { this.fromDate = fromDate; return this; }
		public Builder toDate(String toDate) { this.toDate = toDate; return this; }
		public Builder paymentMethodType(String paymentMethodType) { this.paymentMethodType = paymentMethodType; return this; }
		public Builder perPage(Integer perPage) { this.perPage = perPage; return this; }
		public Builder page(Integer page) { this.page = page; return this; }

		public PaymentListParams build()
		{
			return new PaymentListParams(this);
		}
	}
}
