package com.mileweb.glb.apiserver.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.mileweb.glb.apiserver.entity.SuccessInfo;
import com.mileweb.glb.apiserver.util.ApplicationContextUtil;

public abstract class BaseController { 
	public SuccessInfo getI18NSuccessInfo() {
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
}
