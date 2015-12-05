package com.ylw.net.utils;

import org.junit.Test;

public class PropUtilTest {
	@Test
	public void testPropUtil() {
		PropUtils.load();
		System.out.println(PropUtils.get("123"));
		PropUtils.put("234", "afddsf");
		System.out.println(PropUtils.get("234"));

		PropUtils.putInt("shuzi", 123);
		System.out.println(PropUtils.getInt("shuzi"));
		System.out.println(PropUtils.getInt("shuzi1"));
		PropUtils.store();
	}
}
