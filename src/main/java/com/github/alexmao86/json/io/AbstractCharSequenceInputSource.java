package com.github.alexmao86.json.io;

/**
 * ClassName: AbstractCharSequenceInputSource <br/>
 * 
 * @author
 */
public abstract class AbstractCharSequenceInputSource implements CharSequenceInputSource {
	protected int address = 0;

	@Override
	public int getCursor() {
		return address;
	}
}
