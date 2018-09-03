package com.xms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xms.dao.UserDao;
import com.xms.entity.User;

@Controller
@RequestMapping("/login")
public class LoginController {	
	public LoginController(){
		System.out.println("LOginController init");
	}
	@Resource
	private UserDao userDao;
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){
		
		User loginUser = userDao.findUserByEmail(user.getEmail());
		
		if(loginUser == null || !loginUser
			.getPassword().equals(user.getPassword())){
			//
			request.getSession()
				.setAttribute("message","用户名或密码错误");
			return "redirect:/login/toLogin";
		}else{
			request.getSession().setAttribute("user",loginUser);
			return "redirect:/main/toIndex";
		}
		
	}
	
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request){
		
		request.getSession().setAttribute("user",null);
		
		return "redirect:/main/toIndex";
		
	}
	
}
