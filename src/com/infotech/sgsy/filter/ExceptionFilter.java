package com.infotech.sgsy.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.login.LoginVO;

public class ExceptionFilter implements Filter {
	
	 public void init(FilterConfig config) throws ServletException{
	 
	 }

	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
	 throws IOException, ServletException {
		 
		  HttpServletRequest req = (HttpServletRequest)request;
		  
		  HttpServletResponse res = (HttpServletResponse)response;
		
		  LoginVO loginVO =(LoginVO)req.getSession().getAttribute("loginVO");
		 
		 if(loginVO != null){
			 try {
				 
				String ipAddress=req.getRemoteAddr();
				 
				LoginMasterDAOImpl.logout(loginVO.getUserid().toUpperCase(),ipAddress);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login/pages/common/commonError.jsp");
		 
		 dispatcher.forward(req, res);
	 }
	 
	 public void destroy(){
	 }
	
	

}
