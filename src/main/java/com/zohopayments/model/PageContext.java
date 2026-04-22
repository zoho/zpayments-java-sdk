package com.zohopayments.model;

/**
 * Pagination metadata returned in list API responses.
 *
 * <p>All fields use primitives — missing/null values from the API deserialize to safe defaults
 * ({@code 0} for counts, {@code false} for {@link #hasMorePage()}) rather than {@code null},
 * so callers never need to null-check pagination state.
 */
public final class PageContext
{
	PageContext() {}

	private int page;
	private int perPage;
	private int total;
	private int totalPages;
	private boolean hasMorePage;

	public int getPage() { return page; }
	public int getPerPage() { return perPage; }
	public int getTotal() { return total; }
	public int getTotalPages() { return totalPages; }
	public boolean hasMorePage() { return hasMorePage; }
}
