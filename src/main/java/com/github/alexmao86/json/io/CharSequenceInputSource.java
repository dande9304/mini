package com.github.alexmao86.json.io;

import java.io.IOException;

/**
 * ClassName: CharSequenceInputSource <br/>
 * 
 * @author 
 */
public interface CharSequenceInputSource {
	char NULL_CHAR = (char) 0;

	/**
	 * 
	 * skipWhitespace: skip whitespace at current position and auto maintain pointer
	 * 
	 */
	void skipWhitespace() throws IOException;

	/**
	 * 
	 * getChar: get char at current position and not move pointer
	 */
	char getChar() throws IOException;

	/**
	 * 
	 * move: move the pointer to next position
	 */
	void move() throws IOException;

	/**
	 * 
	 * move: move the pointer to next n position
	 */
	void move(int n) throws IOException;

	/**
	 * 
	 * previousChar: try to get char at previous position
	 */
	char previousChar() throws IOException;

	/**
	 * tryPreviousChar: try to check previous char without throw exception if has
	 * previous char, return it; otherwise return 0
	 */
	char tryPreviousChar();

	/**
	 * 
	 * getCharAndMove: get char at current position and move pointer to next
	 * position
	 */
	char getCharAndMove() throws IOException;

	/**
	 * available: check source is available
	 */
	boolean available();

	/**
	 * remaining: return remaining string of this input source
	 * 
	 * @return
	 */
	String remaining();

	/**
	 * tryNextChar: try to get next char if has char, return it; otherwise return 0
	 */
	char tryNextChar();

	/**
	 * getPointer: get current position reading
	 */
	int getCursor();

}
