package com.github.alexmao86.json;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class JsonUncertainTest {

	@Test
	public void test() {
		// object
		JsonObject obj1 = new JsonObject();
		obj1.addProperty("a", true);
		JsonUncertain json1 = new JsonUncertain(obj1);
		assertEquals(json1.getAsJSONObjectAt(0).getAsBoolean(), true);
		assertEquals(json1.getAsJsonObject().getAsBoolean(), true);
		assertEquals(true, json1.getValue() != null);
		System.out.print(json1.toString());

		JsonUncertain json7 = new JsonUncertain(obj1, JsonElement.JSON_ELEMENT);
		assertEquals(true, json7.getAsJsonObject().getAsBoolean());
		// array
		JsonArray jsonArrayX = new JsonArray(2);
		jsonArrayX.add(new JsonUncertain("0"));
		JsonUncertain jsonArray = new JsonUncertain(jsonArrayX);
		assertEquals(jsonArray.getAsJSONArrayAt(0).getAsString(), "0");

		JsonUncertain jsonArray2 = new JsonUncertain(jsonArrayX, JsonElement.JSON_ELEMENT);
		assertEquals(jsonArray.getAsJSONArray().getAsString(), "0");

		// JsonElement
		JsonElement jsonEle = new JsonUncertain(1);
		assertEquals(jsonEle.getAsNumberAt(0), 1);

		// jsonNull
		JsonUncertain jsonNull1 = new JsonUncertain(JsonNull.INSTANCE);
		assertEquals(jsonNull1.getAsJSONNull().toString(), "");
		assertEquals(-1, jsonNull1.getType());

		JsonUncertain jsonNull2 = new JsonUncertain(JsonNull.INSTANCE, JsonNull.JSON_ELEMENT);
		assertEquals(jsonNull2.getAsJSONNull().toString(), "");

		// date
		Date date = new Date();
		JsonUncertain json3 = new JsonUncertain(date, 5);
		System.out.print(json3.getAsDate());
		System.out.print(json3.getAsDateAt(0));
		System.out.print(json3.toString());

		// string
		JsonUncertain json4 = new JsonUncertain("2", 4);
		assertEquals(json4.getAsShortAt(0), 2);
		assertEquals(json4.getAsBigIntegerAt(0).toString(), "2");
		assertEquals(json4.getAsBigDecimalAt(0).toString(), "2");
		assertEquals(json4.getAsCharacterAt(0), '2');
		assertEquals(json4.getAsIntAt(0), 2);
		assertEquals(json4.getAsLongAt(0), 2);
		assertEquals(json4.getSize(), 1);
		assertEquals(json4.getAsStringAt(0), "2");
		System.out.print(json4.getAsFloatAt(0));
		System.out.print(json4.getAsDoubleAt(0));

		// boolean
		JsonUncertain json5 = new JsonUncertain(true, 0);
		assertEquals(true, json5.getAsBooleanAt(0));
		System.out.print(json5.toString());

		// number
		JsonUncertain json6 = new JsonUncertain(2, 3);
		assertEquals(2, json6.getAsNumberAt(0));
		System.out.print(json6.toString());

	}

}
