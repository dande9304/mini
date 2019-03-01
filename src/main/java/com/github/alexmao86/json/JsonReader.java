package com.github.alexmao86.json;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Stack;

import com.github.alexmao86.json.io.CharSequenceInputSource;
import com.github.alexmao86.json.io.StringInputSource;


public final class JsonReader extends JsonSystem {
	private final class IndexedChar {
		char chr;
		int location;

		private IndexedChar(char chr, int location) {
			super();
			this.chr = chr;
			this.location = location;
		}

		@Override
		public String toString() {
			return chr + "@" + location;
		}
	}

	public final JsonElement parse(final String src) throws IOException, JsonParseException {
		if (src == null || src.trim().length() == 0) {
			return JsonNull.INSTANCE;
		}
		final CharSequenceInputSource source = new StringInputSource(src);
		Stack<IndexedChar> stack = new Stack<IndexedChar>();
		JsonElement element = nextJSONElement(source, stack);
		if (!stack.isEmpty()) {
			throw new JsonParseException(src, stack.pop().location);
		}
		if (source.available()) {
			throw new JsonParseException(source.remaining());
		}
		return element;
	}

	@SuppressWarnings("unchecked")
	public final <T> T parse(final String src, Class<T> type) throws IOException, JsonParseException {
		JsonElement e = parse(src);
		JsonDeserializer<T> jsonDeserializer = this.queryJsonDeserializer(type);
		return (T) jsonDeserializer.deserialize(e, type, this);
	}

	@SuppressWarnings("unchecked")
	public final <T> Collection<T> parseAsCollection(final String src, Class<T> type)
			throws IOException, JsonParseException {
		if (Map.class.isAssignableFrom(type) || Collection.class.isAssignableFrom(type)) {
			throw new IllegalArgumentException("direct nested java generic type is not supported");
		}
		JsonElement e = parse(src);
		@SuppressWarnings("rawtypes")
		JsonDeserializer jsonDeserializer = this.queryJsonDeserializer(Collection.class);
		return (Collection<T>) jsonDeserializer.deserialize(e, Collection.class, this, type);
	}

	/**
	 * nextJSONValue: when we find one whole
	 * 
	 * @param source
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	private final JsonElement nextJSONUncertain(CharSequenceInputSource source, Stack<IndexedChar> stack, int type)
			throws IOException, JsonParseException {
		source.skipWhitespace();
		if (type == JsonElement.STRING) {
			String src = nextString(source);
			return new JsonUncertain(src, type);
		}
		// non-string
		StringBuilder elementBuilder = new StringBuilder();
		while (source.available()) {
			char chr = source.getChar();
			if (chr == ' ' || chr == ',' || chr == ':' || chr == ']' || chr == '}' || chr == '[' || chr == '{') {
				break;
			}
			elementBuilder.append(chr);
			source.move();
		}
		return new JsonUncertain(elementBuilder.toString());
	}

	/**
	 * 
	 * @param source
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 * @since JDK 1.8
	 */
	private final JsonElement nextJSONArray(CharSequenceInputSource source, Stack<IndexedChar> stack)
			throws IOException, JsonParseException {
		JsonArray array = new JsonArray();
		source.move();// skip "["
		source.skipWhitespace();
		boolean firstEntry = true;// flag to mark first entry
		while (source.available()) {

			char chr = source.getChar();

			if (chr == ']') {
				stack.pop();// end of array
				source.move();
				break;
			}

			if (!firstEntry) {
				skipSyntaxChar(source, ',');// skipping entry splitting from second one
				chr = source.getChar();
			}

			if (chr == '{') {// array element is object
				stack.push(new IndexedChar(chr, source.getCursor()));
				array.add(nextJSONObject(source, stack));
			} else if (chr == '[') {// nested array
				stack.push(new IndexedChar(chr, source.getCursor()));
				array.add(nextJSONArray(source, stack));
			} else if (chr == '\"') {// now this array is one array of string
				array.add(nextJSONUncertain(source, stack, JsonElement.STRING));
			} else {// consider non-string array element, such as numbric
				array.add(nextJSONUncertain(source, stack, JsonElement.UNCERTAIN));
			}
			firstEntry = false;
			source.skipWhitespace();
		}
		return array;
	}

	/**
	 * readJSONObject:
	 * 
	 * @param src
	 * @param pointer
	 * @param ret
	 * @throws JsonParseException
	 * @throws IOException
	 */
	private final JsonObject nextJSONObject(final CharSequenceInputSource source, Stack<IndexedChar> stack)
			throws JsonParseException, IOException {
		JsonObject ret = new JsonObject();
		source.move();// skip "{"
		source.skipWhitespace();
		boolean firstEntry = true;// flag to mark first entry
		while (source.available()) {
			char chr = source.getChar();
			if (chr == '}') {
				stack.pop();
				source.move();
				break;
			}

			if (!firstEntry) {
				skipSyntaxChar(source, ',');// skipping entry splitting from second one
				chr = source.getChar();
			}

			source.skipWhitespace();
			String key = nextString(source);
			skipSyntaxChar(source, ':');
			JsonElement value = nextJSONElement(source, stack);

			ret.add(key, value);
			firstEntry = false;
			source.skipWhitespace();
		}
		return ret;
	}

	private final void skipSyntaxChar(final CharSequenceInputSource source, final char c)
			throws IOException, JsonParseException {
		source.skipWhitespace();
		char syntax = source.getCharAndMove();
		if (syntax != c) {// check element splitting
			throw new JsonParseException(source);
		}
		source.skipWhitespace();// eat more possible whitespace
	}

	/**
	 * nextJSONElement:
	 * 
	 * @param src
	 * @param pointer
	 * @return
	 * @throws IOException
	 * @throws JsonParseException
	 */
	private final JsonElement nextJSONElement(final CharSequenceInputSource source, Stack<IndexedChar> stack)
			throws IOException, JsonParseException {
		source.skipWhitespace();

		char chr = source.getChar();
		if (chr == 0) {
			return JsonNull.INSTANCE;// null
		}
		if (chr == '{') {
			stack.push(new IndexedChar(chr, source.getCursor()));
			return nextJSONObject(source, stack);
		} else if (chr == '[') {
			stack.push(new IndexedChar(chr, source.getCursor()));
			return nextJSONArray(source, stack);
		} else if (chr == '\"') {
			return nextJSONUncertain(source, stack, JsonElement.STRING);
		}
		return nextJSONUncertain(source, stack, JsonElement.UNCERTAIN);
	}

	/**
	 * nextString: TODO ignore double quotes option
	 * 
	 * @param src
	 * @param pointer
	 * @return
	 * @throws JsonParseException
	 */
	private final String nextString(final CharSequenceInputSource source) throws IOException, JsonParseException {
		source.skipWhitespace();
		StringBuilder builder = new StringBuilder();
		char chr = source.getCharAndMove();

		if (chr == '\"') {// strict mode
			while (source.available()) {
				chr = source.getChar();
				if (chr == '\"' && source.tryPreviousChar() != '\\') {// meet end of string, aware of \"
					source.move();
					break;
				}
				source.move();
				builder.append(chr);
			}
		} else if (chr == '\'') {// strict mode
			while (source.available()) {
				chr = source.getChar();
				if (chr == '\'' && source.tryPreviousChar() != '\\') {// meet end of string, aware of \'
					source.move();
					break;
				}
				source.move();
				builder.append(chr);
			}
		} else {// non strict mode
			builder.append(chr);
			while (source.available()) {
				chr = source.getChar();
				if (chr == ' ' || chr == ',' || chr == ':' || chr == ']' || chr == '}' || chr == '[' || chr == '{') {
					break;
				}
				source.move();
				builder.append(chr);
			}
		}

		return unescape(builder.toString());
	}
}
