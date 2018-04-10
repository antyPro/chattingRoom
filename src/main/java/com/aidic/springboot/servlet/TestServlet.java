package com.aidic.springboot.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("========servlet初始化啦========");
	}
	
	protected void doGet(HttpServletRequest req,HttpServletResponse rps)throws IOException {
		doPost(req, rps);
		
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse rps) throws IOException {
		rps.setContentType("text/html;charset=utf-8");
		rps.getWriter().write("its my servlet service");
		System.out.println("servlet servicing！！！");
		
	}

}
