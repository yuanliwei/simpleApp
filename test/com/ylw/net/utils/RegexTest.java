package com.ylw.net.utils;

import org.junit.Test;

public class RegexTest {
	@Test
	public void testReg() {
		String s = "asd\r\nsd\\f'fdf";
		System.out.println(s);
		System.out.println(s.replaceAll("\r", "=\\\\\\\\r="));
		System.out.println(s.replaceAll("\r","\\\\\\r").replaceAll("\n","\\\\\\n").replaceAll("'","\\\\'"));
		System.out.println(s.replaceAll("\\\\", "\\\\\\\\"));

	}

}
