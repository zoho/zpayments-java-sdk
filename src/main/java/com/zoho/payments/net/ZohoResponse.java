package com.zoho.payments.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ZohoResponse
{
	private final int statusCode;
	private final Map<String, List<String>> headers;
	private final String body;

	public ZohoResponse(int statusCode, Map<String, List<String>> headers, String body)
	{
		this.statusCode = statusCode;
		this.headers = headers != null ? deepUnmodifiable(headers) : Collections.emptyMap();
		this.body = body;
	}

	public int getStatusCode() { return statusCode; }

	// Returns an unmodifiable view of the response headers.
	public Map<String, List<String>> getHeaders() { return headers; }

	public String getBody() { return body; }

	private static Map<String, List<String>> deepUnmodifiable(Map<String, List<String>> src)
	{
		Map<String, List<String>> safe = new LinkedHashMap<>(src.size());
		for (Map.Entry<String, List<String>> e : src.entrySet())
		{
			safe.put(e.getKey(), Collections.unmodifiableList(new ArrayList<>(e.getValue())));
		}
		return Collections.unmodifiableMap(safe);
	}
}
