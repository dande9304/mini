package com.github.alexmao86.json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.alexmao86.json.annotation.JsonIgnore;
import com.github.alexmao86.json.annotation.JsonSerializable;

/**
 * ClassName: JDKReflectJsonWorker <br/>
 * if object's type can not be find in register, default JDK serializer will be
 * used. System are taking below types as primary types:
 * <ul>
 * <li>String</li>
 * 
 * <li>Boolean</li>
 * <li>Byte</li>
 * <li>Character</li>
 * <li>Short</li>
 * <li>Integer</li>
 * <li>Long</li>
 * <li>Float</li>
 * <li>Double</li>
 * 
 * <li>Number</li>
 * <li>Date</li>
 * <li>java.util.Collection</li>
 * <li>java.util.Map</li>
 * </ul>
 */
@SuppressWarnings("rawtypes")
public class JDKReflectJsonWorker implements JsonSerializer<Object>, JsonDeserializer<Object> {
	private final static Logger LOGGER = Logger.getLogger(JDKReflectJsonWorker.class.getName());
	private Map<Class, List<FieldJsonDefinition>> typeFieldCache = new HashMap<Class, List<FieldJsonDefinition>>();

	public Class<Object> supportedType() {
		return Object.class;
	}

	/*
	 * primary types will be auto boxed 如果object是原始类型，已经被自动装箱
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String serialize(Object object, JsonSystem context) {
		if (object == null)
			return "";
		Class<?> clazz = object.getClass();
		// if supported in built-in
		if (context.supportedSerializerForClass(clazz)) {
			return context.queryJsonSerializer(clazz).serialize(object, context);
		}

		// if array type
		if (clazz.isArray()) {
			Class<?> componentType = clazz.getComponentType();
			if (componentType.isPrimitive()) {
				if (boolean.class.isAssignableFrom(componentType)) {
					return Arrays.toString((boolean[]) object);
				} else if (byte.class.isAssignableFrom(componentType)) {
					return Arrays.toString((byte[]) object);
				} else if (char.class.isAssignableFrom(componentType)) {
					char[] casted = (char[]) object;
					if (casted.length == 0)
						return "[]";
					StringBuilder builder = new StringBuilder(casted.length << 4 + 2);
					builder.append("[");
					for (int i = 0; i < casted.length; i++) {
						builder.append("\"").append(casted[i]).append("\",");
					}
					builder.deleteCharAt(builder.length() - 1);
					builder.append("]");
					return builder.toString();
				} else if (double.class.isAssignableFrom(componentType)) {
					return Arrays.toString((double[]) object);
				} else if (float.class.isAssignableFrom(componentType)) {
					return Arrays.toString((float[]) object);
				} else if (int.class.isAssignableFrom(componentType)) {
					return Arrays.toString((int[]) object);
				} else if (long.class.isAssignableFrom(componentType)) {
					return Arrays.toString((long[]) object);
				} else if (short.class.isAssignableFrom(componentType)) {
					return Arrays.toString((short[]) object);
				}
			} // end of isPrimitive
			else {
				Object[] array = (Object[]) object;
				StringBuilder builder = new StringBuilder(JsonSystem.AVG_OBJ_LENGTH * array.length);
				builder.append("[");
				for (Object obj : array) {
					builder.append(context.queryJsonSerializer(obj.getClass()).serialize(obj, context)).append(",");
				}
				if (array.length > 0)
					builder.deleteCharAt(builder.length() - 1);

				builder.append("]");
				return builder.toString();
			}
		}
		// else if(clazz.isPrimitive()){}//已经被自动装箱
		else if (object instanceof Collection) {
			return context.queryJsonSerializer(Collection.class).serialize(object, context);
		} else if (object instanceof Map) {
			return context.queryJsonSerializer(Map.class).serialize(object, context);
		}

		// check if set annotation on class
		JsonSerializable jsonSerializable = clazz.getDeclaredAnnotation(JsonSerializable.class);
		if (jsonSerializable != null) {
			Class sType = jsonSerializable.serializer();
			JsonSerializer jsonSerializer = context.queryJsonSerializer(sType);
			if (jsonSerializer != null)
				return jsonSerializer.serialize(object, context);
		}

		// then reflect it, process as object
		List<FieldJsonDefinition> fields = typeFieldCache.get(clazz);
		if (fields == null) {
			fields = reflectFileds(clazz, context);
			typeFieldCache.put(clazz, fields);
		}

		StringBuilder builder = new StringBuilder(128);
		builder.append("{");
		for (FieldJsonDefinition def : fields) {
			try {
				Object fieldValue = def.getFieldValue(object);
				if (fieldValue == null)
					continue;
				Object val = def.jsonSerializer.serialize(fieldValue, context);
				// System.out.println(def.memberName+"="+val);
				if (val == null)
					continue;
				builder.append("\"").append(def.memberName).append("\":");
				builder.append(val).append(",");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (builder.charAt(builder.length() - 1) == ',') {
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("}");
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object deserialize(JsonElement json, Class<Object> targetClass, JsonSystem context, Class... actualTypes)
			throws JsonParseException {
		JsonDeserializer deserializer = context.queryJsonDeserializer(targetClass);
		if (!(deserializer instanceof JDKReflectJsonWorker)) {
			return deserializer.deserialize(json, targetClass, context);
		}

		// if array type
		if (targetClass.isArray()) {
			JsonArray casted = json.getAsJSONArray();
			Class<?> componentType = targetClass.getComponentType();
			if (componentType.isPrimitive()) {
				if (boolean.class.isAssignableFrom(componentType)) {
					boolean[] ret = new boolean[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsBooleanAt(i);
					}
					return ret;
				} else if (byte.class.isAssignableFrom(componentType)) {
					byte[] ret = new byte[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsByteAt(i);
					}
					return ret;
				} else if (char.class.isAssignableFrom(componentType)) {
					char[] ret = new char[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsCharacterAt(i);
					}
					return ret;
				} else if (double.class.isAssignableFrom(componentType)) {
					double[] ret = new double[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsDoubleAt(i);
					}
					return ret;
				} else if (float.class.isAssignableFrom(componentType)) {
					float[] ret = new float[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsFloatAt(i);
					}
					return ret;
				} else if (int.class.isAssignableFrom(componentType)) {
					int[] ret = new int[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsIntAt(i);
					}
					return ret;
				} else if (long.class.isAssignableFrom(componentType)) {
					long[] ret = new long[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsLongAt(i);
					}
					return ret;
				} else if (short.class.isAssignableFrom(componentType)) {
					short[] ret = new short[casted.getSize()];
					for (int i = 0; i < casted.getSize(); i++) {
						ret[i] = casted.getAsShortAt(i);
					}
					return ret;
				}
			} // end of isPrimitive
			else {
				Object array = Array.newInstance(componentType, casted.getSize());
				Object[] castedArray = (Object[]) array;
				for (int i = 0; i < casted.getSize(); i++) {
					castedArray[i] = context.queryJsonDeserializer(componentType)
							.deserialize(casted.getAsJSONElementAt(i), componentType, context);
				}
				return array;
			}
		} else if (targetClass.isPrimitive()) {// 如果类字段时原始类型，改写为装箱类型
			if (boolean.class.equals(targetClass)) {
				return json.getAsBoolean();
			} else if (byte.class.equals(targetClass)) {
				return json.getAsByte();
			} else if (char.class.equals(targetClass)) {
				return json.getAsCharacter();
			} else if (short.class.equals(targetClass)) {
				return json.getAsShort();
			} else if (int.class.equals(targetClass)) {
				return json.getAsInt();
			} else if (long.class.equals(targetClass)) {
				return json.getAsLong();
			} else if (float.class.equals(targetClass)) {
				return json.getAsFloat();
			} else if (double.class.equals(targetClass)) {
				return json.getAsDouble();
			}
		}

		// check if set annotation on class
		JsonSerializable jsonSerializable = targetClass.getDeclaredAnnotation(JsonSerializable.class);
		if (jsonSerializable != null) {
			Class dType = jsonSerializable.deserializer();
			JsonDeserializer deserializer2 = context.queryJsonDeserializer(dType);
			if (deserializer2 != null)
				return deserializer2.deserialize(json, targetClass, context);
		}

		// JDK reflect
		List<FieldJsonDefinition> fields = typeFieldCache.get(targetClass);
		if (fields == null) {
			fields = reflectFileds(targetClass, context);
			typeFieldCache.put(targetClass, fields);
		}

		// create instance
		try {
			Object instance = targetClass.newInstance();
			for (FieldJsonDefinition def : fields) {
				JsonElement elment = json.getAsJSONElement(def.memberName);
				if (elment == null)
					continue;
				Object value = def.jsonDeserializer.deserialize(elment, def.mappingType, context, def.actualType0,
						def.actualType1);
				if (value == null)
					continue;
				def.setFieldValue(instance, value);
			}
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			throw new JsonParseException("Your plain java bean does not have default constructor");
		}
	}

	/**
	 * TODO 如果改进支持泛型类型的级联,则需要改进本方法,如果第归的记录和传递类型参数 reflectFileds: use java reflect to
	 * list all fields for json serialization
	 * 
	 * @param object
	 * @return
	 */
	private List<FieldJsonDefinition> reflectFileds(Class clazz, JsonSystem context) {
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return new ArrayList<FieldJsonDefinition>(0);
		}

		ArrayList<FieldJsonDefinition> ret = new ArrayList<FieldJsonDefinition>(fields.length);
		for (Field field : fields) {
			int fieldModifiers = field.getModifiers();

			if (Modifier.isTransient(fieldModifiers)) {
				continue;
			}
			if (Modifier.isStatic(fieldModifiers)) {
				continue;
			}
			if (field.getDeclaredAnnotation(JsonIgnore.class) != null) {// json ignored
				continue;
			}
			field.setAccessible(true);

			// ok to get field
			Class fieldType = field.getType();
			Class actualType0 = null;
			Class actualType1 = null;

			if (Collection.class.isAssignableFrom(fieldType)) {
				try {
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					actualType0 = (Class<?>) listType.getActualTypeArguments()[0];
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE,
							"your pojo {} contains java collection filed, but is not jdk5 generic style, it is not supported",
							clazz);
					LOGGER.log(Level.SEVERE,
							"Please regist your JsonDeserializer and JsonSerializer for {0}, or use jdk5 generic syntax",
							clazz);
					LOGGER.log(Level.SEVERE, "stacktrace", e);
				}
			} else if (Map.class.isAssignableFrom(fieldType)) {
				try {
					ParameterizedType listType = (ParameterizedType) field.getGenericType();
					actualType0 = (Class<?>) listType.getActualTypeArguments()[0];
					actualType1 = (Class<?>) listType.getActualTypeArguments()[1];
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE,
							"your pojo {0} contains java Map filed, but is not jdk5 generic style, it is not supported",
							clazz);
					LOGGER.log(Level.SEVERE,
							"Please regist your JsonDeserializer and JsonSerializer for {0}, or use jdk5 generic syntax",
							clazz);
					LOGGER.log(Level.SEVERE, "stacktrace", e);
				}
			}

			// ok to getter method
			Method getter = getGetterMethod(clazz, field);
			if (getter != null) {
				getter.setAccessible(true);
			}

			// ok to setter method
			Method setter = getSetterMethod(clazz, field);
			if (setter != null) {
				setter.setAccessible(true);
			}

			// ok to get memberName
			String memberName = field.getName();// default
			JsonSerializable jsonSerializable = field.getDeclaredAnnotation(JsonSerializable.class);
			if (jsonSerializable != null) {
				if (!jsonSerializable.value().isEmpty() && jsonSerializable.value() != null) {
					memberName = jsonSerializable.value();
				}
			}

			JsonSerializer serializer = context.queryJsonSerializer(fieldType);
			JsonDeserializer deserializer = context.queryJsonDeserializer(fieldType);
			ret.add(new FieldJsonDefinition(fieldType, actualType0, actualType1, field, getter, setter, memberName,
					serializer, deserializer));
		}
		ret.trimToSize();
		return Collections.unmodifiableList(ret);
	}

	/**
	 * getGetterMethod: get the getter method od one field according to stand java
	 * bean naming convention. if not satisfied to java bean naming convention
	 * return null
	 * 
	 * @param field
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Method getGetterMethod(final Class clazz, final Field field) {
		Class fieldType = field.getType();

		String fieldName = field.getName();
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		String getterMethodName = "get" + fieldName;
		try {
			Method method = clazz.getDeclaredMethod(getterMethodName);
			// check return type matching
			if (!method.getReturnType().equals(fieldType)) {
				return null;
			}
			// check modifiers
			int modifers = method.getModifiers();
			if (!Modifier.isPublic(modifers)) {
				return null;
			}
			return method;
		} catch (NoSuchMethodException | SecurityException e) {
			LOGGER.log(Level.FINE, "did not get method", e);
		}

		// if field is boolean, possilbe getter will be isXXX
		if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
			getterMethodName = "is" + fieldName;
			try {
				Method method = clazz.getDeclaredMethod(getterMethodName);
				// check return type matching
				if (!method.getReturnType().equals(fieldType)) {
					return null;
				}
				// check modifiers
				int modifers = method.getModifiers();
				if (!Modifier.isPublic(modifers)) {
					return null;
				}
				return method;
			} catch (NoSuchMethodException | SecurityException e) {
				LOGGER.log(Level.FINE, "did not get method", e);
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Method getSetterMethod(final Class clazz, final Field field) {
		Class fieldType = field.getType();

		String fieldName = field.getName();
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

		String getterMethodName = "set" + fieldName;
		try {
			Method method = clazz.getDeclaredMethod(getterMethodName, fieldType);
			// check return type matching
			if (!method.getReturnType().equals(void.class)) {
				return null;
			}
			// check modifiers
			int modifers = method.getModifiers();
			if (!Modifier.isPublic(modifers)) {
				return null;
			}
			return method;
		} catch (NoSuchMethodException | SecurityException e) {
			LOGGER.log(Level.FINE, "did not get method", e);
		}

		return null;
	}

	/**
	 * one class to hold field serialization definition
	 */
	class FieldJsonDefinition {
		Class mappingType;// 字段影射的类型,由于装箱类型,和范型,因此存在类型和实际类型可能不同
		// 如果是collection范型，则ActualType0不空，map则ActualType0和ActualType1不空
		Class actualType0;// 如果是范类型，元素的实际类型
		Class actualType1;// 如果是范类型，元素的实际类型
		// the field
		Field field;
		// the getter method
		Method getter;
		// the setter method
		Method setter;
		// the final serialization name
		String memberName;
		// the serializaer will be used
		JsonSerializer jsonSerializer;
		JsonDeserializer jsonDeserializer;

		private FieldJsonDefinition(Class mappingType, Class actualType0, Class actualType1, Field field, Method getter,
				Method setter, String memberName, JsonSerializer jsonSerializer, JsonDeserializer jsonDeserializer) {
			super();
			this.mappingType = mappingType;
			this.actualType0 = actualType0;
			this.actualType1 = actualType1;
			this.field = field;
			this.getter = getter;
			this.setter = setter;
			this.memberName = memberName;
			this.jsonSerializer = jsonSerializer;
			this.jsonDeserializer = jsonDeserializer;
		}

		/**
		 * setValue set value to object, use setter method if setter exists, if not,
		 * reflect field directly.<br/>
		 * 
		 * @throws InvocationTargetException
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public void setFieldValue(Object object, Object value)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			if (setter != null)
				setter.invoke(object, value);
			field.set(object, value);
		}

		/**
		 * invoke get value from object, use getter method if getter exists, if not,
		 * reflect field directly.<br/>
		 * 
		 * @throws InvocationTargetException
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public Object getFieldValue(Object object)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			if (getter != null)
				return getter.invoke(object);
			return field.get(object);
		}
	}

}
