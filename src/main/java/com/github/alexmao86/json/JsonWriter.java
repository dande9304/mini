package com.github.alexmao86.json;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * ClassName: JSONStringify <br/>
 * date: 2017年9月26日 上午9:38:02 <br/>
 * 
 * @author
 */
@SuppressWarnings(value = { "rawtypes", "unchecked" })
public final class JsonWriter extends JsonSystem {

	public final String stringify(Object value) {
		if (value == null)
			return null;
		if (value instanceof JsonElement) {
			return value.toString();
		}
		if (value.getClass().isArray()) {
			return jdkReflectJsonWorker.serialize(value, this);
		}

		return queryJsonSerializer(value.getClass()).serialize(value, this);
	}

	public final String stringify(Map value) {
		if (value == null)
			return null;
		return queryJsonSerializer(Map.class).serialize(value, this);
	}

	public final String stringify(Collection value) {
		if (value == null)
			return null;
		return queryJsonSerializer(Collection.class).serialize(value, this);
	}
}
