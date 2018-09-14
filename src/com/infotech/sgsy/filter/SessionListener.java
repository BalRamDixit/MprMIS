package com.infotech.sgsy.filter;


import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.infotech.sgsy.login.LoginMasterDAOImpl;
import com.infotech.sgsy.login.LoginVO;

public class SessionListener implements HttpSessionListener,ServletContextListener{
	
    static Logger log = Logger.getRootLogger();
	
	/*
	 *     This method will generate the session variable
	 */
	protected boolean setToken(HttpSession session){					
		String token=RandomStringUtils.randomAlphanumeric(25);	
		session.setAttribute("TRACKERID", token);	
		log.info("TRACKERID Inside Listener   "  + token);
		return true;
	}
	
	
	/* 
	 * this method set session token 
	 */
	
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("..........SESSION.....CREATED...............");		
		HttpSession session = event.getSession();	
		setToken(session);			
		log.info("TRACKERID Inside sessionCreated method   "  + session);
	}

	
	public void sessionDestroyed(HttpSessionEvent event) {	
		System.out.println("..........SESSION.....DESTROYED...............");	
		HttpSession objSess = event.getSession();
		LoginVO loginVO= (LoginVO)objSess.getAttribute("loginVO");			
		try {		
			 if(loginVO !=null){						
				//LoginMasterDAOImpl.logout(loginVO.getUserid().toUpperCase(),loginVO.getIpAddress());			
				LoginMasterDAOImpl.logoutForServer();
			}		 
			if (objSess != null && loginVO !=null) {			
				Enumeration sessionValues = objSess.getAttributeNames();			
				while (sessionValues.hasMoreElements()) {				
					String sessionAttributes = (String) sessionValues.nextElement();
					objSess.removeAttribute(sessionAttributes);				
				}		
				objSess.invalidate();
			}			
		} catch (Exception e) {		
			log.info("Error while session destroyed : " + e.getCause()+ "" + e.getMessage() );		
			e.printStackTrace();
		}
	}


	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("Context Destroyed");
		Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("deregistering jdbc driver: "+driver);
            } catch (SQLException e) {
            	 System.out.println("Error deregistering jdbc driver: "+driver);
            }

        }
	}
 
	/*@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {	
			LoginMasterDAOImpl.logoutForServerRestart();	
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}			
	}
	*/
	

	/* Application Startup Event */
	public void	contextInitialized(ServletContextEvent ce) {
		//System.out.println("Server Started.In Servlet Listner Code");
		//try {
			/*	
		UtilService utilService  = new UtilServiceImp();
		ConfigurationService conService=new ConfigurationServiceImp();
		ce.getServletContext().setAttribute("commonURL", utilService.getCommonURLs());
		ce.getServletContext().setAttribute("languages", utilService.getLanguage());
		//ce.getServletContext().setAttribute("titleList", service.getActiveTitles("en"));
		String path=ce.getServletContext().getRealPath("/FileFolder");
		// Automatically setting the context path for windows and linux
		if(System.getProperty("file.separator").equals("\\")) {
			path = path.replaceAll("\\\\", "\\\\\\\\");
			path=path.concat("\\\\");
		}else
			if(System.getProperty("file.separator").equals("/")) {
				path = path.replaceAll("////", "////////");
				path=path.concat("////");
			}	
		conService.updateConfigurationParameterValueByName("UPLOADED_DOCUMENT_REAL_PATH", path, "ADMIN");*/
		//}
		/*catch(Exception e){
			e.printStackTrace();
			
		}*/
	}


	
}
