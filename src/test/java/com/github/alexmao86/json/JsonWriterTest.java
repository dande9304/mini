package com.github.alexmao86.json;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.junit.Test;

public class JsonWriterTest {

	@Test
	public void test() {
		Object str = null;
		System.out.print(new JsonWriter().stringify(str));
		Map map = null;
		System.out.print(new JsonWriter().stringify(map));
		Collection col = null;
		System.out.print(new JsonWriter().stringify(col));
	}

}
