package com.github.alexmao86.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * ClassName: KeyArrayLinkedHashMap is one enhancement of LinkedHashMap that key
 * are synchronzed in array list, so we can get value by index<br/>
 * date: 2017年10月11日 下午1:57:49 <br/>
 * 
 * @author
 */
public class KeyArrayLinkedHashMap extends LinkedHashMap<String, JsonElement> {
	private static final long serialVersionUID = 1L;
	private List<String> keyList = new ArrayList<String>();
	private volatile boolean synch = false;

	public JsonElement getAt(int index) {
		if (!synch)
			synch();
		return this.get(keyList.get(index));
	}

	/*
	 * synch: synch to key list
	 */
	private void synch() {
		keyList.clear();
		for (String k : this.keySet()) {
			keyList.add(k);
		}
		synch = true;
	}

	@Override
	public JsonElement put(String key, JsonElement value) {
		synch = false;
		return super.put(key, value);
	}

	@Override
	public JsonElement remove(Object key) {
		synch = false;
		return super.remove(key);
	}

	@Override
	public boolean remove(Object key, Object value) {
		synch = false;
		return super.remove(key, value);
	}

	@Override
	public JsonElement putIfAbsent(String key, JsonElement value) {
		synch = false;
		return super.putIfAbsent(key, value);
	}

	@Override
	public boolean replace(String key, JsonElement oldValue, JsonElement newValue) {
		synch = false;
		return super.replace(key, oldValue, newValue);
	}

	@Override
	public JsonElement replace(String key, JsonElement value) {
		synch = false;
		return super.replace(key, value);
	}

	@Override
	public JsonElement merge(String key, JsonElement value,
			BiFunction<? super JsonElement, ? super JsonElement, ? extends JsonElement> remappingFunction) {
		synch = false;
		return super.merge(key, value, remappingFunction);
	}

	@Override
	public void putAll(Map<? extends String, ? extends JsonElement> m) {
		synch = false;
		super.putAll(m);
	}
}
