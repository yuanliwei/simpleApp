package com.ylw.net.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropUtils {
	private static String propPath = "properties.xml";
	private final static Properties properties = new Properties();

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static void put(String key, String value) {
		properties.put(key, value);
	}

	public static int getInt(String key) {
		return getInt(key, 0);
	}

	public static int getInt(String key, int defValue) {
		String value = properties.getProperty(key);
		int result = defValue;
		try {
			result = Integer.valueOf(value);
		} catch (NumberFormatException e) {
		}
		return result;
	}

	public static void putInt(String key, int value) {
		properties.put(key, String.valueOf(value));
	}

	public static float getFloat(String key) {
		return getFloat(key, 0);
	}

	public static float getFloat(String key, float defValue) {
		String value = properties.getProperty(key);
		float result = defValue;
		try {
			result = Float.valueOf(value);
		} catch (NumberFormatException e) {
		}
		return result;
	}

	public static void putFloat(String key, float value) {
		properties.put(key, String.valueOf(value));
	}

	public static void load() {
		InputStream inStream;
		try {
			inStream = new FileInputStream(propPath);
			// properties.load(inStream);
			properties.loadFromXML(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void store() {
		OutputStream out;
		try {
			out = new FileOutputStream(propPath);
			String comments = "程序的配置文件";
			// properties.store(out, comments);
			properties.storeToXML(out, comments, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
