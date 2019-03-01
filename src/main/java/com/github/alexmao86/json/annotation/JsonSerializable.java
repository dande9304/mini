package com.github.alexmao86.json.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.TYPE })
public @interface JsonSerializable {
	/**
	 * @return the desired name of the field when it is serialized or deserialized
	 *         if used on class, value will be ignored
	 */
	String value() default "";

	/**
	 * 
	 * deserializer
	 * 
	 * @since JDK 1.8
	 */
	@SuppressWarnings("rawtypes")
	Class deserializer() default Void.class;

	/**
	 * serializer
	 */
	@SuppressWarnings("rawtypes")
	Class serializer() default Void.class;
}
