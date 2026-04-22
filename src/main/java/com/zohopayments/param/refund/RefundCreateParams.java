package com.zohopayments.param.refund;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zohopayments.param.MetaDataParams;
import com.zohopayments.param.MetaDataValidator;
import com.zohopayments.param.ParamValidator;
// Body for {@code POST /payments/{payment_id}/refunds}.
public final class RefundCreateParams
{
	private final Double amount;
	private final String reason;
	private final String type;
	private final String description;
	private final List<MetaDataParams> metaData;

	private RefundCreateParams(Builder b)
	{
		this.amount = b.amount;
		this.reason = b.reason;
		this.type = b.type;
		this.description = b.description;
		this.metaData = b.metaData != null ? Collections.unmodifiableList(new ArrayList<>(b.metaData)) : null;
	}

	public Double getAmount() { return amount; }
	public String getReason() { return reason; }
	public String getType() { return type; }
	public String getDescription() { return description; }
	public List<MetaDataParams> getMetaData() { return metaData; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private Double amount;
		private String reason;
		private String type;
		private String description;
		private List<MetaDataParams> metaData;

		private Builder() {}

		public Builder amount(Double amount) { this.amount = amount; return this; }
		public Builder reason(String reason) { this.reason = reason; return this; }
		public Builder type(String type) { this.type = type; return this; }
		public Builder description(String description) { this.description = description; return this; }
		public Builder metaData(List<MetaDataParams> metaData) { this.metaData = metaData; return this; }

		public RefundCreateParams build()
		{
			if (amount == null)
			{
				throw new IllegalArgumentException("amount is required"); //No I18N
			}
			if (reason == null || reason.isEmpty())
			{
				throw new IllegalArgumentException("reason is required"); //No I18N
			}
			if (type == null || type.isEmpty())
			{
				throw new IllegalArgumentException("type is required"); //No I18N
			}
			ParamValidator.validateDescription(description);
			MetaDataValidator.validate(metaData);
			return new RefundCreateParams(this);
		}
	}
}
