package com.mileweb.glb.apiserver.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author tongh
 *
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ApplicationContextUtil.context = context;
	}
	
	public static ApplicationContext getApplicationContext() {
		return context;
	}

}
