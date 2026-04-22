package com.zohopayments.net;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// Immutable value object representing an outbound HTTP request.
public final class ZohoRequest
{
	private final RequestMethod method;
	private final String url;
	private final Map<String, List<String>> headers;
	private final String body;
	private final Duration timeout;

	private ZohoRequest(RequestMethod method, String url, Map<String, List<String>> headers,
			String body, Duration timeout)
	{
		this.method = method;
		this.url = url;
		this.headers = deepUnmodifiable(headers);
		this.body = body;
		this.timeout = timeout;
	}

	public RequestMethod getMethod() { return method; }
	public String getUrl() { return url; }

	// Returns an unmodifiable view of the request headers.
	public Map<String, List<String>> getHeaders() { return headers; }
	public String getBody() { return body; }
	public Duration getTimeout() { return timeout; }

	public static Builder builder() { return new Builder(); }

	public static final class Builder
	{
		private RequestMethod method;
		private String url;
		private final Map<String, List<String>> headers = new LinkedHashMap<>();
		private String body;
		private Duration timeout;

		private Builder() {}

		public Builder method(RequestMethod method) { this.method = method; return this; }
		public Builder url(String url) { this.url = url; return this; }

		// Appends {@code value} to the list of values for {@code name}.
		public Builder header(String name, String value)
		{
			this.headers.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
			return this;
		}

		// Replaces all existing values for {@code name} with a single {@code value}.
		public Builder setHeader(String name, String value)
		{
			List<String> list = new ArrayList<>();
			list.add(value);
			this.headers.put(name, list);
			return this;
		}

		// Appends each entry of {@code headers} as a single-value header.
		public Builder headers(Map<String, String> headers)
		{
			headers.forEach(this::header);
			return this;
		}

		public Builder body(String body) { this.body = body; return this; }
		public Builder timeout(Duration timeout) { this.timeout = timeout; return this; }

		public ZohoRequest build()
		{
			Objects.requireNonNull(method, "method is required"); //No I18N
			Objects.requireNonNull(url, "url is required"); //No I18N
			return new ZohoRequest(method, url, headers, body, timeout);
		}
	}

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
