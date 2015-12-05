package com.ylw.net;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.net.utils.cache.CacheUtil;
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
		String url="http://yun.baidu.com/pcloud/friend/getfollowlist?query_uk=4180462582&limit=24&start=0&bdstoken=2b6350dafcdc296bbb4237a9ffccb27d&channel=chunlei&clienttype=0&web=1";
//		String url="http://www.baidu.com";
		HttpUtil.get(url, new HttpParamers() {
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

		CacheUtil.initCache();
		CacheUtil.put("sdf", "dddddddddddddddddddddddddddd");
	}
}
