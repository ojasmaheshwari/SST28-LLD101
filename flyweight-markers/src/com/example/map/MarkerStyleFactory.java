package com.example.map;

import java.util.HashMap;
import java.util.Map;

public class MarkerStyleFactory {

	private final Map<String, MarkerStyle> cache = new HashMap<>();

	public MarkerStyle get(String shape, String color, int size, boolean filled) {
		String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
		if (cache.containsKey(key)) {
			return cache.get(key);
		} else {
			MarkerStyle markerStyle = new MarkerStyle(shape, color, size, filled);
			cache.put(key, markerStyle);
			return markerStyle;
		}
	}

	public int cacheSize() {
		return cache.size();
	}
}
