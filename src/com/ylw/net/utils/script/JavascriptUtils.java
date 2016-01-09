package com.ylw.net.utils.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.net.utils.FileUtil;

/**
 * 调用javascript代码
 * 
 * @author ylw
 *
 */
public class JavascriptUtils {
	private static Log log = LogFactory.getLog(JavascriptUtils.class);

	public static Invoke getInvoke(String... files) {
		return getInvoke(null, files);
	}

	public static Invoke getInvoke(Object jsObj, String... files) {
		return getInvoke("jsObj", jsObj, files);
	}

	public static Invoke getInvoke(String jsObjName, Object jsObj,
			String... files) {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("javascript");
		try {
			for (String file : files) {
				engine.eval(FileUtil.getSrcJsString(file));
			}
			if (jsObj != null)
				engine.put(jsObjName, jsObj);
			Invocable invocable = (Invocable) engine;
			return new Invoke(invocable);
		} catch (ScriptException ex) {
			log.error(ex.getMessage(), ex);
		}
		return null;
	}
}
