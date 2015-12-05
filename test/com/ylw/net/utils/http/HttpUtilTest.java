package com.ylw.net.utils.http;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

public class HttpUtilTest {
	private Log log;

	@Before
	public void initLog() {
		log = LogFactory.getLog(HttpUtilTest.class);
	}

	@Test
	public void testGet() {
		HttpUtil.get("http://www.baidu.com", new HttpParamers() {
			@Override
			public void onRespone(String content) {
//				log.debug(content);
				log.debug("qwwerr");
			}
		});
	}
}
