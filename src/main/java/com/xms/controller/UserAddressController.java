package com.xms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xms.dao.UserAddressDao;
import com.xms.entity.UserAddress;

@Controller
@RequestMapping("/address")
@ResponseBody
public class UserAddressController {
	@Resource
	UserAddressDao ad;
	@RequestMapping("/save")
	public void save(UserAddress useraddress){
		System.out.println("address"+useraddress.getAddress());
		System.out.println("city"+useraddress.getCity());
		System.out.println("district"+useraddress.getDistrict());
		System.out.println("id"+useraddress.getId());
		System.out.println("mailcode"+useraddress.getMailCode());
		System.out.println("name"+useraddress.getName());
		System.out.println("openid"+useraddress.getOpenId());
		System.out.println("phone"+useraddress.getPhoneNum());
		System.out.println("provice"+useraddress.getProvince());		
		ad.save(useraddress);
	}
	
	@RequestMapping("/selectAddressByAddressId")
	public UserAddress selectAddressByAddressId(Integer id){
		return ad.selectAddressByAddressId(id);
	}
	@RequestMapping("/selectAddressByOpenId")
	public List<UserAddress> sdf(String openId){
		System.out.println(openId);
		return ad.selectAddressByOpenId(openId);
	}
	
	@RequestMapping("/update")
	public void update(UserAddress useraddress){
		System.out.println("address"+useraddress.getAddress());
		System.out.println("city"+useraddress.getCity());
		System.out.println("district"+useraddress.getDistrict());
		System.out.println("id"+useraddress.getId());
		System.out.println("mailcode"+useraddress.getMailCode());
		System.out.println("name"+useraddress.getName());
		System.out.println("openid"+useraddress.getOpenId());
		System.out.println("phone"+useraddress.getPhoneNum());
		System.out.println("provice"+useraddress.getProvince());
		ad.update(useraddress);
	}
	@RequestMapping("/deleteAddressById")
	public void deleteAddressById(Integer id){
		ad.deleteAddressById(id);
	}
}
