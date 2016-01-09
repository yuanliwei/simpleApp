package com.ylw.net.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.TextUtils;

import com.alibaba.fastjson.util.IOUtils;

public class FileUtil {
	private static Log log = LogFactory.getLog(FileUtil.class);

	public static boolean Createfile(String path, String filename) {
		return Createfile(path + "/" + filename);
	}

	public static boolean Createfile(String fullpath) {
		File file = new File(fullpath);
		File path = file.getParentFile();
		if (!path.exists()) {
			path.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				log.error("create file error ...", e);
				return false;
			}
		}
		return true;
	}

	public static boolean isExistFile(String filefullname) {
		return new File(filefullname).exists();
	}

	public static void saveString(String fullName, String msg) {
		String path = fullName;
		Createfile(path);
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(path));
			os.write(msg.getBytes("utf-8"));
			os.flush();
		} catch (IOException e) {
			log.error("write file error ...", e);
			IOUtils.close(os);
		}
	}

	public static String getString(String fullName) {
		String path = fullName;
		Createfile(path);
		InputStream in = null;
		try {
			in = new FileInputStream(new File(path));
			final ByteArrayBuffer buffer = new ByteArrayBuffer(in.available());
			final byte[] tmp = new byte[4096];
			int l;
			while ((l = in.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			return new String(buffer.toByteArray(), "utf-8");
		} catch (IOException e) {
			log.error("write file error ...", e);
		} finally {
			IOUtils.close(in);
		}
		return "";
	}

	public static String getSrcJsString(String fileName) {
		InputStream inStream = null;
		try {
			inStream = getResJSFile(fileName);
			final ByteArrayBuffer buffer = new ByteArrayBuffer(
					inStream.available());
			final byte[] tmp = new byte[4096];
			int l;
			while ((l = inStream.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			return new String(buffer.toByteArray(), "utf-8");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			IOUtils.close(inStream);
		}
		return fileName;
	}

	public static InputStream getResJSFile(String fileName) {
		try {
			String name = "js/" + fileName;
			Class<? extends FileUtil> clazz = new FileUtil().getClass();
			URL url = clazz.getClassLoader().getResource(name);
			log.debug(url.toString());
			return clazz.getClassLoader().getResourceAsStream(name);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static URL getResUrl(String fileName) {
		String name = fileName;
		Class<? extends FileUtil> clazz = new FileUtil().getClass();
		URL url = clazz.getClassLoader().getResource(name);
		log.debug(url.toString());
		return url;
	}

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
