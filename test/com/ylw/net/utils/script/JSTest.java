package com.ylw.net.utils.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ylw.net.utils.FileUtil;

public class JSTest {
	private static Log log = LogFactory.getLog(JSTest.class);

	@Test
	public void testMyJsUtils() {
		Invoke invoke = JavascriptUtils.getInvoke(new JsObj(), "test.js",
				"testCallJava.js");
		String result = invoke.exec("display", "ylw");
		log.debug(result);
	}

	/**
	 * 测试JS调用java方法
	 */
	@Test
	public void testJsCallJava() {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("javascript");
		try {
			engine.eval(FileUtil.getSrcJsString("test.js"));
			engine.eval(FileUtil.getSrcJsString("testCallJava.js"));
			JsObj jsObj = new JsObj();
			engine.put("jsObj", jsObj);
			Invocable invocable = (Invocable) engine;
			String result = (String) invocable.invokeFunction("display", "ylw");
			log.debug(result);
		} catch (ScriptException ex) {
			log.error(ex.getMessage(), ex);
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 测试调用JS文件里的方法
	 * 
	 * @throws ScriptException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testJSFile() throws ScriptException, NoSuchMethodException {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("javascript");
		engine.eval(FileUtil.getSrcJsString("test.js"));
		engine.eval(FileUtil.getSrcJsString("testCallJava.js"));
		JsObj jsObj = new JsObj();
		engine.put("jsObj", jsObj);
		Invocable invocable = (Invocable) engine;
		String result = (String) invocable.invokeFunction("display", "ylw");
		log.debug(result);

	}

	/**
	 * 在java中调用js，jdk1.6中有加载js引擎类，然后由它来调用js方法。 并通过JDK平台给script的方法中的形参赋值
	 * 
	 * @throws ScriptException
	 * @throws NoSuchMethodException
	 * */
	@Test
	public void testJSMethod() throws NoSuchMethodException, ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();

		ScriptEngine se = sem.getEngineByName("js");
		// String script = "function say(){ return 'hello,'" + name + "; }";
		String script = "function say(){ return 'hello ylw!'; }";
		se.eval(script);
		Invocable inv2 = (Invocable) se;
		String res = (String) inv2.invokeFunction("say", "ylw");
		log.debug(res);
	}

	/**
	 * 测试JS代码
	 * 
	 * @throws ScriptException
	 */
	@Test
	public void testJSCode() throws ScriptException {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("javascript");
		String str = "2*1.3";
		Double d = (Double) engine.eval(str);
		Integer i = d.intValue();
		System.out.println(i);
	}

	public static class JsObj {
		public void display() {
			log.debug("js : say - hello");
		}
	}
}
