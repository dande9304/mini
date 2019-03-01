package com.github.alexmao86.json;

/**
 * ClassName: JSONNull <br/>
 * date: 2017年9月26日 下午2:50:04 <br/>
 * 
 * @author
 */
public class JsonNull extends JsonElement {
	public static final JsonNull INSTANCE = new JsonNull();

	private JsonNull() {

	}

	@Override
	public String toString() {
		return "";
	}
}
