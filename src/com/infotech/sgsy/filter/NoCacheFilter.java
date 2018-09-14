package com.infotech.sgsy.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.infotech.sgsy.exception.SystemFailureException;
import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.login.LoginVO;

/**
 * @author cvas2273
 *
 */
public class NoCacheFilter implements Filter {
	
	String host;
	
	String reportLink = "/shgReport.do";
	
	private static Logger log = Logger.getRootLogger();
	
	private FilterConfig filterConfig;

    public void init(FilterConfig config) throws ServletException {
    	
    	this.host = config.getInitParameter("host");   	
    	
    	
        this.filterConfig = config;
    }
   
    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }
    
    public void setFilterConfig (FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**@author cvas2273
	 * @param request
     * @throws SystemFailureException 
     * @throws IOException 
	 */
    public void doFilter (ServletRequest request,ServletResponse response,FilterChain chain) throws SystemFailureException {
    	
        try {
                
        	if (response instanceof HttpServletResponse) {                	
                    
                	HttpServletResponse httpresponse = (HttpServletResponse)response ;                    
                    
                	HttpServletRequest req=(HttpServletRequest)request ;
                	
                	HttpSession objSess = req.getSession();     
                	
                	LoginVO loginVO = null; 
                	
                	String trackId = null;              
                	
                	// get paging value only for pagination
                	String pagingValue = request.getParameter("paging");           
                	
                 
                	int ajaxMethodCount = 0;                	
                	
                	if(request.getParameter("reqtrack") != null && request.getParameter("reqtrack").trim().length() != 0 && pagingValue == null)
                		{
                		
                		System.out.println("trackid getParameter=="+request.getParameter("reqtrack"));
                		
                		//trackId = request.getParameter("reqtrack"); 
                		if(objSess.getAttribute("TRACKERID")!=null)
                			trackId=(String)objSess.getAttribute("TRACKERID");
                		
                		
                		System.out.println("trackid session=="+trackId);
                		
                		
                		}
                	
                	
                
                	else{
                		
                		if(req.getHeader("reqtrack") != null && req.getHeader("reqtrack").trim().length() != 0){
                			
                			trackId = req.getHeader("reqtrack");
                			
                			ajaxMethodCount = ajaxMethodCount +1;
                		} 
                		
                		System.out.println("trackid getHeader=="+req.getHeader("reqtrack"));
                    	
                	} 
        			
                	// handle pagination 
            		if(pagingValue != null && pagingValue.trim().length() != 0){
            			
            			
            			trackId = request.getParameter("reqtrack");   
            			
            			ajaxMethodCount = ajaxMethodCount +1;
            			
            		}
                	
                	 // Set the Cache-Control and Expires header
                	preventCaching(httpresponse);    
                	
                
                    if(trackId !=null  && trackId.trim().length() != 0  )   {  
                    	
                    	//Users request has expired
        				if(!isTokenValid(req, trackId,ajaxMethodCount)){       					
        					
        					handleIllegalAccess(req, httpresponse, loginVO, objSess);			
    						
        				}else{
        					
        					removeCookies(req, httpresponse);
        					
        					chain.doFilter(request,response);
        				}        					
        			
                    }else{
                    	
                    	removeCookies(req, httpresponse);
                    	
                    	if(req.getServletPath().equals(reportLink)){
                    		
                    		RequestDispatcher dispatcher = request.getRequestDispatcher(getUrl(req));
            				
            				dispatcher.forward(request, response);
                    	}else{
                    		//httpresponse.sendRedirect(host);
                    		
                    		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/main/index.jsp");

            				dispatcher.forward(request, response);	
                    	}
                    }                  
                }   
               
        }catch (Exception e) {	        	
        	
        	log.info("Error While Forwarding request inside NOcacheFilter"  + e.getMessage());
            
			try {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("applicationLevelException.do?methodName=getApplicationException");
				
				dispatcher.forward(request, response);
			
			} catch (ServletException e1) {
				
			} catch (IOException e1) {
				
			}      
        }
       
    }
    
    public void destroy() {
        this.filterConfig = null;
    }   

	
	/**@author cvas2273
	 * @param request
	 * @param aevFlag
	 * this method check user session for every request 
	 * @return 
	 */
	private boolean isTokenValid(HttpServletRequest request,String reqToken,int ajaxMethodCount){
		
		if(request.getAttribute("VALIDATED")!=null){
			return true;
		}	
		
		HttpSession session=null;
		
		session=request.getSession();

		//String reqToken=request.getParameter("reqtrack");		
		System.out.println("req token in isTokenValid== "+reqToken+" session getAttribute in is token valide =="+session.getAttribute("TRACKERID"));
		
		if(reqToken!=null && (reqToken.trim()).equals(session.getAttribute("TRACKERID"))){
			
			if(ajaxMethodCount == 0)
				setToken(session);
						
			request.setAttribute("VALIDATED","true");
			
			
			System.out.println("inside if of isTokenValid =="+reqToken+ " session "+session.getAttribute("TRACKERID"));
			
			return true;					
		}	
		
		return false;
	}

	/**@author cvas2273
	 * @param session
	 * this method generate session for every request 
	 */
	private void setToken(HttpSession session){
		
		String token=RandomStringUtils.randomAlphanumeric(25);
		
		session.setAttribute("TRACKERID",token);
		

		log.info("TRACKERID Inside setToken method of No Cache Filter   "  + token);
	}
	
	
	
	
	/**@author cvas2273
	 * Set the Cache-Control and Expires header
	 * @param response
	 */
	private void preventCaching(HttpServletResponse response){
		
		 // Set the Cache-Control and Expires header
        response.setHeader("Cache-Control", "no-cache") ;
        
        response.setHeader("Pragma", "No-cache");
        
        response.setDateHeader("Expires", 0);    
        
	}
	
	/** @author cvas2273
	 * create a url
	 * @param req
	 * @return
	 */
	public static String getUrl(HttpServletRequest req) {
	   /* String scheme = req.getScheme();             // http
	    String serverName = req.getServerName();     // hostname.com
	    int serverPort = req.getServerPort();        // 8080
*/	   // String contextPath = req.getContextPath();   // /mywebapp
	    String servletPath = req.getServletPath();   // /servlet/MyServlet
	    String pathInfo = req.getPathInfo();         // /a/b;c=123
	    String queryString = req.getQueryString();    // d=789	       
	   
	
	    // Reconstruct original requesting URL
	    String url = servletPath;
	    if (pathInfo != null) {
	        url += pathInfo;
	    }
	    if (queryString != null) {
	        url += "?"+queryString;
	    }	    
	   
	    return url;
	}
	
	
	private void handleIllegalAccess(HttpServletRequest request,HttpServletResponse response,LoginVO loginVO,HttpSession session){
		
		loginVO = (LoginVO) session.getAttribute("loginVO");

		try {

			if (loginVO != null && session != null) {

				//LoginMasterDAOImpl.logout(loginVO.getUserid(),request.getRemoteAddr());			
				
				LoginMasterDAOImpl.logoutForServer();

				Enumeration sessionValues = session.getAttributeNames();
					
				while (sessionValues.hasMoreElements()) {
					
					String sessionAttributes = (String) sessionValues.nextElement();

					session.removeAttribute(sessionAttributes);				
				}
				
				session.invalidate();
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/common/sessionExpire.jsp");

				dispatcher.forward(request, response);

			}else {
				//handle url copy & paste after window close 
				removeCookies(request, response);
				//response.sendRedirect(host);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/main/index.jsp");

				dispatcher.forward(request, response);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
			
       
	}
	
	/**@author cvas2273 
	 * this method remove the cookies on client site
	 * @param request
	 * @param response
	 */
	private void removeCookies(HttpServletRequest request,HttpServletResponse response){
		
		Cookie[] cookies = request.getCookies();
	
		for (int i = 0; i < cookies.length; i++) {
			
			Cookie killCookie = cookies[i];
			
			killCookie.setMaxAge(0);
			
			killCookie.setPath("/");			
			
			response.addCookie(killCookie);
		
		}


	}
	
	public static String getApplicationHostUrl(HttpServletRequest req) {
		
		    String scheme = req.getScheme();             // http
		    
		    String serverName = req.getServerName();     // hostname.com
		    
		    int serverPort = req.getServerPort();        // 8080
		    
		    String contextPath = req.getContextPath();   // /mywebapp		    
		    
		    String REQ = req.getRequestURI();   
		    
		    String url2 = req.getRequestURL().toString();   
		    
		    String url = scheme+"://"+serverName+":"+serverPort+contextPath;
		    
		    log.info("Application Host ::==> " + url);
		   
		    return url;
		}

}
