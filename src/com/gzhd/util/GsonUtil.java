package com.gzhd.util;

import java.util.Collection;
import java.util.Dictionary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().enableComplexMapKeySerialization().create();

	/**
	 * @Name: parseJsonArray
	 * @Description: Collection集合转化为JsonArray
	 * @Version: V1.00 （版本号）
	 * @Parameters: 无
	 * @Return: JsonArray
	 */
	public static JsonArray parseJsonArray(Collection<?> list) {
		String r = gson.toJson(list);
		JsonParser parser = new JsonParser();
		JsonElement el = parser.parse(r);
		JsonArray jsonArray = null;
		if (el.isJsonArray()) {
			jsonArray = el.getAsJsonArray();
		}
		return jsonArray;
	}

	/**
	 * @Name: parseJsonArray
	 * @Description: Directory集合转化为JsonArray
	 * @Version: V1.00 （版本号）
	 * @Parameters: 无
	 * @Return: JsonArray
	 */
	public static JsonArray parseJsonArray(Dictionary<Object, Object> table) {
		String r = gson.toJson(table);
		JsonParser parser = new JsonParser();
		JsonElement el = parser.parse(r);
		JsonArray jsonArray = null;
		if (el.isJsonArray()) {
			jsonArray = el.getAsJsonArray();
		}
		return jsonArray;
	}

	/**
	 * @Name: parseJsonObject
	 * @Description: 实体对象转化为JsonArray
	 * @Version: V1.00 （版本号）
	 * @Parameters: 无
	 * @Return: JsonObject
	 */
	public static JsonObject parseJsonObject(Object o) {
		String r = gson.toJson(o);
		JsonParser parser = new JsonParser();
		JsonElement el = parser.parse(r);
		JsonObject j = null;
		if (el.isJsonObject()) {
			j = el.getAsJsonObject();
		}
		return j;
	}

	/**
	 * @Name: parseJsonObject
	 * @Description: json格式字符串转化为JsonObject
	 * @Version: V1.00 （版本号）
	 * @Parameters: String
	 * @Return: JsonObject
	 */
	public static JsonObject parseJsonObject(String s) {
		JsonParser parser = new JsonParser();
		JsonElement jsonEl = parser.parse(s);
		JsonObject j = null;
		j = jsonEl.getAsJsonObject();
		return j;
	}

}
