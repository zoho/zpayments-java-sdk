package com.zoho.payments;

public final class ZohoPaymentsClient implements AutoCloseable
{
	private final Edition edition;
	private final TokenManager tokenManager;
	private final ZohoHttpClient httpClient;

	private final PaymentLinkService paymentLinks;
	private final PaymentSessionService paymentSessions;
	private final CustomerService customers;
	private final PaymentService payments;
	private final RefundService refunds;

	private final PaymentMethodSessionService paymentMethodSessions;
	private final PaymentMethodService paymentMethods;
	private final MandateService mandates;
	private final CollectService collect;

	ZohoPaymentsClient(ZohoHttpClient httpClient, TokenManager tokenManager, Edition edition)
	{
		this.edition = edition;
		this.tokenManager = tokenManager;
		this.httpClient = httpClient;

		this.paymentLinks = new PaymentLinkService(httpClient);
		this.paymentSessions = new PaymentSessionService(httpClient);
		this.customers = new CustomerService(httpClient, edition);
		this.payments = new PaymentService(httpClient, edition);
		this.refunds = new RefundService(httpClient);

		this.mandates = new MandateService(httpClient);
		this.collect = new CollectService(httpClient);
		this.paymentMethods = new PaymentMethodService(httpClient);
		this.paymentMethodSessions = new PaymentMethodSessionService(httpClient);
	}

	/**
	 * Mandates API ({@code /mandates}, mandate payment sessions).
	 * Requires {@link Edition#IN}.
	 *
	 * @throws UnsupportedOperationException if this client was built with {@link Edition#US}
	 */
	public MandateService mandates()
	{
		if (!edition.isIN())
		{
			throw new UnsupportedOperationException("MandateService is only available for Edition.IN"); //No I18N
		}
		return mandates;
	}

	/**
	 * Collect — virtual accounts API ({@code /virtualaccounts}).
	 * Requires {@link Edition#IN}.
	 *
	 * @throws UnsupportedOperationException if this client was built with {@link Edition#US}
	 */
	public CollectService collect()
	{
		if (!edition.isIN())
		{
			throw new UnsupportedOperationException("CollectService is only available for Edition.IN"); //No I18N
		}
		return collect;
	}

	/**
	 * Saved payment methods ({@code /paymentmethods}).
	 * Requires {@link Edition#US}.
	 *
	 * @throws UnsupportedOperationException if this client was built with {@link Edition#IN}
	 */
	public PaymentMethodService paymentMethods()
	{
		if (!edition.isUS())
		{
			throw new UnsupportedOperationException("PaymentMethodService is only available for Edition.US"); //No I18N
		}
		return paymentMethods;
	}

	/**
	 * Payment method sessions ({@code /paymentmethodsessions}).
	 * Requires {@link Edition#US}.
	 *
	 * @throws UnsupportedOperationException if this client was built with {@link Edition#IN}
	 */
	public PaymentMethodSessionService paymentMethodSessions()
	{
		if (!edition.isUS())
		{
			throw new UnsupportedOperationException("PaymentMethodSessionService is only available for Edition.US"); //No I18N
		}
		return paymentMethodSessions;
	}

	/**
	 * Replaces the active access token for all subsequent API requests.
	 * Thread-safe: in-flight requests complete with the old token.
	 */
	public void updateToken(String newAccessToken)
	{
		tokenManager.updateToken(newAccessToken);
	}

	// Payment links API ({@code /paymentlinks}). Available on all editions.
	public PaymentLinkService paymentLinks() { return paymentLinks; }

	// Payment sessions API ({@code /paymentsessions}). Available on all editions.
	public PaymentSessionService paymentSessions() { return paymentSessions; }

	// Customers API ({@code /customers}). Available on all editions.
	public CustomerService customers() { return customers; }

	// Payments API ({@code /payments}). Available on all editions.
	public PaymentService payments() { return payments; }

	// Refunds API ({@code /refunds}). Available on all editions.
	public RefundService refunds() { return refunds; }

	/**
	 * Releases resources held by the underlying HTTP transport. Idempotent;
	 * subsequent API calls on this client throw {@link IllegalStateException}.
	 */
	@Override
	public void close()
	{
		httpClient.close();
	}
}
