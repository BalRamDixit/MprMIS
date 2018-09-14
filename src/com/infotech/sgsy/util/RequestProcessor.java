package com.infotech.sgsy.util;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.infotech.sgsy.login.LoginMasterAction;
import com.infotech.sgsy.login.LoginMasterDAOImpl;


public class RequestProcessor extends org.apache.struts.tiles.TilesRequestProcessor{

	private boolean isTokenValid(HttpServletRequest request,String aevFlag,String pagingValue){
		if(request.getAttribute("VALIDATED")!=null){
			return true;
		}	
		HttpSession session=null;
		session=request.getSession();

		String reqToken=request.getParameter("reqtrack");			
		if(reqToken!=null && (reqToken.trim()).equals(session.getAttribute("TRACKERID"))){			
			if(pagingValue==null)
			  setToken(session);
			request.setAttribute("VALIDATED","true");
			return true;					
		}
		String headerID=request.getParameter("Header");	
		if(headerID!=null)
			return true;
		return false;
	}

	private void setToken(HttpSession session){
		String token=RandomStringUtils.randomAlphanumeric(25);
		session.setAttribute("TRACKERID",token);
	}

	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	protected boolean processRoles ( HttpServletRequest request, 
			HttpServletResponse response, ActionMapping mapping ) throws IOException, ServletException{
		logger.info("RequestProcessor:processRoles Starting Function ");
		HttpSession ses=request.getSession();
		String trackId = request.getParameter("reqtrack");
		String pagingValue=request.getParameter("paging");
			if(trackId !=null  && trackId.trim().length() != 0  ){
				if(!isTokenValid(request, "",pagingValue)){
					 try { 
						 	new LoginMasterAction().logOut(mapping, null, request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return redirect ( request, response, "" );
				}
			}
		return true;	
	}
	

	private boolean redirect ( HttpServletRequest request, HttpServletResponse response, String page ) throws IOException{
		response.sendRedirect ( request.getContextPath() + page );
		return false;
	}
}
