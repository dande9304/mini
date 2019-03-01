package com.github.alexmao86.json;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class JsonArrayTest {

	@Test
	public void test() {

		// 制作一个array数据
		JsonArray jsonArrayX = new JsonArray(2);
		jsonArrayX.add(new JsonUncertain("0"));

		// JsonArray 相关方法
		JsonArray jsonArray = new JsonArray(5);
		jsonArray.add(new JsonUncertain(1l));
		jsonArray.add(new JsonUncertain(true));
		jsonArray.add(jsonArrayX);
		jsonArray.add(new JsonUncertain(new Date()));
		jsonArray.add(new JsonUncertain("123"));
		jsonArray.add(null);

		assertEquals(6, jsonArray.getItemsAsList().size());
		assertEquals(1l, jsonArray.getAsLongAt(0));
		assertEquals(true, jsonArray.getAsBooleanAt(1));
		assertEquals('0', jsonArray.getAsJSONArrayAt(2).getAsCharacter());
		assertEquals(true, jsonArray.getAsDateAt(3) != null);
		assertEquals(jsonArray.getSize(), 6);

		// 1个基本数据类型
		JsonArray jsonArray2 = new JsonArray(2);
		jsonArray2.add(new JsonUncertain("45"));
		JsonElement element = jsonArray2.get(0);

		assertEquals("45", jsonArray2.getAsJSONElementAt(0).getAsString());

		assertEquals(element.isJSONNull(), false);
		assertEquals(element.getAsJSONUncertain().getAsString(), "45");
		assertEquals(jsonArray2.getSize(), 1);
		Iterator<JsonElement> it = jsonArray2.iterator();
		System.out.print("it.next():" + it.next().getAsString());

		assertEquals(element.toString(), "\"45\"");

		assertEquals(jsonArray2.getAsBoolean(), false);

		assertEquals(jsonArray2.getAsNumber().intValue(), 45);

		assertEquals(jsonArray2.getAsString(), "45");

		assertEquals(jsonArray2.getAsInt(), 45);

		assertEquals(jsonArray2.getAsByte(), 45);

		assertEquals(jsonArray2.getAsCharacter(), '4');

		assertEquals(jsonArray2.getAsBigDecimal().intValue(), 45);

		assertEquals(jsonArray2.getAsBigInteger().intValue(), 45);

		assertEquals(jsonArray2.getAsShort(), 45);

		// 带下标
		assertEquals(jsonArray2.getAsByteAt(0), 45);
		assertEquals(jsonArray2.getAsCharacterAt(0), '4');
		assertEquals(jsonArray2.getAsBigDecimalAt(0).toString(), "45");
		assertEquals(jsonArray2.getAsBigIntegerAt(0).toString(), "45");
		assertEquals(jsonArray2.getAsShortAt(0), 45);
		assertEquals(jsonArray2.getAsNumberAt(0).intValue(), 45);

		System.out.print("dou" + jsonArray2.getAsDouble());

		System.out.print("dou" + jsonArray2.getAsFloat());

		System.out.print("dou" + jsonArray2.getAsLong());

		jsonArray2.addAll(jsonArray);
		System.out.print(jsonArray.getSize());

	}

}
