package com.ylw.net.utils.script;

public class JSStringUtil {
	public static String coverToJSString(String rawStr) {
		return rawStr.replaceAll("\r", "\\\\\\r").replaceAll("\n", "\\\\\\n")
				.replaceAll("'", "\\\\'");
	}
	public static String coverToHtmlJSString(String rawStr) {
		return rawStr.replaceAll("\\\\", "\\\\\\\\").replaceAll("\r", "\\\\\\r").replaceAll("\n", "\\\\\\n")
				.replaceAll("'", "\\\\'");
	}
}
