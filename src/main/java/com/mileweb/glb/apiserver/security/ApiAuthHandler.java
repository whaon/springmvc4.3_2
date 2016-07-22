package com.mileweb.glb.apiserver.security;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.cnc.portal.util.EncodeUtil;
import com.mileweb.glb.apiserver.util.ApplicationContextUtil;

public class ApiAuthHandler implements IAuthHandler {

	String[] browsers = { "Mozilla", "Opera", "Firefox", "MSIE", "Firefox", "Safari", "Chrome" };

	@Override
	public boolean supports(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");

		if (StringUtils.isBlank(userAgent)) {
			return true;
		}
		{
			for (String browser : browsers) {
				if (StringUtils.containsIgnoreCase(userAgent, browser)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void authenticate(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String auth = request.getHeader("Authorization");

		if (StringUtils.isNotEmpty(auth) && auth.length() > 6) {

			String date = request.getHeader("Date");
			if (StringUtils.isEmpty(date)) {
				send400(response);
				return;
			}

			auth = auth.substring(6, auth.length());

			auth = EncodeUtil.decodeBase64ToString(auth);

			String user = auth.split(":")[0];
			String pwd = auth.split(":")[1];

			Properties p = ApplicationContextUtil.getApplicationContext().getBean("userProperties", Properties.class);
			String generatePwd;
			try {
				generatePwd = DigestUtil.signHmacSHA1(p.getProperty(user), date);
			} catch (Exception e) {// won't happen
				e.printStackTrace();
				throw new ServletException("encryption error when authentication");
			}

			if (p.containsKey(user) && generatePwd.equals(pwd)) {
				chain.doFilter(request, response);
			} else {
				send406(response);
			}
		} else {
			send406(response);
		}

	}

	private void send400(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setHeader("WWW-Authenticate", "need Date in head");

	}

	private void send406(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("WWW-Authenticate", "Basic realm=\"GLB2.0 API SERVER\"");
	}

	public static void main(String[] args) {
		try {
			System.out.println(DigestUtil.signHmacSHA1("mileweb2016!@#", "Thu, 17 May 2012 19:37:58 GMT"));

			System.out.println(new Date());

			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
			System.out.println(sdf.format(new Date()));
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			System.out.println(sdf.format(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
