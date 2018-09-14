package com.infotech.skills.registration.pia;
/**
 * Created By: Laxman Singh
 * Description: Checks captcha value 
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CaptchaCheck extends HttpServlet{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			log.info("At CaptchaCheck");
			String status = "false";
			String captcha = request.getParameter("captcha");
			String answer = (String)request.getSession().getAttribute("CorrectAnswer");
			if(!captcha.equals("") && captcha.equals(answer)){
				status="true";
			}
			response.getWriter().print(status);
		}
	}

