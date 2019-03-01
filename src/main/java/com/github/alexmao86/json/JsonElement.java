package com.github.alexmao86.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * A class representing an element of Json. It could either be a
 * {@link JsonObject}, a {@link JsonArray}, a {@link JsonPrimitive} or a
 * {@link JsonNull}.
 * 
 * @author
 */
public abstract class JsonElement extends JsonSystem {
	/**
	 * type definition of uncertain
	 */
	public static final int UNCERTAIN = -1;
	/**
	 * type definition of boolean
	 */
	public static final int BOOLEAN = 0;
	/**
	 * type definition of byte
	 */
	// public static final int BYTE = 1;
	/**
	 * type definition of char
	 */
	public static final int CHAR = 2;
	/**
	 * type definition of number
	 */
	public static final int NUMBER = 3;
	/**
	 * type definition of string
	 */
	public static final int STRING = 4;
	/**
	 * type definition of date
	 */
	public static final int DATE = 5;
	/**
	 * type definition of json element
	 */
	public static final int JSON_ELEMENT = 6;

	/*
	 * ***************************class cast check
	 * methods**************************************
	 */

	/**
	 * provides check for verifying if this element is an array or not.
	 *
	 * @return true if this element is of type {@link JsonArray}, false otherwise.
	 */
	public boolean isJSONArray() {
		return this instanceof JsonArray;
	}

	/**
	 * provides check for verifying if this element is a Json object or not.
	 *
	 * @return true if this element is of type {@link JsonObject}, false otherwise.
	 */
	public boolean isJSONObject() {
		return this instanceof JsonObject;
	}

	/**
	 * provides check for verifying if this element represents a null value or not.
	 *
	 * @return true if this element is of type {@link JsonNull}, false otherwise.
	 */
	public boolean isJSONNull() {
		return this instanceof JsonNull;
	}

	/**
	 * provides check for verifying if this element is a primitive or not.
	 *
	 * @return true if this element is of type {@link JsonUncertain}, false
	 *         otherwise.
	 */
	public boolean isJSONUncertain() {
		return this instanceof JsonUncertain;
	}

	/**
	 * convenience method to get this element as a {@link JsonObject}. If the
	 * element is of some other type, a {@link ClassCastException} will result.
	 * Hence it is best to use this method after ensuring that this element is of
	 * the desired type by calling {@link #isJSONObject()} first.
	 *
	 * @return get this element as a {@link JsonObject}.
	 * @throws IllegalStateException
	 *             if the element is of another type.
	 */
	public JsonObject getAsJsonObject() {
		if (isJSONObject()) {
			return (JsonObject) this;
		}
		throw new IllegalStateException("Not a JSON Object: " + this);
	}

	/**
	 * convenience method to get this element as a {@link JsonArray}. If the element
	 * is of some other type, a {@link ClassCastException} will result. Hence it is
	 * best to use this method after ensuring that this element is of the desired
	 * type by calling {@link #isJSONArray()} first.
	 *
	 * @return get this element as a {@link JsonArray}.
	 * @throws IllegalStateException
	 *             if the element is of another type.
	 */
	public JsonArray getAsJSONArray() {
		if (isJSONArray()) {
			return (JsonArray) this;
		}
		throw new IllegalStateException("This is not a JSON Array.");
	}

	/**
	 * convenience method to get this element as a {@link JsonUncertain}. If the
	 * element is of some other type, a {@link ClassCastException} will result.
	 * Hence it is best to use this method after ensuring that this element is of
	 * the desired type by calling {@link #JSONUncertain()} first.
	 *
	 * @return get this element as a {@link JsonUncertain}.
	 * @throws IllegalStateException
	 *             if the element is of another type.
	 */
	public JsonUncertain getAsJSONUncertain() {
		if (isJSONUncertain()) {
			return (JsonUncertain) this;
		}
		throw new IllegalStateException("This is not a JSONUncertain.");
	}

	/**
	 * convenience method to get this element as a {@link JsonNull}. If the element
	 * is of some other type, a {@link ClassCastException} will result. Hence it is
	 * best to use this method after ensuring that this element is of the desired
	 * type by calling {@link #isJSONNull()} first.
	 *
	 * @return get this element as a {@link JsonNull}.
	 * @throws IllegalStateException
	 *             if the element is of another type.
	 * 
	 */
	public JsonNull getAsJSONNull() {
		if (isJSONNull()) {
			return (JsonNull) this;
		}
		throw new IllegalStateException("This is not a JSON Null.");
	}

	/*
	 * ********************************get single value as single data type, for
	 * composite design pattern leaf *********************************************
	 */

	/**
	 * convenience method to get this element as a boolean value.
	 *
	 * @return get this element as a primitive boolean value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid boolean value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public boolean getAsBoolean() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link Number}.
	 *
	 * @return get this element as a {@link Number}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid number.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public Number getAsNumber() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a string value.
	 *
	 * @return get this element as a string value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid string value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public String getAsString() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive double value.
	 *
	 * @return get this element as a primitive double value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid double value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public double getAsDouble() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive float value.
	 *
	 * @return get this element as a primitive float value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid float value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public float getAsFloat() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive long value.
	 *
	 * @return get this element as a primitive long value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid long value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public long getAsLong() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive integer value.
	 *
	 * @return get this element as a primitive integer value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid integer value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public int getAsInt() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive byte value.
	 *
	 * @return get this element as a primitive byte value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid byte value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 * 
	 */
	public byte getAsByte() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive character value.
	 *
	 * @return get this element as a primitive char value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid char value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 * 
	 */
	public char getAsCharacter() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link BigDecimal}.
	 *
	 * @return get this element as a {@link BigDecimal}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain}. * @throws
	 *             NumberFormatException if the element is not a valid
	 *             {@link BigDecimal}.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 * 
	 */
	public BigDecimal getAsBigDecimal() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link BigInteger}.
	 *
	 * @return get this element as a {@link BigInteger}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain}.
	 * @throws NumberFormatException
	 *             if the element is not a valid {@link BigInteger}.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 * 
	 */
	public BigInteger getAsBigInteger() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive short value.
	 *
	 * @return get this element as a primitive short value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid short value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public short getAsShort() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link java.util.Date} value.
	 *
	 * @return get this element as a {@link java.util.Date} value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonUncertain} and is not a
	 *             valid short value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonArray} but contains more
	 *             than a single element.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonObject} but contains
	 *             more than a single element.
	 */
	public Date getAsDate() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/*
	 * ***********************get element at index, this is for composite design
	 * pattern branch**************************************
	 */
	/**
	 * convenience method to get this element as JSONObject at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as JSONObject.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not JSONObject.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not JSONObject.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public JsonElement getAsJSONElementAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as JSONArray at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as JSONArray.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not JSONArray.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not JSONArray.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public JsonArray getAsJSONArrayAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as JSONObject at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as JSONObject.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not JSONObject.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not JSONObject.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public JsonObject getAsJSONObjectAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a boolean value at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive boolean value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid boolean value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid boolean value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public boolean getAsBooleanAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link Number} at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a {@link Number}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid number.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid number value.
	 * @throws IllegalStateException
	 *             if the element at position is of the type {@link JsonUncertain}
	 *             but index != 0, API consider JSONUncertain as one object length
	 *             of 1
	 */
	public Number getAsNumberAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a string value at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a string value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid string value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid string value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public String getAsStringAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive double value at
	 * position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive double value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid double value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid double value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public double getAsDoubleAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive float value at
	 * position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive float value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid float value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid float value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public float getAsFloatAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive long value at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive long value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid long value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid long value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public long getAsLongAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive integer value at
	 * position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive integer value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid integer value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid int value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public int getAsIntAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive byte value at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive byte value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid byte value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid byte value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 * 
	 */
	public byte getAsByteAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive character value at
	 * position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive char value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid char value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid char value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 * 
	 */
	public char getAsCharacterAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link BigDecimal} at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a {@link BigDecimal}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             can not cast to BigDecimal
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position can not cast to BigDecimal
	 * @throws NumberFormatException
	 *             if the element is not a valid {@link BigDecimal}.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 * 
	 */
	public BigDecimal getAsBigDecimalAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link BigInteger} at position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a {@link BigInteger}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             can not cast to BigInteger
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position can not cast to BigInteger
	 * @throws NumberFormatException
	 *             if the element is not a valid {@link BigInteger}.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 * 
	 */
	public BigInteger getAsBigIntegerAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive short value at
	 * position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a primitive short value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid short value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid short value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public short getAsShortAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link java.util.Date} value at
	 * position.
	 * 
	 * @param index
	 *            index of member wants to get
	 * @return get this element at position as a {@link java.util.Date} value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonArray} and data at position
	 *             is not a valid Date.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid Date value.
	 * @throws IllegalStateException
	 *             if the element is of the type {@link JsonUncertain} but index !=
	 *             0, API consider JSONUncertain as one object length of 1
	 */
	public Date getAsDateAt(int index) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/* *****************JSONObject member getter*************** */
	/**
	 * convenience method to get this element as JSONElement at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as JSONElement.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not JSONElement.
	 */
	public JsonElement getAsJSONElement(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as JSONArray at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as JSONArray.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not JSONArray.
	 */
	public JsonArray getAsJSONArray(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as JSONObject at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as JSONObject.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not JSONObject.
	 */
	public JsonObject getAsJSONObject(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a boolean value at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a primitive boolean value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid boolean value.
	 */
	public boolean getAsBoolean(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link Number} at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a {@link Number}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid number value.
	 */
	public Number getAsNumber(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a string value at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a string value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid string value.
	 */
	public String getAsString(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive double value at
	 * position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a primitive double value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid double value.
	 */
	public double getAsDouble(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive float value at
	 * position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a primitive float value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid float value.
	 */
	public float getAsFloat(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive long value at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a primitive long value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid long value.
	 */
	public long getAsLong(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive integer value at
	 * position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a primitive integer value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid int value.
	 */
	public int getAsInt(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive byte value at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a primitive byte value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid byte value.
	 * 
	 */
	public byte getAsByte(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive character value at
	 * position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a primitive char value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid char value.
	 * 
	 */
	public char getAsCharacter(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link BigDecimal} at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a {@link BigDecimal}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position can not cast to BigDecimal
	 * @throws NumberFormatException
	 *             if the element is not a valid {@link BigDecimal}.
	 * 
	 */
	public BigDecimal getAsBigDecimal(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link BigInteger} at position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element at position as a {@link BigInteger}.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position can not cast to BigInteger
	 * @throws NumberFormatException
	 *             if the element is not a valid {@link BigInteger}.
	 */
	public BigInteger getAsBigInteger(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a primitive short value at
	 * position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element at position as a primitive short value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data at
	 *             position is not a valid short value.
	 */
	public short getAsShort(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * convenience method to get this element as a {@link java.util.Date} value at
	 * position.
	 * 
	 * @param memberName
	 *            member name
	 * @return get this element's member as a {@link java.util.Date} value.
	 * @throws ClassCastException
	 *             if the element is of not a {@link JsonObject} and data of member
	 *             is not a valid Date value.
	 */
	public Date getAsDate(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/* *************************************** */
	/**
	 * convenience method to get size of this json element. if instance is
	 * {@link JsonUncertain}, return 1; if {@link JsonArray}, return array size; if
	 * {@link JsonObject}, return 1; if {@link JsonNull} return 0
	 * 
	 * @return get this element size.
	 */
	public int getSize() {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}

	/**
	 * Convenience method to check if a member with the specified name is present in
	 * this object.
	 *
	 * @param memberName
	 *            name of the member that is being checked for presence.
	 * @return true if there is a member with the specified name, false otherwise.
	 */
	public boolean has(String memberName) {
		throw new UnsupportedOperationException(getClass().getSimpleName());
	}
}
