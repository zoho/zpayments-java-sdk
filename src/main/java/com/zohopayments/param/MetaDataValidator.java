package com.zohopayments.param;

import java.util.List;

/**
 * Shared validation for metadata lists.
 * Used by param builders to enforce API constraints consistently.
 */
public final class MetaDataValidator
{
	private static final int MAX_ENTRIES = 5;
	private static final int MAX_KEY_LENGTH = 20;
	private static final int MAX_VALUE_LENGTH = 500;

	private MetaDataValidator() {}

	public static void validate(List<MetaDataParams> metaData)
	{
		if (metaData == null)
		{
			return;
		}
		if (metaData.size() > MAX_ENTRIES)
		{
			throw new IllegalArgumentException("metaData must have at most " + MAX_ENTRIES + " entries"); //No I18N
		}
		for (MetaDataParams entry : metaData)
		{
			if (entry == null)
			{
				throw new IllegalArgumentException("metaData entries must not be null"); //No I18N
			}
			String k = entry.getKey();
			String v = entry.getValue();
			if (k == null || k.isEmpty())
			{
				throw new IllegalArgumentException("metaData key must not be null or empty"); //No I18N
			}
			if (k.length() > MAX_KEY_LENGTH)
			{
				throw new IllegalArgumentException("metaData key must be at most " + MAX_KEY_LENGTH + " characters"); //No I18N
			}
			if (v != null && v.length() > MAX_VALUE_LENGTH)
			{
				throw new IllegalArgumentException("metaData value must be at most " + MAX_VALUE_LENGTH + " characters"); //No I18N
			}
		}
	}
}
