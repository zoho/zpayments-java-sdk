package com.zoho.payments.model;

import com.zoho.payments.param.MetaDataParams;

/**
 * Key-value metadata returned on a resource.
 *
 * <p>Response-only type. For request payloads, use
 * {@link MetaDataParams}.
 */
public final class MetaData
{
	private String key;
	private String value;

	MetaData() {}

	public String getKey() { return key; }
	public String getValue() { return value; }
}
