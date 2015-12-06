package com.ylw.net;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.helpers.Loader;

import com.alibaba.fastjson.JSON;
import com.ylw.net.model.BaiduFans;
import com.ylw.net.model.UrlBaiduFans;
import com.ylw.net.utils.PropUtils;
import com.ylw.net.utils.TaskUtils;
import com.ylw.net.utils.cache.CacheUtil;
import com.ylw.net.utils.http.HttpParamers;
import com.ylw.net.utils.http.HttpUtil;
import com.ylw.net.utils.storage.ormliteutils.OrmLiteUtils;

public class MainClass {
	private static Log log = LogFactory.getLog(MainClass.class);

	public static void main(String[] args) {
		PropUtils.load();
		// TODO Auto-generated method stub
		log.info("hello world!");
		// String url =
		// "http://yun.baidu.com/pcloud/friend/getfollowlist?query_uk=4180462582&limit=24&start=0&channel=chunlei&clienttype=0&web=1";
		// String url =
		// "http://yun.baidu.com/pcloud/friend/getfanslist?query_uk=2050031688&limit=25&start=1&channel=chunlei&clienttype=0&web=1";
		// String url =
		// "http://yun.baidu.com/pcloud/friend/getfanslist?query_uk=2050031688&limit=25&start=1&channel=chunlei&clienttype=0&web=1";
		// // String url="http://www.baidu.com";
		// HttpUtil.get(url, new HttpParamers() {
		// @Override
		// public void onRespone(String content) {
		// // TODO Auto-generated method stub
		// log.info(content);
		// UrlBaiduFans urlFans = JSON.parseObject(content,
		// UrlBaiduFans.class);
		// List<BaiduFans> bdfs = urlFans.getBaiduFans();
		// OrmLiteUtils.saveOrUpdateAll(bdfs);
		// log.info("save list size : " + bdfs.size() + " over ...");
		// }
		// });
		log.info("hello world!");

		CacheUtil.initCache();
		CacheUtil.put("sdf", "dddddddddddddddddddddddddddd");
		// PropUtils.putInt("cur_page", 1);

		loader(PropUtils.getInt("cur_page", 0));

		log.info("PropUtils.get(\"234\") : " + PropUtils.get("234"));
		PropUtils.put("wsd", "548565");
		log.info("PropUtils.get(\"wsd\") : " + PropUtils.get("wsd"));
		PropUtils.store();
		log.info("end..............");
	}

	private static void loader(int page) {
		// TODO Auto-generated method stub
		log.info("load page " + page + " begin ...");
		PropUtils.putInt("cur_page", page);
		String url = "http://yun.baidu.com/pcloud/friend/getfanslist?query_uk=2050031688&limit=24&channel=chunlei&clienttype=0&web=1";
		HttpUtil.get(url, new HttpParamers() {
			@Override
			public void initParams(Map<String, String> params) {
				params.put("start", String.valueOf(page * 24 + 1));
			}

			@Override
			public void onRespone(String content) {
				// TODO Auto-generated method stub
				log.info(content);
				UrlBaiduFans urlFans = JSON.parseObject(content,
						UrlBaiduFans.class);
				List<BaiduFans> bdfs = urlFans.getBaiduFans();
				TaskUtils.getExcutor().execute(new Runnable() {

					@Override
					public void run() {
						OrmLiteUtils.saveOrUpdateAll(bdfs);
						log.info("save page " + page + " list size : "
								+ bdfs.size() + " over ...");
					}
				});
				if (page * 25 < urlFans.getTotal_count()) {
					TaskUtils.getSingleExcutor().execute(new Runnable() {

						@Override
						public void run() {
							loader(page + 1);
						}
					});
				}
			}
		});
	}
}
