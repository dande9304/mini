package com.github.alexmao86.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * JSONUncertain: JSONUncertain is original string which can not be
 * determined<br/>
 * 
 */
public class JsonUncertain extends JsonElement {
	private final Object value;
	private final int type;

	public JsonUncertain(Object value) {
		this(value, UNCERTAIN);
	}

	public JsonUncertain(Object value, int type) {
		super();
		this.value = value;
		this.type = type;
	}

	public final Object getValue() {
		return value;
	}

	public final int getType() {
		return type;
	}

	@Override
	public String toString() {
		switch (type) {
		case UNCERTAIN:
		case JSON_ELEMENT:
			return new JsonWriter().stringify(value);
		case BOOLEAN:
			if (value instanceof Boolean)
				return value.toString();
			else
				return Boolean.parseBoolean(value.toString()) + "";
		case CHAR:
		case STRING:
			return new JsonWriter().stringify(value.toString());
		case DATE:
			if (value instanceof Date)
				new JsonWriter().stringify(value);
			return new JsonWriter().stringify(value.toString());
		case NUMBER:
			return value.toString();
		}
		return value.toString();
	}

	@Override
	public JsonObject getAsJsonObject() {
		if (value instanceof JsonObject)
			return (JsonObject) value;
		if (type == JSON_ELEMENT)
			return ((JsonElement) value).getAsJsonObject();
		return super.getAsJsonObject();
	}

	@Override
	public JsonArray getAsJSONArray() {
		if (value instanceof JsonArray)
			return (JsonArray) value;
		if (type == JSON_ELEMENT)
			return ((JsonElement) value).getAsJSONArray();
		return super.getAsJSONArray();
	}

	@Override
	public JsonUncertain getAsJSONUncertain() {
		if (value instanceof JsonUncertain)
			return (JsonUncertain) value;
		if (type == JSON_ELEMENT)
			return ((JsonElement) value).getAsJSONUncertain();
		return super.getAsJSONUncertain();
	}

	@Override
	public JsonNull getAsJSONNull() {
		if (value instanceof JsonNull)
			return (JsonNull) value;
		if (type == JSON_ELEMENT)
			return ((JsonElement) value).getAsJSONNull();
		return super.getAsJSONNull();
	}

	@Override
	public boolean getAsBoolean() {
		if (value instanceof Boolean)
			return (Boolean) value;
		return Boolean.parseBoolean(value.toString());
	}

	@Override
	public Number getAsNumber() {
		if (value instanceof Number)
			return (Number) value;
		return new LazilyParsedNumber(value.toString());
	}

	@Override
	public String getAsString() {
		return value.toString();
	}

	@Override
	public double getAsDouble() {
		if (value instanceof Double)
			return (Double) value;
		return Double.parseDouble((String) value);
	}

	@Override
	public float getAsFloat() {
		if (value instanceof Float)
			return (Float) value;
		return Float.parseFloat((String) value);
	}

	@Override
	public long getAsLong() {
		if (value instanceof Long)
			return (Long) value;
		return Long.parseLong((String) value);
	}

	@Override
	public int getAsInt() {
		if (value instanceof Integer)
			return (Integer) value;
		return Integer.parseInt((String) value);
	}

	@Override
	public byte getAsByte() {
		if (value instanceof Byte)
			return (Byte) value;
		return Byte.parseByte((String) value);
	}

	@Override
	public char getAsCharacter() {
		if (value instanceof Character)
			return (Character) value;
		return value.toString().charAt(0);
	}

	@Override
	public BigDecimal getAsBigDecimal() {
		if (value instanceof BigDecimal)
			return (BigDecimal) value;
		return new BigDecimal(value.toString());
	}

	@Override
	public BigInteger getAsBigInteger() {
		if (value instanceof BigInteger)
			return (BigInteger) value;
		return new BigInteger(value.toString());
	}

	@Override
	public short getAsShort() {
		if (value instanceof Short)
			return (Short) value;
		return Short.parseShort(value.toString());
	}

	@Override
	public Date getAsDate() {
		if (value instanceof Date)
			return (Date) value;
		return dateFormatter.parse(value.toString());
	}

	@Override
	public JsonElement getAsJSONElementAt(int index) {
		if (index != 0)
			return super.getAsJSONElementAt(index);
		return this;
	}

	@Override
	public JsonArray getAsJSONArrayAt(int index) {
		if (index != 0)
			return super.getAsJSONArrayAt(index);
		return this.getAsJSONArray();
	}

	@Override
	public JsonObject getAsJSONObjectAt(int index) {
		if (index != 0)
			return super.getAsJSONObjectAt(index);
		return this.getAsJsonObject();
	}

	@Override
	public boolean getAsBooleanAt(int index) {
		if (index != 0)
			return super.getAsBooleanAt(index);
		return this.getAsBoolean();
	}

	@Override
	public Number getAsNumberAt(int index) {
		if (index != 0)
			return super.getAsNumberAt(index);
		return this.getAsNumber();
	}

	@Override
	public String getAsStringAt(int index) {
		if (index != 0)
			return super.getAsStringAt(index);
		return this.getAsString();
	}

	@Override
	public double getAsDoubleAt(int index) {
		if (index != 0)
			return super.getAsDoubleAt(index);
		return this.getAsDouble();
	}

	@Override
	public float getAsFloatAt(int index) {
		if (index != 0)
			return super.getAsFloatAt(index);
		return this.getAsFloat();
	}

	@Override
	public long getAsLongAt(int index) {
		if (index != 0)
			return super.getAsLongAt(index);
		return this.getAsLong();
	}

	@Override
	public int getAsIntAt(int index) {
		if (index != 0)
			return super.getAsIntAt(index);
		return this.getAsInt();
	}

	@Override
	public byte getAsByteAt(int index) {
		if (index != 0)
			return super.getAsByteAt(index);
		return this.getAsByte();
	}

	@Override
	public char getAsCharacterAt(int index) {
		if (index != 0)
			return super.getAsCharacterAt(index);
		return this.getAsCharacter();
	}

	@Override
	public BigDecimal getAsBigDecimalAt(int index) {
		if (index != 0)
			return super.getAsBigDecimalAt(index);
		return this.getAsBigDecimal();
	}

	@Override
	public BigInteger getAsBigIntegerAt(int index) {
		if (index != 0)
			return super.getAsBigIntegerAt(index);
		return this.getAsBigInteger();
	}

	@Override
	public short getAsShortAt(int index) {
		if (index != 0)
			return super.getAsShortAt(index);
		return this.getAsShort();
	}

	@Override
	public Date getAsDateAt(int index) {
		if (index != 0)
			return super.getAsDateAt(index);
		return this.getAsDate();
	}

	@Override
	public int getSize() {
		return 1;
	}

}
