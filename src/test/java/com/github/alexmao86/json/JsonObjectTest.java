package com.github.alexmao86.json;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import org.junit.Test;

public class JsonObjectTest {
	private JsonObject obj = new JsonObject();
	private Map<String, JsonElement> map;

	@Test
	public void test() {

		obj.add("a", new JsonUncertain("2"));
		obj.add("b", null);
		assertEquals(obj.has("c"), false);

		obj.remove("b");
		obj.addProperty("c", true);
		obj.has("c");
		obj.remove("c");
		map = obj.getMemberAsMap();

		// 基本数据类型
		assEquaAll();

		obj.addProperty("a", new Date());
		// date
		assEquaDate();
		// insert data
		JsonArray array = new JsonArray();
		array.add(new JsonUncertain("0"));
		JsonObject obj2 = new JsonObject();
		obj2.add("insert", new JsonUncertain("insert"));
		obj.addProperty("b", 1);
		obj.add("c", array);
		obj.add("d", obj2);
		obj.add("e", new JsonUncertain(true));
		// more
		assMoreDate();
	}

	public void assMoreDate() {
		assertEquals(true, obj.getAsBooleanAt(4));
		assertEquals(true, obj.getAsBoolean("e"));
		assertEquals(1, obj.getAsJSONArrayAt(2).getSize());
		assertEquals("insert", obj.getAsJSONObjectAt(3).getAsString());
		assertEquals(1, obj.getAsJSONElement("b").getAsInt());
		assertEquals(5, obj.entrySet().size());

	}

	public void assEquaDate() {
		System.out.print(obj.getAsDate("a"));
		System.out.print(obj.getAsDate());
		System.out.print(obj.getAsDateAt(0));

	}

	public void assEquaAll() {
		// get()
		assertEquals(obj.getAsBoolean(), false);

		assertEquals(obj.getAsNumber().intValue(), 2);

		assertEquals(obj.getAsString(), "2");

		assertEquals(obj.getAsInt(), 2);

		assertEquals(obj.getAsCharacter(), '2');

		assertEquals(obj.getAsBigDecimal().intValue(), 2);

		assertEquals(obj.getAsBigInteger().intValue(), 2);

		assertEquals(obj.getAsShort(), 2);

		assertEquals(obj.getSize(), 1);

		assertEquals(map.size(), 1);

		System.out.print(obj.getAsLong() + "");

		System.out.print(obj.getAsDouble() + "");

		System.out.print(obj.getAsFloat() + "");

		// 带下标
		assertEquals(obj.getAsJSONElementAt(0).getAsInt(), 2);

		assertEquals(obj.getAsByte(), 2);

		assertEquals(obj.getAsNumberAt(0).intValue(), 2);

		assertEquals(obj.getAsStringAt(0), "2");

		assertEquals(obj.getAsIntAt(0), 2);

		assertEquals(obj.getAsLongAt(0), 2);

		assertEquals(obj.getAsBigDecimalAt(0).intValue(), 2);

		assertEquals(obj.getAsBigIntegerAt(0).intValue(), 2);

		assertEquals(obj.getAsCharacterAt(0), '2');

		System.out.print(obj.getAsDoubleAt(0) + "");

		System.out.print(obj.getAsFloatAt(0) + "");

		System.out.print(obj.getAsByteAt(0) + "");

		System.out.print(obj.getAsShortAt(0) + "");

		// 通过key获取
		assertEquals(obj.getAsBigInteger("a").intValue(), 2);

		assertEquals(obj.getAsNumber("a").intValue(), 2);

		assertEquals(obj.getAsString("a"), "2");

		assertEquals(obj.getAsInt("a"), 2);

		assertEquals(obj.getAsLong("a"), 2);

		assertEquals(obj.getAsBigDecimal("a").intValue(), 2);

		assertEquals(obj.getAsCharacter("a"), '2');

		System.out.print(obj.getAsDouble("a") + "");

		System.out.print(obj.getAsFloat("a") + "");

		System.out.print(obj.getAsByte("a") + "");

		System.out.print(obj.getAsShort("a") + "");

		obj.remove("a");
	}

}
