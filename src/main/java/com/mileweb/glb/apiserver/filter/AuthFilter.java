package com.mileweb.glb.apiserver.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mileweb.glb.apiserver.security.ApiAuthHandler;
import com.mileweb.glb.apiserver.security.IAuthHandler;

public class AuthFilter implements Filter {
	
	List<IAuthHandler> handlers = new ArrayList<IAuthHandler>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		handlers.add(new ApiAuthHandler());
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		IAuthHandler handler = getAvailableHandler(httpRequest);
		handler.authenticate(httpRequest, httpResponse, chain);
		
	}
	
	private IAuthHandler getAvailableHandler(HttpServletRequest request) {
		for(IAuthHandler handler: handlers) {
			if(handler.supports(request)) {
				return handler;
			}
		}
		
		return new IAuthHandler() {

			@Override
			public void authenticate(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
				chain.doFilter(request, response);
			}

			@Override
			public boolean supports(HttpServletRequest request) {
				return true;
			}
			
		};
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
