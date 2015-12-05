package com.ylw.net;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.net.utils.http.HttpParamers;
import com.ylw.net.utils.http.HttpUtil;

public class MainClass {
	private static Log log = LogFactory.getLog(MainClass.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		HttpUtil.get("http://www.baidu.com", new HttpParamers() {
			@Override
			public void onRespone(String content) {
				// TODO Auto-generated method stub
				super.onRespone(content);
				log.debug(content);
			}
		});
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
		log.debug("hello world!");
	}
}
