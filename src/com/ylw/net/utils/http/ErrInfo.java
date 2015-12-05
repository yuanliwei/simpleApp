package com.ylw.net.utils.http;

public class ErrInfo<T> {
	private int code;
	private String info;
	private T obj;

	public ErrInfo(int code) {
		this.code = code;

	}

	public ErrInfo(int code, String info) {
		this.code = code;
		this.info = info;
	}

	public ErrInfo(int code, String info, T obj) {
		this.code = code;
		this.info = info;
		this.obj = obj;
	}

	public int getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}

	public T getObj() {
		return obj;
	}

	@Override
	public String toString() {
		return "ErrInfo [code=" + code + ", info=" + info + ", obj=" + obj
				+ "]";
	}

}
