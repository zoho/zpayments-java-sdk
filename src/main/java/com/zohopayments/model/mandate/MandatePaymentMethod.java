package com.zohopayments.model.mandate;

// Payment method snapshot in Mandate and Mandate Notification responses (UPI only).
public final class MandatePaymentMethod
{
	private String type;
	private Upi upi;

	MandatePaymentMethod() {}

	public String getType() { return type; }
	public Upi getUpi() { return upi; }

	public static final class Upi
	{
		private String upiId;

		Upi() {}

		public String getUpiId() { return upiId; }
	}
}
