package com.ylw.net.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.junit.Test;

public class PinYinTest {
	@Test
	public void testPinUtil() {
		String ch = "汉语转拼音测试";
		char[] ca = ch.toCharArray();
		for (char c : ca) {
			String[] py = PinyinHelper.toHanyuPinyinStringArray(c);
			for (String pinyin : py) {
				System.out.print(pinyin + " ");
			}
			System.out.println();
		}
	}
}
