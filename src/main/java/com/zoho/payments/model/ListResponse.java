package com.zoho.payments.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generic wrapper for paginated list API responses.
 *
 * @param <T> the type of resource in the list
 */
public final class ListResponse<T>
{
	private final List<T> data;
	private final PageContext pageContext;

	public ListResponse(List<T> data, PageContext pageContext)
	{
		this.data = data != null ? Collections.unmodifiableList(new ArrayList<>(data)) : Collections.emptyList();
		this.pageContext = pageContext;
	}

	public List<T> getData() { return data; }
	public PageContext getPageContext() { return pageContext; }
}
