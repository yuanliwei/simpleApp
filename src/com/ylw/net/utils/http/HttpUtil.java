package com.ylw.net.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);

	public static void get(String url, HttpParamers paramers) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			RequestConfig config = RequestConfig.custom()
					.setCircularRedirectsAllowed(false)
					.setConnectionRequestTimeout(5000).setConnectTimeout(5000)
					.setContentCompressionEnabled(true)
					.setExpectContinueEnabled(true).setMaxRedirects(5)
					.setRedirectsEnabled(true)
					.setRelativeRedirectsAllowed(true).setSocketTimeout(10000)
					.build();
			String url_ = paramers.getUrl(url);
			log.debug("GET:" + url_);
			HttpGet httpGet = new HttpGet(url_);
			httpGet.setConfig(config);
			httpGet.setHeaders(paramers.getHeaders());
			CloseableHttpResponse response = client.execute(httpGet);
			responseHandler(paramers, response);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			paramers.onError(new ErrInfo<Throwable>(-1, e.getMessage(), e));
		}
	}

	public static void post(String url, HttpParamers paramers) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			RequestConfig config = RequestConfig.custom()
					.setCircularRedirectsAllowed(false)
					.setConnectionRequestTimeout(5000).setConnectTimeout(5000)
					.setContentCompressionEnabled(true)
					.setExpectContinueEnabled(true).setMaxRedirects(5)
					.setRedirectsEnabled(true)
					.setRelativeRedirectsAllowed(true).setSocketTimeout(10000)
					.build();
			String url_ = paramers.getUrl(url);
			log.debug("GET:" + url_);
			HttpPost httpPost = new HttpPost(url_);
			httpPost.setConfig(config);
			httpPost.setHeaders(paramers.getHeaders());
			httpPost.setEntity(paramers.getEntity());
			CloseableHttpResponse response = client.execute(httpPost);
			responseHandler(paramers, response);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			paramers.onError(new ErrInfo<Throwable>(-1, e.getMessage(), e));
		}
	}

	private static void responseHandler(HttpParamers paramers,
			CloseableHttpResponse response) throws IOException,
			UnsupportedEncodingException {
		try {
			log.debug(response.getStatusLine().toString());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity1 = response.getEntity();
				byte[] buffer = EntityUtils.toByteArray(entity1);
				String jsonContent = new String(buffer, "UTF-8");
				log.debug(jsonContent);
				EntityUtils.consume(entity1);
				paramers.onRespone(jsonContent);
			} else {
				HttpEntity entity1 = response.getEntity();
				byte[] buffer = EntityUtils.toByteArray(entity1);
				String jsonContent = new String(buffer, "UTF-8");
				log.debug(jsonContent);
				EntityUtils.consume(entity1);
				paramers.onRespone(response.getStatusLine().toString()
						+ "\r\n\r\n" + jsonContent);
			}

		} finally {
			response.close();
		}
	}
}
