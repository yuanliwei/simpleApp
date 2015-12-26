package com.ylw.net.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;

import com.alibaba.fastjson.util.IOUtils;

public class FileUtil {

	private static Log log = LogFactory.getLog(FileUtil.class);

	/**
	 * @description 把一段字符串保存到文件中
	 * @param fullPath
	 * @param data
	 * @return
	 * @author 袁立位
	 * @date 2015年7月17日 下午2:44:11
	 */
	public static void saveFullPathFile(String fullPath, String data) {
		createFile(fullPath);
		File file = new File(fullPath);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(data.getBytes("UTF-8"));
			fos.flush();
		} catch (IOException e) {
			log.error("saveFullPathFile : '" + fullPath + "' with '" + data
					+ "' error ...");
		} finally {
			IOUtils.close(fos);
		}
	}

	private static void createFile(String dir) {
		mkdirParentDir(dir);
		File file = new File(dir);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.error(dir, e);
			}
		}
	}

	/**
	 * 
	 * @description 根据路径创建父文件夹
	 * @param path
	 * @return
	 * @author 胡同涛
	 * @date 2015-5-12 上午11:25:43
	 */
	public static boolean mkdirParentDir(String path) {
		if (TextUtils.isBlank(path)) {
			return false;
		}
		return mkdirParentDir(new File(path));
	}

	/**
	 * 
	 * @description 创建父文件夹
	 * @param file
	 * @return
	 * @author 胡同涛
	 * @date 2015-5-12 上午11:23:56
	 */
	public static boolean mkdirParentDir(File file) {
		File parentDir = file.getParentFile();
		if (null != parentDir && !parentDir.exists()) {
			if (!parentDir.mkdirs())
				return false;
		}
		return true;
	}
}
