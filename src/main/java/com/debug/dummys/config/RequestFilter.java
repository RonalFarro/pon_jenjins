package com.debug.dummys.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class RequestFilter implements Filter {
	
	@Override
	public void destroy() {
		//destroy
	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
	        throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

		filterChain.doFilter(servletRequest, response);
	}
	
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {}

}
