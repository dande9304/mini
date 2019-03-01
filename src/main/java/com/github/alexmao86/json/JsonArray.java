package com.github.alexmao86.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: JSONArray <br/>
 * 
 * @author
 * @version
 * @since JDK 1.8
 */
public final class JsonArray extends JsonElement {
	private final List<JsonElement> elements;

	/**
	 * Creates an empty JsonArray.
	 */
	public JsonArray(int initialCapicity) {
		elements = new ArrayList<JsonElement>(initialCapicity);
	}

	/**
	 * Creates an empty JsonArray.
	 */
	public JsonArray() {
		elements = new ArrayList<JsonElement>();
	}

	/**
	 * Adds the specified element to self.
	 *
	 * @param element
	 *            the element that needs to be added to the array.
	 */
	public final void add(JsonElement element) {
		if (element == null) {
			element = JsonNull.INSTANCE;
		}
		elements.add(element);
	}

	/**
	 * Adds all the elements of the specified array to self.
	 *
	 * @param array
	 *            the array whose elements need to be added to the array.
	 */
	public final void addAll(JsonArray array) {
		elements.addAll(array.elements);
	}

	@Override
	public final int getSize() {
		return elements.size();
	}

	/**
	 * Returns an iterator to navigate the elements of the array. Since the array is
	 * an ordered list, the iterator navigates the elements in the order they were
	 * inserted.
	 *
	 * @return an iterator to navigate the elements of the array.
	 */
	public final Iterator<JsonElement> iterator() {
		return elements.iterator();
	}

	/**
	 * Returns the ith element of the array.
	 *
	 * @param i
	 *            the index of the element that is being sought.
	 * @return the element present at the ith index.
	 * @throws IndexOutOfBoundsException
	 *             if i is negative or greater than or equal to the
	 *             {@link #getSize()} of the array.
	 */
	public final JsonElement get(int i) {
		return elements.get(i);
	}

	@Override
	public final boolean getAsBoolean() {
		if (getSize() != 1)
			return super.getAsBoolean();
		return elements.get(0).getAsBoolean();
	}

	@Override
	public final Number getAsNumber() {
		if (getSize() != 1)
			return super.getAsNumber();
		return elements.get(0).getAsNumber();
	}

	@Override
	public final String getAsString() {
		if (getSize() != 1)
			return super.getAsString();
		return elements.get(0).getAsString();
	}

	@Override
	public final double getAsDouble() {
		if (getSize() != 1)
			return super.getAsDouble();
		return elements.get(0).getAsDouble();
	}

	@Override
	public final float getAsFloat() {
		if (getSize() != 1)
			return super.getAsFloat();
		return elements.get(0).getAsFloat();
	}

	@Override
	public final long getAsLong() {
		if (getSize() != 1)
			return super.getAsLong();
		return elements.get(0).getAsLong();
	}

	@Override
	public final int getAsInt() {
		if (getSize() != 1)
			return super.getAsInt();
		return elements.get(0).getAsInt();
	}

	@Override
	public final byte getAsByte() {
		if (getSize() != 1)
			return super.getAsByte();
		return elements.get(0).getAsByte();
	}

	@Override
	public final char getAsCharacter() {
		if (getSize() != 1)
			return super.getAsCharacter();
		return elements.get(0).getAsCharacter();
	}

	@Override
	public final BigDecimal getAsBigDecimal() {
		if (getSize() != 1)
			return super.getAsBigDecimal();
		return elements.get(0).getAsBigDecimal();
	}

	@Override
	public final BigInteger getAsBigInteger() {
		if (getSize() != 1)
			return super.getAsBigInteger();
		return elements.get(0).getAsBigInteger();
	}

	@Override
	public final short getAsShort() {
		if (getSize() != 1)
			return super.getAsShort();
		return elements.get(0).getAsShort();
	}

	@Override
	public final Date getAsDate() {
		if (getSize() != 1)
			return super.getAsDate();
		return elements.get(0).getAsDate();
	}

	@Override
	public final JsonElement getAsJSONElementAt(int index) {
		return elements.get(index);
	}

	@Override
	public final JsonArray getAsJSONArrayAt(int index) {
		return elements.get(index).getAsJSONArray();
	}

	@Override
	public final JsonObject getAsJSONObjectAt(int index) {
		return elements.get(index).getAsJsonObject();
	}

	@Override
	public final boolean getAsBooleanAt(int index) {
		return elements.get(index).getAsBoolean();
	}

	@Override
	public final Number getAsNumberAt(int index) {
		return elements.get(index).getAsNumber();
	}

	@Override
	public final String getAsStringAt(int index) {
		return elements.get(index).getAsString();
	}

	@Override
	public final double getAsDoubleAt(int index) {
		return elements.get(index).getAsDouble();
	}

	@Override
	public final float getAsFloatAt(int index) {
		return elements.get(index).getAsFloat();
	}

	@Override
	public final long getAsLongAt(int index) {
		return elements.get(index).getAsLong();
	}

	@Override
	public final int getAsIntAt(int index) {
		return elements.get(index).getAsInt();
	}

	@Override
	public final byte getAsByteAt(int index) {
		return elements.get(index).getAsByte();
	}

	@Override
	public final char getAsCharacterAt(int index) {
		return elements.get(index).getAsCharacter();
	}

	@Override
	public final BigDecimal getAsBigDecimalAt(int index) {
		return elements.get(index).getAsBigDecimal();
	}

	@Override
	public final BigInteger getAsBigIntegerAt(int index) {
		return elements.get(index).getAsBigInteger();
	}

	@Override
	public final short getAsShortAt(int index) {
		return elements.get(index).getAsShort();
	}

	@Override
	public final Date getAsDateAt(int index) {
		return elements.get(index).getAsDate();
	}

	protected List<JsonElement> getItemsAsList() {
		return Collections.unmodifiableList(this.elements);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(AVG_OBJ_LENGTH * elements.size());
		builder.append("[");
		for (JsonElement e : elements) {
			if (e instanceof JsonNull)
				continue;
			builder.append(e.toString()).append(",");
		}
		if (builder.charAt(builder.length() - 1) == ',') {
			builder.deleteCharAt(builder.length() - 1);
		}
		builder.append("}");
		return builder.toString();
	}
}
