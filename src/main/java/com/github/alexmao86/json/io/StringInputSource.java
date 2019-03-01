package com.github.alexmao86.json.io;

import java.io.IOException;

/**
 * ClassName: StringInputSource <br/>
 * 
 * @author
 */
public final class StringInputSource extends AbstractCharSequenceInputSource {
	private final String src;

	public StringInputSource(final String src) {
		super();
		this.src = src;
	}

	@Override
	public void skipWhitespace() throws IOException {
		while (address < src.length()) {
			char chr = src.charAt(address);
			if (chr > 32 && chr < 127) {
				break;
			}
			address++;
		}
	}

	@Override
	public char getChar() throws IOException {
		if (address < src.length())
			return src.charAt(address);
		throw new IOException("EOF String source");
	}

	@Override
	public void move() throws IOException {
		if (address >= src.length())
			throw new IOException("EOF String source");
		address++;
	}

	@Override
	public void move(int n) throws IOException {
		if (address >= src.length() - n)
			throw new IOException("EOF String source");
		address += n;
	}

	@Override
	public char previousChar() throws IOException {
		if (address - 1 < 0)
			throw new IOException("SOF String source");
		return src.charAt(address - 1);
	}

	@Override
	public char tryPreviousChar() {
		if (address - 1 < 0)
			return NULL_CHAR;
		return src.charAt(address - 1);
	}

	public char getCharAndMove() throws IOException {
		if (address < src.length()) {
			char chr = src.charAt(address);
			address++;
			return chr;
		}
		throw new IOException("EOF String source");
	}

	@Override
	public boolean available() {
		return address < src.length();
	}

	@Override
	public String remaining() {
		return src.substring(address);
	}

	@Override
	public char tryNextChar() {
		if (address + 1 > src.length() - 1)
			return NULL_CHAR;
		return src.charAt(address + 1);
	}
}
