package com.ylw.net.utils.storage.ormliteutils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

public class OrmLiteUtils {
	@SuppressWarnings("rawtypes")
	private static Map<Class, Dao> map = new HashMap<Class, Dao>();

	private static Log log = LogFactory.getLog(OrmLiteUtils.class);

	@SuppressWarnings("unchecked")
	protected static <T> Dao<T, Object> getDao(Class<?> clazz) {
		Dao<T, Object> dao = map.get(clazz);
		if (dao != null) {
			return dao;
		}
		try {
			dao = (Dao<T, Object>) DaoManager.createDao(
					OrmLiteConnection.getConnection(), clazz);
			TableUtils.createTableIfNotExists(
					OrmLiteConnection.getConnection(), clazz);
			map.put(clazz, dao);
		} catch (SQLException e) {
			log.error("createDAO error ...", e);
		}
		return dao;
	}

	public static <T> void save(Object obj) {
		Dao<Object, Object> dao = getDao(obj.getClass());
		try {
			dao.create(obj);
		} catch (SQLException e) {
			log.error("save Object error ...", e);
		}
	}

	public static void update(Object obj) {
		Dao<Object, Object> dao = getDao(obj.getClass());
		try {
			dao.update(obj);
		} catch (SQLException e) {
			log.error("update Object error ...", e);
		}
	}

	public static void saveOrUpdate(Object obj) {
		Dao<Object, Object> dao = getDao(obj.getClass());
		try {
			dao.createOrUpdate(obj);
		} catch (SQLException e) {
			log.error("saveOrUpdate Object error ...", e);
		}
	}

	public static <T> void saveOrUpdateAll(List<T> list) {
		log.debug("begin save list");
		if (list == null || list.size() == 0) {
			return;
		}
		Dao<Object, Object> dao = getDao(list.get(0).getClass());
		try {
			for (T obj : list) {
				dao.createOrUpdate(obj);
			}
		} catch (SQLException e) {
			log.error("saveOrUpdate List error ...", e);
		}
		log.debug("end save list");
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> queryForAll(Class<T> clazz) {
		try {
			return (List<T>) getDao(clazz).queryForAll();
		} catch (SQLException e) {
			log.error("query list error ...", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T queryForSameId(T obj) {
		try {
			return (T) getDao(obj.getClass()).queryForSameId(obj);
		} catch (SQLException e) {
			log.error("query list error ...", e);
		}
		return null;
	}

	public static <T> List<T> queryForEq(String fieldName, String col,
			Class<T> clazz) {
		try {
			Dao<T, Object> dao = getDao(clazz);
			return dao.queryForEq(fieldName, col);
		} catch (SQLException e) {
			log.error("queryForEq list error ...", e);
		}
		return null;
	}

	public static <T> int delete(List<T> lists) {
		if (lists == null || lists.size() == 0) {
			return 0;
		}
		try {
			return getDao(lists.get(0).getClass()).delete(lists);
		} catch (SQLException e) {
			log.error("query list error ...", e);
		}
		return 0;
	}

	public static <T> int delete(T obj) {
		try {
			return getDao(obj.getClass()).delete(obj);
		} catch (SQLException e) {
			log.error("query list error ...", e);
		}
		return 0;
	}
}
