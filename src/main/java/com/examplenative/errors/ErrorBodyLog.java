package com.examplenative.errors;

import com.google.gson.Gson;

public class ErrorBodyLog {
	
	private String path;
	private String method;
	private Object body;
	private String exception;
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	public String marshall() {
		return new Gson().toJson(this);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

}
