package com.ylw.net.utils.http;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ylw.net.utils.script.Invoke;
import com.ylw.net.utils.script.JavascriptUtils;
import com.ylw.net.utils.script.JSTest.JsObj;

public class GetHtmlPage {
	private Log log = LogFactory.getLog(HttpUtilTest.class);

	@Test
	public void testGetPage() {
		HttpUtil.get("http://ratedata.gaincapital.com", new HttpParamers() {
			@Override
			public void onRespone(String content)
					throws UnsupportedEncodingException {
				content = content.replaceAll("\r","\\\\\\r").replaceAll("\n","\\\\\\n").replaceAll("'","\\\\'");
				log.info(content);
				// log.debug("content")
				Invoke invoke = JavascriptUtils.getInvoke("test.js");
				String result = invoke.exec("getUrls", content);
				log.info(result);;
			}
		});
	}
}
