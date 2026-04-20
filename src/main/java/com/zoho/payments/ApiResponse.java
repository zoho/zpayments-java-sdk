package com.zoho.payments;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// Parsed HTTP response — status code plus a deserialized JSON body.
final class ApiResponse
{
	private final int statusCode;
	private final JsonObject body;

	ApiResponse(int statusCode, JsonObject body)
	{
		this.statusCode = statusCode;
		this.body = body;
	}

	JsonObject getBody() { return body; }

	// Returns the raw {@code code} value from the response body, or {@code null}.
	String getCodeString()
	{
		if (body != null && body.has("code")) //No I18N
		{
			try
			{
				JsonElement codeElement = body.get("code"); //No I18N
				if (codeElement == null || codeElement.isJsonNull())
				{
					return null;
				}
				String raw = codeElement.getAsString();
				if (raw == null)
				{
					return null;
				}
				String trimmed = raw.trim();
				return trimmed.isEmpty() ? null : trimmed;
			}
			catch (IllegalStateException | ClassCastException | UnsupportedOperationException ignored)
			{
				return null;
			}
		}
		return null;
	}

	String getMessage()
	{
		if (body != null && body.has("message")) //No I18N
		{
			JsonElement el = body.get("message"); //No I18N
			if (el != null && el.isJsonPrimitive())
			{
				return el.getAsString();
			}
		}
		return null;
	}

	boolean isSuccess()
	{
		return statusCode >= 200 && statusCode < 300;
	}
}
