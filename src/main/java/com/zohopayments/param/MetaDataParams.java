package com.zohopayments.param;

import com.zohopayments.model.MetaData;

/**
 * Key-value metadata attached to a request body.
 *
 * <p>Used in create/update request payloads. On the response side, see
 * {@link MetaData}.
 *
 * <p>Maximum 5 entries per request; keys up to 20 characters, values up to 500 characters.
 */
public final class MetaDataParams
{
	private final String key;
	private final String value;

	public MetaDataParams(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	public String getKey() { return key; }
	public String getValue() { return value; }
}
