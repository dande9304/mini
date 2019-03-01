package com.github.alexmao86.json;

import static org.junit.Assert.*;

import org.junit.Test;

public class LazilyParsedNumberTest {

	@Test
	public void test() {
		String data = "10";
		LazilyParsedNumber lazi = new LazilyParsedNumber(data);
		assertEquals(lazi.intValue(), 10);
		assertEquals(lazi.longValue(), 10l);
		assertEquals(lazi.toString(), "10");
		System.out.print(lazi.floatValue());
		System.out.print(lazi.doubleValue());
	}

}
