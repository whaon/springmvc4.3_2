package com.mileweb.glb.apiserver.support;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mileweb.glb.apiserver.exception.BizException;
import com.mileweb.glb.apiserver.exception.BizRuntimeException;
import com.mileweb.glb.apiserver.exception.ExceptionWrapper;
import com.mileweb.glb.apiserver.exception.ValidRuntimeException;
import com.mileweb.glb.apiserver.util.ApplicationContextUtil;

/**
 * 
 * @author tongh
 *
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {  
  
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception e) {  
        Map<String, String> model = new HashMap<String, String>();  
        
        if(e instanceof BizRuntimeException || e instanceof BizException) {
        	BizRuntimeException me = (BizRuntimeException) e;
        	
        	ExceptionWrapper wrapper = me.getExceptionWrapper();
        	if(wrapper == null) {
            	setModel(ExceptionWrapper.DEFAULT_CODE, e.getMessage(), model);
            	
        	} else {
        		String code = wrapper.getCode();
            	model.put("code", code);  
            	
            	String msg = wrapper.getMsg();
            	String i18nMsg = ApplicationContextUtil.getApplicationContext().getMessage(msg, me.getParam(), LocaleContextHolder.getLocale());
            	model.put("msg", i18nMsg);
        	}
        } else if(e instanceof ValidRuntimeException) {
        	ValidRuntimeException me = (ValidRuntimeException) e;
        	model.put("code", ExceptionWrapper.DEFAULT_VALID_CODE);  
        	model.put("msg", me.getMessage());
        } else {
        	setModel(ExceptionWrapper.DEFAULT_CODE, e.getMessage(), model);
        }
          
        return new ModelAndView("definiteJson", model);  
    }  
    
    private void setModel(String code, String msg, Map<String, String> model) {
    	String i18nMsg = ApplicationContextUtil.getApplicationContext().getMessage(msg, null, LocaleContextHolder.getLocale());
		
    	model.put("code", code);  
    	model.put("msg", i18nMsg);
    }
}  