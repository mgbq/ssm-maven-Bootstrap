package com.javen.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class HandlerInterceptor implements
		org.springframework.web.servlet.HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String url=request.getRequestURI();
		
		
		/*if(request.getSession().getAttribute("Teacher") == null 
				|| request.getSession().getAttribute("Student") == null
				|| request.getSession().getAttribute("Manage") == null)
		{
		}*/
		
		return true;
		
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
