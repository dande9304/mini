package com.github.alexmao86.json;

import com.github.alexmao86.json.io.CharSequenceInputSource;

/**
 * ClassName: JsonParseException <br/>
 * date: 2017年9月26日 上午10:02:11 <br/>
 * 
 * @author
 */
public class JsonParseException extends Exception {
	/**
	 * Creates a new instance of JsonParseException.
	 * 
	 * @param source
	 */
	public JsonParseException(CharSequenceInputSource source) {

	}

	public JsonParseException() {
		super();
	}

	public JsonParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JsonParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonParseException(String message) {
		super(message);
	}

	public JsonParseException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new instance of JsonParseException.
	 * 
	 * @param src
	 * @param location
	 */
	public JsonParseException(String src, int location) {
		super(src + ", location " + location);
	}

	private static final long serialVersionUID = 1L;
}
