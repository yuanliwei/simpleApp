package com.ylw.net.utils.http;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.junit.Test;

import com.ylw.net.utils.script.Invoke;
import com.ylw.net.utils.script.JSStringUtil;
import com.ylw.net.utils.script.JavascriptUtils;
import com.ylw.net.utils.script.JSTest.JsObj;

public class GetHtmlPage {
	private Log log = LogFactory.getLog(HttpUtilTest.class);

	@Test
	public void testGetPage() {
		String baseUrl = "http://ratedata.gaincapital.com";
		HttpUtil.get(baseUrl, new HttpParamers() {
			@Override
			public void onRespone(String content)
					throws UnsupportedEncodingException {
				content = JSStringUtil.coverToJSString(content);
				log.info(content);
				// log.debug("content")

				Invoke invoke = JavascriptUtils.getInvoke("test.js");
				String result = invoke.exec("getUrls", baseUrl, content);
				log.info(result);
			}
		});
	}
}

class NameValue implements NameValuePair {
	private String name;
	private String value;

	public NameValue(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
