package com.xms.servlet;

import java.io.Serializable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.xms.thread.AccessTokenThread;

public class InitServlet extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
		public void init(ServletConfig config) throws ServletException
	    {	System.out.println("启动线程");
	        new Thread(new AccessTokenThread()).start(); 
	    }
}
