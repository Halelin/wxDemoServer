package com.xms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xms.dao.UserWxDao;
import com.xms.entity.UserWx;

@Controller
@RequestMapping("/wxRegiste")
@ResponseBody
public class RegisteWxController {
	@Resource
	UserWxDao userwxDao;
	
	@RequestMapping("/checkRegiste")	
	public Boolean checkRegiste(String id){
		UserWx userWx = userwxDao.findUserWxByid(id);
		if(userWx!=null){
			return true;
		}
		return false;
	}
	
	@RequestMapping("/registe")
	public Boolean registe(UserWx userwx){
		userwxDao.save(userwx);
		return true;		
	}
	
}
