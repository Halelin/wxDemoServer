package com.xms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xms.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(
		HttpServletRequest request,
			HttpServletResponse response, 
				Object handler) throws Exception {
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){			
	//		判断是否是Ajax异步请求
			String XRequested = request
				.getHeader("X-Requested-With");
			if("XMLHttpRequest".equals(XRequested)){
				response.getWriter().print("IsAjax");
				
			}else{
				response.sendRedirect(request
					.getContextPath()+"/login/toLogin");
			}

			System.out.println("拦截请求");
			return false;			
		}else{
			System.out.println("请求通过");
			return true;			
		}
		
	}
	
}
