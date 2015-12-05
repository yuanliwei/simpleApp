package com.ylw.net.utils.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

public class CacheUtil {
	private static Log log = LogFactory.getLog(CacheUtil.class);
	private static Cache cache = null;

	public static void initCache() {
		CacheManager manager = CacheManager.getInstance();
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setLogging(true);
		cacheConfiguration.name("Cache");
		cache = new Cache(cacheConfiguration);
		cache.setCacheManager(manager);
		cache.initialise();
		log.info("cache has initialised...");
	}

	public static Cache getCache() {
		return cache;
	}

	public static void put(String key, Object value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	public static Object get(String key) {
		Element ele = cache.get(key);
		if (ele != null) {
			log.debug("Element " + key + " hit count : " + ele.getHitCount());
			return ele.getObjectValue();
		}
		return null;
	}
}
