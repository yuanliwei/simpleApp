package com.ylw.net.utils.script;

import javax.script.Invocable;
import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Invoke {
	private static Log log = LogFactory.getLog(Invoke.class);

	private Invocable invocable;

	public Invoke(Invocable invocable) {
		this.invocable = invocable;
	}

	public String exec(String method, Object... params) {
		try {
			return (String) invocable.invokeFunction(method, params);
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage(), e);
		} catch (ScriptException e) {
			log.error(e.getMessage(), e);
		}
		return "error!";
	}
}
