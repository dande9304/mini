
package com.github.alexmao86.json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;

import junit.framework.TestCase;

public class BenchMarkTest extends TestCase {
	static int TIMES=100000;
	public void testMiniJson() throws IOException, JsonParseException {
		String json = loadTestTestFile("complex");
		long acc=0;
		for(int i=0;i<TIMES;i++) {
			long start=System.nanoTime();
			new JsonReader().parse(json);
			long end=System.nanoTime();
			long dur=end-start;
			acc+=(dur);
		}
		System.out.printf("test mini json parse %d times, total time %d ms, avg %d ns\n", TIMES, acc/1000000, acc/TIMES);
	}
	public void testGson() throws IOException, JsonParseException {
		String json = loadTestTestFile("complex");
		long acc=0;
		for(int i=0;i<TIMES;i++) {
			long start=System.nanoTime();
			new Gson().fromJson(json, ArrayList.class);
			long end=System.nanoTime();
			long dur=end-start;
			acc+=(dur);
		}
		System.out.printf("test Gson parse %d times, total time %d ms, avg %d ns\n", TIMES, acc/1000000, acc/TIMES);
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
		return ret;
	}
}
