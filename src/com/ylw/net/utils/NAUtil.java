package com.ylw.net.utils;
/*
 * 文 件 名:  NAUtil.java
 * 修 改 人:   袁立位
 * 修改时间:  2015年11月24日
 */

import org.apache.http.util.TextUtils;

/**
 * 编码转换工具类<br/>
 * <ol>
 * <li>ascii2native(String sAscii)</li>
 * <li>native2ascii(String sNative)</li>
 * </ol>
 * 
 * @author 袁立位
 * @date 2015年11月24日 下午2:59:31
 */
public class NAUtil {
    public static String ascii2native(String sAscii) {
        if (TextUtils.isEmpty(sAscii))
            return "";
        StringBuilder sb = new StringBuilder();
        String[] words = sAscii.split("\\\\u");
        sb.append(words[0]);
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            sb.append((char) Integer.parseInt(word.substring(0, 4), 16));
            if (word.length() > 4) {
                sb.append(word.substring(4));
            }
        }
        return sb.toString();
    }

    public static String native2ascii(String sNative) {
        if (TextUtils.isEmpty(sNative))
            return "";
        char[] chars = sNative.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c > 127) {
                sb.append("\\u");
                sb.append(Integer.toHexString(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
