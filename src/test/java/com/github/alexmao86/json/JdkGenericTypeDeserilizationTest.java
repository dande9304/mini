package com.github.alexmao86.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class JdkGenericTypeDeserilizationTest extends TestCase {
	private Map<String, UserBean> map;
	private List<UserBean> list = new ArrayList<>();
	private String json;
	private String arrayJson;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		map = new HashMap<>();
		map.put("Alex", new UserBean("Alex", "Road 1", 31, 1000));
		map.put("Dailey", new UserBean("Dailey", "Road 1", 31, 1000));
		map.put("Mozi", new UserBean("Mozi", "Road 1", 31, 1000));
		map.put("Dawei", new UserBean("Dawei", "Road 1", 31, 1000));
		list.addAll(map.values());

		JsonWriter jsonWriter = new JsonWriter();
		json = jsonWriter.stringify(map);
		arrayJson = jsonWriter.stringify(list);
		System.out.println(json);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testMapRead() throws IOException, JsonParseException {
		JsonReader jsonReader = new JsonReader();
		Map<String, UserBean> map = jsonReader.parseAsMap(json, String.class, UserBean.class);
		System.out.println(map);
		assertEquals(this.map.size(), map.size());

		for (String key : map.keySet()) {
			assertEquals(true, this.map.containsKey(key));
			assertEquals(map.get(key), this.map.get(key));
		}
		for (String key : this.map.keySet()) {
			assertEquals(true, map.containsKey(key));
			assertEquals(map.get(key), this.map.get(key));
		}
	}

	public void testCollectionRead() throws IOException, JsonParseException {
		JsonReader jsonReader = new JsonReader();
		Collection<UserBean> list = jsonReader.parseAsCollection(arrayJson, UserBean.class);
		assertEquals(this.list.size(), list.size());
		Iterator<UserBean> it = list.iterator();
		Iterator<UserBean> it1 = this.list.iterator();
		while (it.hasNext()) {
			UserBean u1 = it.next();
			UserBean u2 = it1.next();
			assertEquals(u1, u2);
		}
	}
}
