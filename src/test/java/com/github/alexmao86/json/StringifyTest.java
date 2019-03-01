package com.github.alexmao86.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.github.alexmao86.json.JsonWriter;

import junit.framework.TestCase;

public class StringifyTest extends TestCase {
	public void testMap() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("age", 25);
		map.put("name", "alex");
		map.put("position", "Programmer");
		map.put("salary", "99999999");
		String json = new JsonWriter().stringify(map);
		System.out.println(json);

	}

	public void testArray() {
		char[] carr = { 'a', 'b', 'c', 'd', 'e' };
		byte[] barr = { 1, 2, 3, 4, 5 };
		int[] iarr = { 1, 2, 3, 4, 5 };
		short[] sarr = { 1, 2, 3, 4, 5 };

		System.out.println(new JsonWriter().stringify(carr));
		System.out.println(new JsonWriter().stringify(barr));
		System.out.println(new JsonWriter().stringify(iarr));
		System.out.println(new JsonWriter().stringify(sarr));

		DemoJavaBean bean = new DemoJavaBean();
		bean.setAddress("test add1");
		bean.setAge(31);
		bean.setFeeling("I am feeling good");
		bean.setName("Alex");

		bean.setFather(new DemoJavaBean(55, "Mao XX", "ZiGui"));
		bean.setMother(new DemoJavaBean(52, "Wang XX", "Yichang"));
		bean.setChildren(new ArrayList<>());

		System.out.println(new JsonWriter().stringify(bean));
	}

	public void testJsonElementStringify() {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("isProgrammer", true);
		jsonObject.addProperty("testC", 'C');
		jsonObject.addProperty("name", "alex");

		System.out.println(new JsonWriter().stringify(jsonObject));

		JsonArray jsonArray = new JsonArray();
		jsonArray.add(new JsonUncertain("123"));
		jsonArray.add(new JsonUncertain("abc"));
		jsonArray.add(jsonObject);
		System.out.println(new JsonWriter().stringify(jsonArray));
	}
}
