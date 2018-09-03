package com.xms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.xms.entity.AccessToken;


public class AccessTokenUtil {
	public static AccessToken freshAccessToken(){
		AccessToken token =new AccessToken();
		Map<String,Object> tokenMap = Json2Map.json2Map(getTokenJson());
		
//		System.out.println(tokenMap);
		token.setAccess_token((String)tokenMap.get("access_token"));
		token.setExpires_in((Integer)tokenMap.get("expires_in"));
//		System.out.println("expires_in"+token.getExpires_in());
//		System.out.println("setAccess_token"+token.getAccess_token());
		return token;
	}
	
	
	public static String getTokenJson(){
		StringBuffer document = null ;  
		BufferedReader reader = null;  
		String line = null;
		try {
			String code ="https://api.weixin.qq.com/cgi-bin/token?grant_type="
					+ "client_credential&appid=wx73f98cc9f35ba77d&secret=b8b0df39f493554a57ad149ceb41d2f9";					
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
		String to =String.valueOf(document);
		//System.out.println(to);	
		return to;
	}
}
