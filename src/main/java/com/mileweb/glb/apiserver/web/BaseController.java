package com.mileweb.glb.apiserver.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mileweb.glb.apiserver.entity.SuccessInfo;
import com.mileweb.glb.apiserver.exception.BizRuntimeException;
import com.mileweb.glb.apiserver.util.ApplicationContextUtil;

public abstract class BaseController { 
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private  HttpServletResponse response;
	
	@Autowired
	ObjectMapper objectMapper;
	
	protected SuccessInfo getDefaultSuccessInfo() {
		SuccessInfo s = new SuccessInfo();
		s.setCode(100);
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		RequestContext requestContext = new RequestContext(request);
		
		System.out.println(RequestContextUtils.getLocale(request));
		
		System.out.println(request.getLocale());
		System.out.println(LocaleContextHolder.getLocale());
		
		System.out.println(requestContext.getMessage("suc"));
		
		System.out.println(ApplicationContextUtil.getApplicationContext().getMessage("suc", null, LocaleContextHolder.getLocale()));
		
		s.setMessage(requestContext.getMessage("suc"));
		
		return s;
	}
	
	protected void flushJson(Object o) {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		ServletOutputStream  pw = null;
		try {
			pw  = response.getOutputStream();
			pw.print(objectMapper.writeValueAsString(o));
			pw.flush();
		} catch (IOException e) {
			log.error("flush json error", e);
			throw new BizRuntimeException(e.getMessage());
		} finally {
			IOUtils.closeQuietly(pw);
		}
	}

	protected void flushDefaultSuccess(int eventId) {
		SuccessInfo info = this.getDefaultSuccessInfo();
		info.setEventId(eventId);
		this.flushJson(info);
	}
}
