package com.github.alexmao86.json;

/**
 * ClassName: ObjectSwapper <br/>
 * date: 2017年9月26日 上午9:55:37 <br/>
 * 
 * @author
 */
public interface JsonSerializer<T> {
	/**
	 * 
	 * supportedType: declare its supported class<br/>
	 */
	Class<T> supportedType();

	/**
	 * 
	 * serialize given object instance to json string<br/>
	 */
	default public String serialize(T object, JsonSystem context) {
		return object.toString();
	}
}
