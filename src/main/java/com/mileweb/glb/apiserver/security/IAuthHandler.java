package com.mileweb.glb.apiserver.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthHandler {
	public void authenticate(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

	public boolean supports(HttpServletRequest request);
}
