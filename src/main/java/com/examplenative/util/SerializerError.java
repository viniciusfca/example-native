package com.examplenative.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.examplenative.errors.ErrorBodyLog;

import jakarta.servlet.http.HttpServletRequest;

public class SerializerError {

	private SerializerError(){}

	public static String serializerErrorBody(HttpServletRequest request, WebRequest webRequest, String exception) {
		
		ErrorBodyLog errorBody = new ErrorBodyLog();

		errorBody.setPath(request.getServletPath());
		errorBody.setMethod(request.getMethod());
		errorBody.setException(exception);
		errorBody.setBody(webRequest.getAttribute("body", RequestAttributes.SCOPE_REQUEST));

		return errorBody.marshall();

	}
}
