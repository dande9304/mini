package com.github.alexmao86.json;

/**
 * ClassName: JsonDeserializer <br/>
 * date: 2017年10月11日 下午4:34:23 <br/>
 * 
 * @author
 */
public interface JsonDeserializer<T> {
	public Class<T> supportedType();

	/**
	 * 
	 * deserialize
	 * 
	 * @param json
	 *            JsonElement system
	 * @param targetClass
	 *            your target class
	 * @param context
	 *            the json context
	 * @param actuallTypes
	 *            if targetClass is java generic types, then you must provide your
	 *            actual types.
	 * @return
	 * @throws JsonParseException
	 * @since JDK 1.8
	 */
	public T deserialize(JsonElement json, Class<T> targetClass, JsonSystem context,
			@SuppressWarnings("rawtypes") Class... actuallTypes) throws JsonParseException;
}
