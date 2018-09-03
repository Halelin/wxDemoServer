package com.xms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xms.dao.UserWxDao;



@Controller
@RequestMapping("/wxLogin")
@ResponseBody
public class LoginWxController {
	@Resource
	UserWxDao userwxDao;	
	@RequestMapping("/Login")	
	@SuppressWarnings("rawtypes")
	public String getExternalJsonData(String code){
		StringBuffer document = null ;  
		BufferedReader reader = null;  
		String line = null;
		try {
			code ="https://api.weixin.qq.com/sns/jscode2session?appid=wx73f98cc9f35ba77d"
					+ "&secret=b8b0df39f493554a57ad149ceb41d2f9"
					+ "&js_code="+code+""
					+ "&grant_type=authorization_code";
			URL url = new URL(code);
			document = new StringBuffer();
			URLConnection conn = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = reader.readLine()) != null) {
				document.append(line);
			}
		} catch (Exception e) {
			throw new RuntimeException("获取外部数据失败，原因：" + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return String.valueOf(document);
	}	
	
}
