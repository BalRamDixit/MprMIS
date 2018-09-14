package com.infotech.sgsy.login;


import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;



public class SessionExpiryServlet extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
	
		if(session != null)
		{
			session.invalidate();
		}
		request.getRequestDispatcher("WEB-INF/pages/common/sessionExpire.jsp").forward(request,response);
	
	}
	
	public void destroy(){
		
	}
}
