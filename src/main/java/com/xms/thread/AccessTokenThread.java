package com.xms.thread;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xms.dao.AccessTokenDao;
import com.xms.dao.UserDao;
import com.xms.entity.AccessToken;
import com.xms.util.AccessTokenUtil;
import com.xms.util.SpringContextUtil;

public class AccessTokenThread implements Runnable {
	public static AccessToken accessToken;
	@Override
	public void run() {
		// TODO Auto-generated method stub
	        while(true)
	        {	
	            AccessToken token = AccessTokenUtil.freshAccessToken(); // 莉主ｾｮ菫｡譛榊苅蝎ｨ蛻ｷ譁ｰaccess_token
				if(token != null){
				    accessToken = token;
				    System.out.println("get an accessToken");
				    System.out.println("accessToken: "+token.getAccess_token());
//				    String xml ="springmvc.xml";
//				    ApplicationContext ac = new ClassPathXmlApplicationContext(xml);
//				    AccessTokenDao tokenDao = (AccessTokenDao)ac.getBean("accessTokenDao");		    
				    
//				    AccessTokenDao tokenDao =(AccessTokenDao)SpringContextUtil.getBean(AccessTokenDao.class);
				    AccessTokenDao tokenDao =(AccessTokenDao)SpringContextUtil.getBean("accessTokenDao");
				    tokenDao.delAll();
				    tokenDao.save(token);
				   System.out.println("写入accessToken："+token.getAccess_token());
				}else{
				    System.out.println("get access_token failed------------------------------");
				}	             
	            try{
	                if(null != accessToken){
	                    Thread.sleep((accessToken.getExpires_in() - 200) * 1000);    // 莨醍悛7000遘�
	                	//Thread.sleep(5000);
	                	System.out.println("SleepThreadId:"+Thread.currentThread().getId());
	                	Thread.currentThread().getId();
	                }else{
	                    Thread.sleep(60 * 1000);    // 螯よ棡access_token荳ｺnull�ｼ�60遘貞錘蜀崎執蜿�
	                	//Thread.sleep(300000000);
	                }
	            }catch(InterruptedException e){
	                try{
	                    Thread.sleep(60 * 1000);
	                }catch(InterruptedException e1){
	                    e1.printStackTrace();
	                }
	            }	       
	    }
	}

}
