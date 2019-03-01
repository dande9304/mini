package com.github.alexmao86.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class JDKReflectJsonWorkerTest2 extends TestCase {
	private TestBean p;
	JsonReader reader = new JsonReader();
	JsonWriter writer = new JsonWriter();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		initClass();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() throws IOException, JsonParseException {
		// object
		String jsonStringObject = writer.stringify(p);
		TestBean testBean = reader.parse(jsonStringObject, TestBean.class);
		System.out.print("\n" + jsonStringObject);

	}

	public void initClass() {
		Map<String, String> map = new HashMap();
		map.put("1", "1");
		List<String> list = new ArrayList();
		list.add("1");
		p = new TestBean();
		p.setBol(true);
		p.setBy((byte) 1);
		p.setCh('a');
		p.setSho((short) 1);
		p.setIn(1);
		p.setLon(1);
		p.setFlo(1);
		p.setDou(1);
		/*
		 * p.setStrArray(new String[] {"1"}); p.setByArray(new byte[] {1});
		 * p.setBolArray(new boolean[] {true}); p.setCharArray(new char[] {'a'});
		 * p.setDoubleArray(new double[] {1.0}); p.setFloatArray(new float[] {1});
		 * p.setIntArray(new int[] {1}); p.setLongArray(new long[] {1});
		 * p.setShortArray(new short[] {1});
		 */
		p.setMap(map);
		p.setCol(list);
	}
}
