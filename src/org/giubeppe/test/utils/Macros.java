package org.giubeppe.test.utils;

import java.util.Map;
import java.util.Map.Entry;

public class Macros {
	static public String expandMacros(String text, Map<String, String> macroValues) {
		
		for (Entry<String, String> keyValue: macroValues.entrySet()) {
			String key = keyValue.getKey();
			String val = keyValue.getValue();
			
			String regexStr = key;
			
			text = text.replaceAll(regexStr, val);
		}
		
		return text;
	}
}
