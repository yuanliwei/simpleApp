package com.ylw.net.utils.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class EhCacheTest {
	Log log = LogFactory.getLog(EhCacheTest.class);

	@Test
	public void testEhCache() {
		CacheUtil.initCache();
		CacheUtil.put("123", "hfhfhfhfhfggdgdfgddfdffdfd");
		CacheUtil.put("1231", "hfhfhfhfhfggdgdfgddfdffdfd");
		CacheUtil.put("1232", "hfhfhfhfhfggdgdfgddfdffdfd");
		CacheUtil.put("1233", "hfhfhfhfhfggdgdfgddfdffdfd");
		CacheUtil.put("1234", "hfhfhfhfhfggdgdfgddfdffdfd");
		Object value = CacheUtil.get("123");
		log.debug("value is :" + value);
		value = CacheUtil.get("1233");
		log.debug("value is :" + value);
		value = CacheUtil.get("1233");
		log.debug("value is :" + value);
		value = CacheUtil.get("1232");
		log.debug("value is :" + value);
		value = CacheUtil.get("1233");
		log.debug("value is :" + value);
	}
}
