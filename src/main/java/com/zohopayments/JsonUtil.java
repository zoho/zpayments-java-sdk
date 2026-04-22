package com.zohopayments;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.zohopayments.exception.ZohoPaymentsException;
import com.zohopayments.model.PageContext;

import java.math.BigDecimal;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// JSON helpers for the SDK.
final class JsonUtil
{
	private static final JsonDeserializer<BigDecimal> BIG_DECIMAL_LENIENT = (JsonElement json, Type typeOfT,
			JsonDeserializationContext context) ->
	{
		if (json == null || json.isJsonNull())
		{
			return null;
		}
		if (json.isJsonPrimitive())
		{
			JsonPrimitive p = json.getAsJsonPrimitive();
			if (p.isString())
			{
				String s = p.getAsString();
				if (s == null || s.isBlank())
				{
					return null;
				}
				return new BigDecimal(s.trim());
			}
		}
		try
		{
			return json.getAsBigDecimal();
		}
		catch (NumberFormatException ignored)
		{
			return null;
		}
	};

	private static final Gson GSON = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.registerTypeAdapter(BigDecimal.class, BIG_DECIMAL_LENIENT)
			.create();

	private JsonUtil() {}

	static Gson gson() { return GSON; }
	static String toJson(Object obj) { return GSON.toJson(obj); }
	static <T> T fromJson(String json, Class<T> clazz) { return GSON.fromJson(json, clazz); }
	static <T> T fromJson(JsonElement json, Class<T> clazz) { return GSON.fromJson(json, clazz); }
	static <T> T fromJson(String json, Type type) { return GSON.fromJson(json, type); }
	static <T> T fromJson(JsonElement json, Type type) { return GSON.fromJson(json, type); }
	static JsonObject parseObject(String json) { return GSON.fromJson(json, JsonObject.class); }

	static <T> List<T> listFromJson(JsonObject body, Type type, String... keys)
	{
		if (body == null)
		{
			return Collections.emptyList();
		}
		for (String key : keys)
		{
			if (body.has(key) && body.get(key).isJsonArray())
			{
				List<T> result = GSON.fromJson(body.getAsJsonArray(key), type);
				return result != null ? result : Collections.emptyList();
			}
		}
		return Collections.emptyList();
	}

	static JsonObject getObject(JsonObject body, String... keys)
	{
		if (body == null)
		{
			return null;
		}
		for (String key : keys)
		{
			if (body.has(key) && body.get(key).isJsonObject())
			{
				return body.getAsJsonObject(key);
			}
		}
		return null;
	}

	static JsonObject getObjectRequired(JsonObject body, String... candidateKeys)
	{
		if (body == null)
		{
			throw new ZohoPaymentsException("Response body is null; expected resource object with key(s) " //No I18N
					+ Arrays.toString(candidateKeys));
		}
		for (String key : candidateKeys)
		{
			if (body.has(key) && body.get(key).isJsonObject())
			{
				return body.getAsJsonObject(key);
			}
		}
		throw new ZohoPaymentsException(
				"Response body missing expected resource key(s) " + Arrays.toString(candidateKeys)); //No I18N
	}

	/**
	 * Unwraps a single resource from an {@link ApiResponse} body into {@code type},
	 * looking it up under the first of {@code candidateKeys} that resolves to a JSON object.
	 */
	static <T> T unwrap(ApiResponse response, Class<T> type, String... candidateKeys)
	{
		return fromJson(getObjectRequired(response.getBody(), candidateKeys), type);
	}

	static PageContext readPageContext(JsonObject body)
	{
		JsonObject pageObj = getObject(body, "page_context"); //No I18N
		if (pageObj == null)
		{
			return null;
		}
		return fromJson(pageObj, PageContext.class);
	}
}
