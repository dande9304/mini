package com.github.alexmao86.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ClassName: JSONObject
 * 
 * @author
 */
public final class JsonObject extends JsonElement {
	private final KeyArrayLinkedHashMap members = new KeyArrayLinkedHashMap();

	/**
	 * @param property
	 *            name of the member.
	 * @param value
	 *            the member object.
	 */
	public final void add(String property, JsonElement value) {
		if (value == null) {
			value = JsonNull.INSTANCE;
		}
		members.put(property, value);
	}

	/**
	 * Removes the {@code property} from this {@link JsonObject}.
	 *
	 * @param property
	 *            name of the member that should be removed.
	 * @return the {@link JsonElement} object that is being removed.
	 * @since 1.3
	 */
	public final JsonElement remove(String property) {
		return members.remove(property);
	}

	/**
	 * Convenience method to add a primitive member. The specified value is
	 * converted to a JsonPrimitive of String.
	 *
	 * @param property
	 *            name of the member.
	 * @param value
	 *            the string value associated with the member.
	 */
	public final void addProperty(String property, String value) {
		add(property, createJsonElement(value, JsonElement.STRING));
	}

	/**
	 * Convenience method to add a primitive member. The specified value is
	 * converted to a JsonPrimitive of Number.
	 *
	 * @param property
	 *            name of the member.
	 * @param value
	 *            the number value associated with the member.
	 */
	public final void addProperty(String property, Number value) {
		add(property, createJsonElement(value, JsonElement.NUMBER));
	}

	/**
	 * Convenience method to add a boolean member. The specified value is converted
	 * to a JsonPrimitive of Boolean.
	 *
	 * @param property
	 *            name of the member.
	 * @param value
	 *            the number value associated with the member.
	 */
	public final void addProperty(String property, Boolean value) {
		add(property, createJsonElement(value, JsonElement.BOOLEAN));
	}

	/**
	 * Convenience method to add a char member. The specified value is converted to
	 * a JsonPrimitive of Character.
	 *
	 * @param property
	 *            name of the member.
	 * @param value
	 *            the number value associated with the member.
	 */
	public final void addProperty(String property, Character value) {
		add(property, createJsonElement(value, JsonElement.CHAR));
	}

	/**
	 * Convenience method to add a Date member. The specified value is converted to
	 * a JsonPrimitive of Date.
	 *
	 * @param property
	 *            name of the member.
	 * @param value
	 *            the number value associated with the member.
	 */
	public final void addProperty(String property, Date value) {
		add(property, createJsonElement(value, JsonElement.DATE));
	}

	/**
	 * Creates the proper {@link JsonElement} object from the given {@code value}
	 * object.
	 *
	 * @param value
	 *            the object to generate the {@link JsonElement} for
	 * @return a {@link JsonPrimitive} if the {@code value} is not null, otherwise a
	 *         {@link JsonNull}
	 */
	private JsonElement createJsonElement(Object value, int type) {
		return value == null ? JsonNull.INSTANCE : new JsonUncertain(value, type);
	}

	/**
	 * Returns a set of members of this object. The set is ordered, and the order is
	 * in which the elements were added.
	 *
	 * @return a set of members of this object.
	 */
	public final Set<Map.Entry<String, JsonElement>> entrySet() {
		return members.entrySet();
	}

	/**
	 * Convenience method to check if a member with the specified name is present in
	 * this object.
	 *
	 * @param memberName
	 *            name of the member that is being checked for presence.
	 * @return true if there is a member with the specified name, false otherwise.
	 */
	public final boolean has(String memberName) {
		return members.containsKey(memberName);
	}

	/* ****************************** */

	@Override
	public final boolean getAsBoolean() {
		if (members.size() != 1)
			return super.getAsBoolean();
		return members.getAt(0).getAsBoolean();
	}

	@Override
	public final Number getAsNumber() {
		if (members.size() != 1)
			return super.getAsNumber();
		return members.getAt(0).getAsNumber();
	}

	@Override
	public final String getAsString() {
		if (members.size() != 1)
			return super.getAsString();
		return members.getAt(0).getAsString();
	}

	@Override
	public final double getAsDouble() {
		if (members.size() != 1)
			return super.getAsDouble();
		return members.getAt(0).getAsDouble();
	}

	@Override
	public final float getAsFloat() {
		if (members.size() != 1)
			return super.getAsFloat();
		return members.getAt(0).getAsFloat();
	}

	@Override
	public final long getAsLong() {
		if (members.size() != 1)
			return super.getAsLong();
		return members.getAt(0).getAsLong();
	}

	@Override
	public final int getAsInt() {
		if (members.size() != 1)
			return super.getAsInt();
		return members.getAt(0).getAsInt();
	}

	@Override
	public final byte getAsByte() {
		if (members.size() != 1)
			return super.getAsByte();
		return members.getAt(0).getAsByte();
	}

	@Override
	public final char getAsCharacter() {
		if (members.size() != 1)
			return super.getAsCharacter();
		return members.getAt(0).getAsCharacter();
	}

	@Override
	public final BigDecimal getAsBigDecimal() {
		if (members.size() != 1)
			return super.getAsBigDecimal();
		return members.getAt(0).getAsBigDecimal();
	}

	@Override
	public final BigInteger getAsBigInteger() {
		if (members.size() != 1)
			return super.getAsBigInteger();
		return members.getAt(0).getAsBigInteger();
	}

	@Override
	public final short getAsShort() {
		if (members.size() != 1)
			return super.getAsShort();
		return members.getAt(0).getAsShort();
	}

	@Override
	public final Date getAsDate() {
		if (members.size() != 1)
			return super.getAsDate();
		return members.getAt(0).getAsDate();
	}

	@Override
	public final JsonElement getAsJSONElementAt(int index) {
		return members.getAt(index);
	}

	@Override
	public final JsonArray getAsJSONArrayAt(int index) {
		return members.getAt(index).getAsJSONArray();
	}

	@Override
	public final JsonObject getAsJSONObjectAt(int index) {
		return members.getAt(index).getAsJsonObject();
	}

	@Override
	public final boolean getAsBooleanAt(int index) {
		return members.getAt(index).getAsBoolean();
	}

	@Override
	public final Number getAsNumberAt(int index) {
		return members.getAt(index).getAsNumber();
	}

	@Override
	public final String getAsStringAt(int index) {
		return members.getAt(index).getAsString();
	}

	@Override
	public final double getAsDoubleAt(int index) {
		return members.getAt(index).getAsDouble();
	}

	@Override
	public final float getAsFloatAt(int index) {
		return members.getAt(index).getAsFloat();
	}

	@Override
	public final long getAsLongAt(int index) {
		return members.getAt(index).getAsLong();
	}

	@Override
	public final int getAsIntAt(int index) {
		return members.getAt(index).getAsInt();
	}

	@Override
	public final byte getAsByteAt(int index) {
		return members.getAt(index).getAsByte();
	}

	@Override
	public final char getAsCharacterAt(int index) {
		return members.getAt(index).getAsCharacter();
	}

	@Override
	public final BigDecimal getAsBigDecimalAt(int index) {
		return members.getAt(index).getAsBigDecimal();
	}

	@Override
	public final BigInteger getAsBigIntegerAt(int index) {
		return members.getAt(index).getAsBigInteger();
	}

	@Override
	public final short getAsShortAt(int index) {
		return members.getAt(index).getAsShort();
	}

	@Override
	public final Date getAsDateAt(int index) {
		return members.getAt(index).getAsDate();
	}

	@Override
	public final JsonElement getAsJSONElement(String memberName) {
		return members.get(memberName);
	}

	@Override
	public final JsonArray getAsJSONArray(String memberName) {
		return members.get(memberName).getAsJSONArray();
	}

	@Override
	public final JsonObject getAsJSONObject(String memberName) {
		return members.get(memberName).getAsJsonObject();
	}

	@Override
	public final boolean getAsBoolean(String memberName) {
		return members.get(memberName).getAsBoolean();
	}

	@Override
	public final Number getAsNumber(String memberName) {
		return members.get(memberName).getAsNumber();
	}

	@Override
	public final String getAsString(String memberName) {
		return members.get(memberName).getAsString();
	}

	@Override
	public final double getAsDouble(String memberName) {
		return members.get(memberName).getAsDouble();
	}

	@Override
	public final float getAsFloat(String memberName) {
		return members.get(memberName).getAsFloat();
	}

	@Override
	public final long getAsLong(String memberName) {
		return members.get(memberName).getAsLong();
	}

	@Override
	public final int getAsInt(String memberName) {
		return members.get(memberName).getAsInt();
	}

	@Override
	public final byte getAsByte(String memberName) {
		return members.get(memberName).getAsByte();
	}

	@Override
	public final char getAsCharacter(String memberName) {
		return members.get(memberName).getAsCharacter();
	}

	@Override
	public final BigDecimal getAsBigDecimal(String memberName) {
		return members.get(memberName).getAsBigDecimal();
	}

	@Override
	public final BigInteger getAsBigInteger(String memberName) {
		return members.get(memberName).getAsBigInteger();
	}

	@Override
	public final short getAsShort(String memberName) {
		return members.get(memberName).getAsShort();
	}

	@Override
	public final Date getAsDate(String memberName) {
		return members.get(memberName).getAsDate();
	}

	@Override
	public final int getSize() {
		return members.size();
	}

	protected Map<String, JsonElement> getMemberAsMap() {
		return Collections.unmodifiableMap(this.members);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(AVG_OBJ_LENGTH << 2);
		builder.append("{");
		for (Entry<String, JsonElement> entry : members.entrySet()) {
			JsonElement value = entry.getValue();
			if (value instanceof JsonNull)
				continue;
			builder.append("\"").append(entry.getKey()).append("\":").append(value.toString()).append(",");
		}
		if (builder.charAt(builder.length() - 1) == ',') {
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("}");
		return builder.toString();
	}
}
