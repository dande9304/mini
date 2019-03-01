package com.github.alexmao86.json;

import static org.junit.Assert.*;

import org.junit.Test;

public class KeyArrayLinkedHashMapTest {

	@Test
	public void test() {
		KeyArrayLinkedHashMap map = new KeyArrayLinkedHashMap();
		KeyArrayLinkedHashMap map2 = new KeyArrayLinkedHashMap();

		map.put("a", new JsonUncertain(2));
		map.put("b", new JsonUncertain(3));
		map2.put("z", new JsonUncertain(10));
		map.putAll(map2);
		map.putIfAbsent("a", new JsonUncertain(20));
		map.replace("b", new JsonUncertain(6));
		map.replace("a", new JsonUncertain(2), new JsonUncertain(4));
		map.remove("b");

		assertEquals(map.get("a").getAsInt(), 2);
		assertEquals(map.get("z").getAsInt(), 10);

		map.remove("a", new JsonUncertain(2));
		System.out.print(map.size());
	}

}
