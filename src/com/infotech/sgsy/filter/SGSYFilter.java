package com.infotech.sgsy.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.RandomStringUtils;
/**
 * @Description : Filter for every action
 * @author 37362
 */
public class SGSYFilter implements Filter{


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
		
	
	
	
	
		public void doFilter(ServletRequest req, ServletResponse res,
				FilterChain chain) throws IOException, ServletException {
			

			// TODO Auto-generated method stub
		
			HttpServletRequest request=(HttpServletRequest)req;
			HttpServletResponse response=(HttpServletResponse)res;
			
			response.setHeader("Cache-Control","no-cache"); 
			response.setHeader("Pragma","no-cache"); 
			response.setDateHeader ("Expires", -1);

			
			/*//System.out.println("In Do-Filter");
			HttpSession session=request.getSession();
			session = request.getSession(false);
			System.out.println("ContextPath==="+request.getContextPath());
			System.out.println("AuthType==="+request.getAuthType());
			System.out.println("Method==="+request.getMethod());
			System.out.println("PathInfo"+request.getPathInfo());
			System.out.println("PotoCol"+request.getProtocol());
			System.out.println("RemoteAddress"+request.getRemoteAddr());
			System.out.println("RequestedSessionID"+request.getRequestedSessionId());
			System.out.println("ContentType"+request.getContentType());
			System.out.println("URI"+request.getRequestURI());
			System.out.println("URL"+request.getRequestURL());
			System.out.println("URL"+request.getParameter("methodName"));
			System.out.println("URL"+request.getParameter("methodName"));
			*/
			

			if(request.getSession().isNew()){
				request.getRequestDispatcher("WEB-INF/jsp/common/sessionExpired.jsp").forward(request,response);
			}else{
				
				chain.doFilter(req,res);
			}
		}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
