package com.ylw.net.utils.http;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

public class HttpParamers {
	private Log log = LogFactory.getLog(HttpParamers.class);

	public void initHeaders(Map<String, String> headers) {
	}

	public void initParams(Map<String, String> params) {
	}

	public void initPostParams(Map<String, String> postParams) {
	}

	public void onRespone(String content) throws Exception {
	}

	protected void initSubClsHeaders(Map<String, String> headers) {

	}

	protected void initSubClsParams(Map<String, String> params) {

	}

	protected void initSubClsPostParams(Map<String, String> postParamsMap) {

	}

	public final String getUrl(String url) throws URISyntaxException {
		Map<String, String> paramsMap = new HashMap<String, String>();
		initSubClsParams(paramsMap);
		initParams(paramsMap);

		URIBuilder builder = new URIBuilder(url);
		builder.setCharset(Charset.forName("UTF-8"));
		List<NameValuePair> httpParams = new LinkedList<NameValuePair>();
		for (String key : paramsMap.keySet()) {
			httpParams.add(new BasicNameValuePair(key, paramsMap.get(key)));
		}
		Collections.sort(httpParams, new Comparator<NameValuePair>() {
			@Override
			public int compare(NameValuePair nameValuePair,
					NameValuePair nameValuePair2) {
				return nameValuePair.getName().compareToIgnoreCase(
						nameValuePair2.getName());
			}
		});
		builder.addParameters(httpParams);
		url = builder.toString();
		// Log.debug("URL:" + url);
		return url;
	}

	public final Header[] getHeaders() {
		Map<String, String> headerMap = new HashMap<String, String>();
		// headerMap.put(HTTP.USER_AGENT, "Android-Up366test-Moblie 1.0");
		// headerMap.put("Accept-Encoding", "gzip,deflate");
		// headerMap.put("Charset", "UTF-8");
		initSubClsHeaders(headerMap);
		initHeaders(headerMap);

		int i = 0;
		int headerLength = headerMap.size();
		Header[] headers = new Header[headerLength];
		Iterator<Entry<String, String>> it = headerMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> next = it.next();
			headers[i++] = new BasicHeader(next.getKey(), next.getValue());
		}
		return headers;
	}

	public final HttpEntity getEntity() {
		Map<String, String> postParamsMap = new HashMap<String, String>();

		initSubClsPostParams(postParamsMap);
		initPostParams(postParamsMap);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> it = postParamsMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Entry<String, String> next = it.next();
			params.add(new BasicNameValuePair(next.getKey(), next.getValue()));
		}
		StringEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
		// entity.setChunked(true);

		return entity;
	}

	public void onError(ErrInfo<Throwable> e) {
		log.error(e.getInfo(), e.getObj());
	}

}
