
package com.github.alexmao86.json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.github.alexmao86.json.JsonElement;
import com.github.alexmao86.json.JsonNull;
import com.github.alexmao86.json.JsonParseException;
import com.github.alexmao86.json.JsonReader;

import junit.framework.TestCase;

public class JSONReaderTest extends TestCase {
	JsonReader jsonReader;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		jsonReader = new JsonReader();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		jsonReader = null;
	}

	public void testParseEmpty() throws IOException, JsonParseException {
		String json = loadTestTestFile("empty");
		JsonElement result = parse(json);
		assertEquals(JsonNull.INSTANCE, result);
	}

	public void testParseNumber() throws IOException, JsonParseException {
		String json = loadTestTestFile("number");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONUncertain());
		assertEquals(123.45f, result.getAsFloat());
	}

	public void testParseNumberArray() throws IOException, JsonParseException {
		String json = loadTestTestFile("numberarray");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONArray());
		assertEquals(1, result.getAsIntAt(0));
		assertEquals(2, result.getAsIntAt(1));
		assertEquals(3.1415926d, result.getAsDoubleAt(2));
		assertEquals(4.2f, result.getAsFloatAt(3));
		assertEquals(5.0f, result.getAsFloatAt(4));
	}

	public void testParseString() throws IOException, JsonParseException {
		String json = loadTestTestFile("string");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONUncertain());
		assertEquals("Alex", result.getAsString());
	}

	public void testParseStringArray() throws IOException, JsonParseException {
		String json = loadTestTestFile("stringarray");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONArray());
		assertEquals(5, result.getSize());
		assertEquals("a", result.getAsStringAt(0));
		assertEquals("b", result.getAsStringAt(1));
		assertEquals("c", result.getAsStringAt(2));
		assertEquals("d", result.getAsStringAt(3));
		assertEquals("e", result.getAsStringAt(4));
	}

	public void testParseObjectArray() throws IOException, JsonParseException {
		String json = loadTestTestFile("objectarray");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONArray());
		assertEquals(4, result.getSize());

		assertEquals(31, result.getAsJSONObjectAt(0).getAsInt("age"));
		assertEquals(25, result.getAsJSONObjectAt(1).getAsInt("age"));
		assertEquals(29, result.getAsJSONObjectAt(2).getAsInt("age"));
		assertEquals(29, result.getAsJSONObjectAt(3).getAsInt("age"));

		assertEquals("Alex", result.getAsJSONObjectAt(0).getAsString("name"));
		assertEquals("Mozi", result.getAsJSONObjectAt(1).getAsString("name"));
		assertEquals("Dailey", result.getAsJSONObjectAt(2).getAsString("name"));
		assertEquals("Dawei", result.getAsJSONObjectAt(3).getAsString("name"));

		assertEquals("QingCheng Road No.1", result.getAsJSONObjectAt(0).getAsString("address"));
		assertEquals("XXX", result.getAsJSONObjectAt(1).getAsString("address"));
		assertEquals("Yangchenghu Ave", result.getAsJSONObjectAt(2).getAsString("address"));
		assertEquals("Zhangjiagang", result.getAsJSONObjectAt(3).getAsString("address"));
	}

	public void testParseObject() throws IOException, JsonParseException {
		String json = loadTestTestFile("object");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONObject());
		assertEquals("Alex", result.getAsString("name"));
		assertEquals(31, result.getAsInt("age"));
		assertEquals("QingCheng Road No.1", result.getAsString("address"));
	}

	public void testParseNestedObject() throws IOException, JsonParseException {
		String json = loadTestTestFile("nestedobject");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONObject());
		assertEquals("Alex", result.getAsString("name"));
		assertEquals(31, result.getAsInt("age"));
		assertEquals("QingCheng Road No.1", result.getAsString("address"));

		assertEquals(55, result.getAsJSONObject("father").getAsInt("age"));
		assertEquals("Mao XX", result.getAsJSONObject("father").getAsString("name"));
		assertEquals("Zigui", result.getAsJSONObject("father").getAsString("address"));

		JsonElement weekdays = result.getAsJSONArray("weekdays");
		assertEquals(true, weekdays.isJSONArray());

		assertEquals("Sun", weekdays.getAsStringAt(0));
		assertEquals("Mon", weekdays.getAsStringAt(1));
		assertEquals("Tue", weekdays.getAsStringAt(2));
		assertEquals("Wed", weekdays.getAsStringAt(3));
		assertEquals("Thr", weekdays.getAsStringAt(4));
		assertEquals("Fri", weekdays.getAsStringAt(5));
		assertEquals("Sat", weekdays.getAsStringAt(6));
	}

	public void testParseNonstrictNestedObject() throws IOException, JsonParseException {
		String json = loadTestTestFile("nonStrictNestedobject");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONObject());
		assertEquals("Alex", result.getAsString("name"));
		assertEquals(31, result.getAsInt("age"));
		assertEquals("QingCheng Road No.1", result.getAsString("address"));

		assertEquals(55, result.getAsJSONObject("father").getAsInt("age"));
		assertEquals("Mao XX", result.getAsJSONObject("father").getAsString("name"));
		assertEquals("Zigui", result.getAsJSONObject("father").getAsString("address"));

		JsonElement weekdays = result.getAsJSONArray("weekdays");
		assertEquals(true, weekdays.isJSONArray());

		assertEquals("Sun", weekdays.getAsStringAt(0));
		assertEquals("Mon", weekdays.getAsStringAt(1));
		assertEquals("Tue", weekdays.getAsStringAt(2));
		assertEquals("Wed", weekdays.getAsStringAt(3));
		assertEquals("Thr", weekdays.getAsStringAt(4));
		assertEquals("Fri", weekdays.getAsStringAt(5));
		assertEquals("Sat", weekdays.getAsStringAt(6));
	}

	public void testParseComplex() throws IOException, JsonParseException {
		String json = loadTestTestFile("complex");
		JsonElement result = parse(json);
		assertEquals(true, result.isJSONArray());
		assertEquals("SNORUS", result.getAsJSONObjectAt(1).getAsString("company"));
	}

	private JsonElement parse(String json) throws IOException, JsonParseException {
		JsonElement ret = jsonReader.parse(json);
		System.out.println(ret);
		return ret;
	}

	public static String loadTestTestFile(String name) throws IOException {
		StringBuilder b = new StringBuilder(512);
		Reader in = new FileReader(new File("src/test/resources/" + name + ".json"));
		char[] buf = new char[128];
		int length = -1;
		while ((length = in.read(buf)) != -1) {
			b.append(buf, 0, length);
		}
		in.close();
		String ret = b.toString();
		System.out.print("JSON: ");
		System.out.println(b);
		return ret;
	}
}
