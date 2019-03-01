package com.github.alexmao86.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: JSONSystem is the core rules holder for all serializer<br/>
 * date: 2017年9月28日 下午2:36:08 <br/>
 * 
 * @author
 */
@SuppressWarnings("rawtypes")
public class JsonSystem {
	private final static Logger LOGGER = Logger.getLogger(JsonSystem.class.getName());
	protected static final String JSON_SPLITER = ",";
	protected static final int AVG_OBJ_LENGTH = 24;

	protected JsonDateFormatter dateFormatter = new JsonDateFormatter() {
	};

	/**
	 * if user does not speicify serializer, reflection serialzer will be used
	 */
	protected final static JDKReflectJsonWorker jdkReflectJsonWorker = new JDKReflectJsonWorker();

	protected final static Map<Class<?>, JsonSerializer<?>> builtInJSONSerializers = new HashMap<Class<?>, JsonSerializer<?>>();

	protected final Map<Class<?>, JsonSerializer<?>> userJsonSerializers = new HashMap<Class<?>, JsonSerializer<?>>();

	protected final static Map<Class<?>, JsonDeserializer> builtInJsonDeserializers = new HashMap<Class<?>, JsonDeserializer>();

	protected final Map<Class<?>, JsonDeserializer> userJsonDeserializers = new HashMap<Class<?>, JsonDeserializer>();

	// register built-in object swapper
	static {
		// string
		JsonWorker stringWorker = new JsonWorker<String>() {
			public Class<String> supportedType() {
				return String.class;
			}

			@Override
			public String serialize(String src, JsonSystem context) {
				return new StringBuilder(2 + src.length()).append("\"").append(escape(src)).append("\"").toString();
			}

			@Override
			public String deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsString();
			}
		};
		globalJsonSerializer(stringWorker);
		globalJsonDeserializer(stringWorker);

		// boolean primary type? how
		JsonWorker boolWorker = new JsonWorker<Boolean>() {
			public Class<Boolean> supportedType() {
				return Boolean.class;
			}

			@Override
			public Boolean deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsBoolean();
			}
		};
		globalJsonSerializer(boolWorker);
		globalJsonDeserializer(boolWorker);

		// byte
		JsonWorker byteWorker = new JsonWorker<Byte>() {
			public Class<Byte> supportedType() {
				return Byte.class;
			}

			@Override
			public Byte deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsByte();
			}
		};
		globalJsonSerializer(byteWorker);
		globalJsonDeserializer(byteWorker);

		// character
		JsonWorker charWorker = new JsonWorker<Character>() {
			public Class<Character> supportedType() {
				return Character.class;
			}

			@Override
			public String serialize(Character src, JsonSystem context) {
				return new StringBuilder(3).append("\"").append(src).append("\"").toString();
			}

			@Override
			public Character deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsCharacter();
			}
		};
		globalJsonSerializer(charWorker);
		globalJsonDeserializer(charWorker);
		// short
		JsonWorker shortWorker = new JsonWorker<Short>() {
			public Class<Short> supportedType() {
				return Short.class;
			}

			@Override
			public Short deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsShort();
			}
		};
		globalJsonSerializer(shortWorker);
		globalJsonDeserializer(shortWorker);
		// Integer
		JsonWorker intWorker = new JsonWorker<Integer>() {
			public Class<Integer> supportedType() {
				return Integer.class;
			}

			@Override
			public Integer deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsInt();
			}
		};
		globalJsonSerializer(intWorker);
		globalJsonDeserializer(intWorker);

		// long
		JsonWorker longWorker = new JsonWorker<Long>() {
			public Class<Long> supportedType() {
				return Long.class;
			}

			@Override
			public Long deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsLong();
			}
		};
		globalJsonSerializer(longWorker);
		globalJsonDeserializer(longWorker);
		// float
		JsonWorker floatWorker = new JsonWorker<Float>() {
			public Class<Float> supportedType() {
				return Float.class;
			}

			@Override
			public Float deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsFloat();
			}
		};
		globalJsonSerializer(floatWorker);
		globalJsonDeserializer(floatWorker);

		// double
		JsonWorker doubleWorker = new JsonWorker<Double>() {
			public Class<Double> supportedType() {
				return Double.class;
			}

			@Override
			public Double deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsDouble();
			}
		};
		globalJsonSerializer(doubleWorker);
		globalJsonDeserializer(doubleWorker);

		JsonWorker numberWorker = new JsonWorker<Number>() {
			public Class<Number> supportedType() {
				return Number.class;
			}

			@Override
			public Number deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsNumber();
			}
		};
		globalJsonSerializer(numberWorker);
		globalJsonDeserializer(numberWorker);

		JsonWorker dateWorker = new JsonWorker<Date>() {
			public Class<Date> supportedType() {
				return Date.class;
			}

			@Override
			public String serialize(Date object, JsonSystem context) {
				if (object == null)
					return null;
				return context.dateFormatter.format(object);
			}

			@Override
			public Date deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				return json.getAsDate();
			}
		};
		globalJsonSerializer(dateWorker);
		globalJsonDeserializer(dateWorker);

	}

	{

		JsonWorker collectionWorker = new JsonWorker<Collection>() {
			public Class<Collection> supportedType() {
				return Collection.class;
			}

			@SuppressWarnings("unchecked")
			@Override
			public String serialize(Collection value, JsonSystem context) {
				if (value == null || value.size() == 0)
					return "[]";

				StringBuilder builder = new StringBuilder(value.size() * AVG_OBJ_LENGTH);
				builder.append("[");
				for (Object obj : value) {
					JsonSerializer swapper = queryJsonSerializer(obj.getClass());
					builder.append(swapper.serialize(obj, JsonSystem.this)).append(",");
				}
				builder.deleteCharAt(builder.length() - 1);
				builder.append("]");
				return builder.toString();
			}

			// TODO how to determine composited type, java运行期间进行了范型擦除
			@SuppressWarnings("unchecked")
			@Override
			public Collection deserialize(JsonElement json, Class targetClass, JsonSystem context,
					Class... actuallTypes) throws JsonParseException {
				if (actuallTypes == null || actuallTypes.length == 0) {
					throw new IllegalArgumentException("actual type not set");
				} else if (Collection.class.isAssignableFrom(actuallTypes[0])
						|| Map.class.isAssignableFrom(actuallTypes[0])) {
					throw new UnsupportedOperationException("nested generic type not supported");
				}
				List<JsonElement> elements = json.getAsJSONArray().getItemsAsList();
				if (elements == null)
					return null;
				List list = new ArrayList<>(elements.size());
				for (JsonElement e : elements) {
					JsonDeserializer deserializer = context.queryJsonDeserializer(actuallTypes[0]);
					list.add(deserializer.deserialize(e, actuallTypes[0], context));
				}
				return list;
			}
		};
		registerJsonSerializer(collectionWorker);
		registerJsonDeserializer(collectionWorker);

		JsonWorker mapWorker = new JsonWorker<Map>() {
			public Class<Map> supportedType() {
				return Map.class;
			}

			@SuppressWarnings("unchecked")
			@Override
			public String serialize(Map value, JsonSystem context) {
				if (value.size() == 0)
					return "{}";

				JsonSerializer stringSwapper = queryJsonSerializer(String.class);
				StringBuilder builder = new StringBuilder(value.size() * AVG_OBJ_LENGTH);
				builder.append("{");
				Iterator iterator = value.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry entry = (Entry) iterator.next();
					String key = entry.getKey().toString();
					Object v = entry.getValue();

					JsonSerializer valueSwapper = queryJsonSerializer(v.getClass());

					builder.append(stringSwapper.serialize(key, JsonSystem.this)).append(":")
							.append(valueSwapper.serialize(v, JsonSystem.this)).append(",");
				}
				builder.deleteCharAt(builder.length() - 1);
				builder.append("}");
				return builder.toString();
			}

			@SuppressWarnings("unchecked")
			@Override
			public Map deserialize(JsonElement json, Class targetClass, JsonSystem context, Class... actuallTypes)
					throws JsonParseException {
				if (actuallTypes == null || actuallTypes.length == 0) {
					throw new IllegalArgumentException("actual type not set");
				} else if (!String.class.isAssignableFrom(actuallTypes[0])) {
					throw new UnsupportedOperationException("nested generic type not supported");
				} else if (Collection.class.isAssignableFrom(actuallTypes[0])
						|| Map.class.isAssignableFrom(actuallTypes[0])) {
					throw new UnsupportedOperationException("nested generic type not supported");
				} else if (Collection.class.isAssignableFrom(actuallTypes[1])
						|| Map.class.isAssignableFrom(actuallTypes[1])) {
					throw new UnsupportedOperationException("nested generic type not supported");
				}
				Map map = new HashMap<>();

				Map<String, JsonElement> jsonMap = json.getAsJsonObject().getMemberAsMap();// TODO how to determine
																							// composited type,
																							// java运行期间进行了范型擦除
				for (Entry<String, JsonElement> entry : jsonMap.entrySet()) {
					JsonElement valueEntry = entry.getValue();
					JsonDeserializer deserializer = context.queryJsonDeserializer(actuallTypes[1]);
					map.put(entry.getKey(), deserializer.deserialize(valueEntry, actuallTypes[1], context));
				}
				return map;
			}
		};
		registerJsonSerializer(mapWorker);
		registerJsonDeserializer(mapWorker);
	}

	public final static void globalJsonSerializer(JsonSerializer<?> serializer) {
		if (builtInJSONSerializers.containsKey(serializer.supportedType())) {
			LOGGER.log(Level.FINE, "{} is already registered, you are overriding");
		}
		builtInJSONSerializers.put(serializer.supportedType(), serializer);
	}

	public final static void globalJsonDeserializer(JsonDeserializer deserializer) {
		if (builtInJsonDeserializers.containsKey(deserializer.supportedType())) {
			LOGGER.log(Level.FINE, "{} is already registered, you are overriding");
		}
		builtInJsonDeserializers.put(deserializer.supportedType(), deserializer);
	}

	public final void registerJsonSerializer(JsonSerializer<?> swapper) {
		if (userJsonSerializers.containsKey(swapper.supportedType())) {
			LOGGER.log(Level.FINE, "{} is already registered, you are overriding");
		}
		userJsonSerializers.put(swapper.supportedType(), swapper);
	}

	public final void registerJsonDeserializer(JsonDeserializer swapper) {
		if (userJsonDeserializers.containsKey(swapper.supportedType())) {
			LOGGER.log(Level.FINE, "{} is already registered, you are overriding");
		}
		userJsonDeserializers.put(swapper.supportedType(), swapper);
	}

	/**
	 * Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters (U+0000
	 * through U+001F).
	 * 
	 * @param s
	 * @return
	 */
	final static String escape(String s) {
		if (s == null)
			return null;
		StringBuilder sb = new StringBuilder();
		final int len = s.length();
		for (int i = 0; i < len; i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				// Reference: http://www.unicode.org/versions/Unicode5.1.0/
				if ((ch >= '\u0000' && ch <= '\u001F') || (ch >= '\u007F' && ch <= '\u009F')
						|| (ch >= '\u2000' && ch <= '\u20FF')) {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		} // for
		return sb.toString();
	}

	/**
	 * unescape:TODO describe the approach and target of this method.
	 * 
	 * @param string
	 * @return
	 */
	final static String unescape(String string) {
		return string;
	}

	public final void setJsonDateFormatter(JsonDateFormatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	protected final JsonSerializer queryJsonSerializer(Class cls) {
		JsonSerializer swapper = userJsonSerializers.get(cls);
		if (swapper != null)
			return swapper;

		swapper = builtInJSONSerializers.get(cls);
		if (swapper != null)
			return swapper;

		// default
		return jdkReflectJsonWorker;
	}

	protected final JsonDeserializer queryJsonDeserializer(Class cls) {
		JsonDeserializer swapper = userJsonDeserializers.get(cls);
		if (swapper != null)
			return swapper;

		swapper = builtInJsonDeserializers.get(cls);
		if (swapper != null)
			return swapper;

		// default
		return jdkReflectJsonWorker;
	}

	/**
	 * supportedSerializerForClass
	 * 
	 * @param clazz
	 * @return
	 */
	public boolean supportedSerializerForClass(Class<?> clazz) {
		return builtInJSONSerializers.containsKey(clazz) || userJsonDeserializers.containsKey(clazz);
	}
}
