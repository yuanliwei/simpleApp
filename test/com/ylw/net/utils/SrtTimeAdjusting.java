package com.ylw.net.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class SrtTimeAdjusting {
	Log log = LogFactory.getLog(SrtTimeAdjusting.class);

	/**
	 * 调整字幕文件的时间
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testAdjustingSrtTime() throws IOException, ParseException {
		String src = "G:/迅雷下载/电影/Ant-Man.2015.1080p.WEBRip.x264.AAC2.0-RARBG/蚁人.Ant-Man.2015.BluRay.720p.x264.2Audio.AC3-CnSCG.chs&eng.srt";
		String dst = "G:/迅雷下载/电影/Ant-Man.2015.1080p.WEBRip.x264.AAC2.0-RARBG/蚁人.Ant-Man.2015.BluRay.720p.x264.2Audio.AC3-CnSCG.chs&eng_addtime.srt";
		int mills = -7000;
		readAndwriteFile(src, dst, mills);
	}

	private void readAndwriteFile(String src, String dst, int mills)
			throws IOException, ParseException {
		InputStream inputStream = new FileInputStream(src);
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "GBK");
		OutputStream os = new FileOutputStream(dst);
		OutputStreamWriter writer = new OutputStreamWriter(os, "GBK");

		BufferedReader reader = new BufferedReader(inputStreamReader);
		String line;
		// 01:56:39,240
		Pattern pattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2},\\d{3}");
		while ((line = reader.readLine()) != null) {
			log.debug(line);
			Matcher matcher = pattern.matcher(line);
			String time1;
			String time2 = null;
			if (matcher.find()) {
				time1 = matcher.group();
				line = line.replace(time1, addTime(mills, time1));
				if (matcher.find()) {
					time2 = matcher.group();
					line = line.replace(time2, addTime(mills, time2));
				}
				log.debug(time1 + " " + time2);
			}
			writer.write(line);
			writer.append("\r\n");
		}
		reader.close();
		inputStream.close();
		writer.flush();
		writer.close();
	}

	private String addTime(int mills, String time) {
		String[] times = time.split("[:,]");
		log.debug(Arrays.toString(times));
		int h = Integer.valueOf(times[0]);
		int m = Integer.valueOf(times[1]);
		int s = Integer.valueOf(times[2]);
		int S = Integer.valueOf(times[3]);

		int milli = ((h * 60 + m) * 60 + s) * 1000 + S + mills;
		S = milli % 1000;
		s = milli / 1000 % 60;
		m = milli / 1000 / 60 % 60;
		h = milli / 1000 / 60 / 60;
		String result = String.format("%02d:%02d:%02d,%03d", h, m, s, S);
		return result;
	}
}
