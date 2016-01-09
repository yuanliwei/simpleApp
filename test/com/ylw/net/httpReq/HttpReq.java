package com.ylw.net.httpReq;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.net.utils.FileUtil;
import com.ylw.net.utils.http.HttpParamers;
import com.ylw.net.utils.http.HttpUtil;

public class HttpReq {

	public static void main(String[] args) {
		new HttpReq().testHttpReq();
	}

	public void testHttpReq() {
		// String url = "http://wap.cabnhina.com/index.asp";
		String url = "http://wap.cabnhina.com/login.asp?id=1";
//		String url = "http://wap.cabnhina.com/add_1.asp";
		HttpUtil.get(url, new HttpParamers() {
			@Override
			public void initHeaders(Map<String, String> headers) {
				// TODO Auto-generated method stub
				super.initHeaders(headers);
				headers.put("Range", "bytes=360-18446744073709551615");
			}

			@Override
			public void initParams(Map<String, String> params) {
				// TODO Auto-generated method stub
				super.initParams(params);
			}

			@Override
			public void initPostParams(Map<String, String> postParams) {
				postParams.put("id", "1");
				postParams.put("logonCardNum", "456654");
				postParams.put("netType", "1254");
				postParams.put("xcard5", "454545454");
				postParams.put("randomId", "445");
				postParams.put("cbRemember", "1");
			}

			@Override
			public void onRespone(String content)
					throws UnsupportedEncodingException {
				Log log = LogFactory.getLog(getClass());
				log.info(content);
//				String fullPath = "C:/Users/y/Desktop/cmb.html";
//				FileUtil.saveFullPathFile(fullPath, content);
			}

		});
	}
}
