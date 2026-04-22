package com.zohopayments.param;

// Common pagination fields shared across all list-query parameter classes.
public interface PaginationParams
{
	Integer getPerPage();
	Integer getPage();
}
