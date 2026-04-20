package com.zoho.payments;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

// URL query string builder. A {@code null} value is silently omitted.
final class QueryParams
{
	private final Map<String, String> params = new LinkedHashMap<>();

	QueryParams add(String key, String value)
	{
		Objects.requireNonNull(key, "query parameter key must not be null"); //No I18N
		if (value != null)
		{
			params.put(key, value);
		}
		return this;
	}

	QueryParams add(String key, Long value)
	{
		Objects.requireNonNull(key, "query parameter key must not be null"); //No I18N
		if (value != null)
		{
			params.put(key, value.toString());
		}
		return this;
	}

	QueryParams add(String key, Integer value)
	{
		Objects.requireNonNull(key, "query parameter key must not be null"); //No I18N
		if (value != null)
		{
			params.put(key, value.toString());
		}
		return this;
	}

	QueryParams add(String key, Boolean value)
	{
		Objects.requireNonNull(key, "query parameter key must not be null"); //No I18N
		if (value != null)
		{
			params.put(key, value.toString());
		}
		return this;
	}

	QueryParams addAll(QueryParams other)
	{
		if (other != null)
		{
			params.putAll(other.params);
		}
		return this;
	}

	String toQueryString()
	{
		if (params.isEmpty())
		{
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet())
		{
			if (sb.length() > 0)
			{
				sb.append("&"); //No I18N
			}
			sb.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
			sb.append("="); //No I18N
			sb.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
		}
		return sb.toString();
	}

	boolean isEmpty() { return params.isEmpty(); }
}
