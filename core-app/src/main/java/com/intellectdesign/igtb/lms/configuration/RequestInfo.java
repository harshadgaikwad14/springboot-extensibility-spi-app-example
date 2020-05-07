package com.intellectdesign.igtb.lms.configuration;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestInfo {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestInfo.class);

	public Map<String, String> getRequestInfo(final HttpServletRequest request) {
		LOGGER.info("********* REQUEST INFORAMTION **************");

		final Map<String, String> requestInfoMap = new HashMap<>();
		requestInfoMap.put("method", request.getMethod());
		requestInfoMap.put("uri", request.getRequestURI());
		requestInfoMap.put("client", request.getRemoteAddr());

		final Map<String, String[]> params = request.getParameterMap();
		final Iterator<String> i = params.keySet().iterator();
		while (i.hasNext()) {
			final String key = (String) i.next();
			final String value = ((String[]) params.get(key))[0];

			requestInfoMap.put(key, value);
		}

		final Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			final String key = (String) headerNames.nextElement();
			final String value = request.getHeader(key);

			requestInfoMap.put(key, value);
		}

		for (Entry<String, String> map : requestInfoMap.entrySet()) {

			LOGGER.info("****> Name : {} - Value : {} ", map.getKey(), map.getValue());
		}
		
		return requestInfoMap;
	}

}
