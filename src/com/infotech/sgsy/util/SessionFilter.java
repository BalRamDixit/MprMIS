/*package com.infotech.sgsy.util;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang.RandomStringUtils;

import com._3iinfotech.pykka.homePageContent.HomePageContentService;
import com._3iinfotech.pykka.homePageContent.HomePageContentServiceImpl;
import com._3iinfotech.pykka.login.LoginMasterDAOImpl;
import com._3iinfotech.pykka.master.configuration.ConfigurationService;
import com._3iinfotech.pykka.master.configuration.ConfigurationServiceImp;
import com._3iinfotech.pykka.master.titleDetails.TitleServiceImpl;
import com._3iinfotech.pykka.util.utilDBMethod.UtilService;
import com._3iinfotech.pykka.util.utilDBMethod.UtilServiceImp;





public class SessionFilter implements HttpSessionListener,ServletContextListener {

	public static int counter = 0;
	int cnt1=0;
	int cnt2=0; 

	protected boolean setToken(HttpSession session){			
		String token=RandomStringUtils.randomAlphanumeric(25);
		session.setAttribute("TRACKERID", token);
		return true;
	}
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		//TitleServiceImpl service=new TitleServiceImpl();
		//HomePageContentService homeService = new HomePageContentServiceImpl();
		System.out.println("...............CREATED...............");
		int i= 0;
		String language="en";
		HttpSession session = event.getSession();
		try{
		language=(String)session.getAttribute("lang");
		if(language==null)
			language="en";
		session.setAttribute("titleList", service.getActiveTitlesHome(language));
		session.setAttribute("latestNewsData", homeService.getCompositeTitleUploadedListForHomePage("56", language));
		session.setAttribute("achievementsData", homeService.getCompositeTitleUploadedListForHomePage("57", language));
		session.setAttribute("quickLinks",homeService.getQuickLinksForHomePage(language));
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setToken(session);
		

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("...............DISTROYED...............");
		HttpSession objSess = event.getSession();
		String userId= (String)objSess.getAttribute("userId");
		LoginMasterDAOImpl dao=new LoginMasterDAOImpl();
		try {
			if(userId !=null)
				dao.logout(userId,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (objSess != null && userId !=null ) {
			Enumeration sessionValues = objSess.getAttributeNames();
			while (sessionValues.hasMoreElements()) {
				String sessionAttributes = (String) sessionValues.nextElement();
				//System.out.println("sessionAttributes :" + sessionAttributes);
				objSess.removeAttribute(sessionAttributes);				
			}
			objSess.invalidate();
		}
	
	}
	
	 Application Startup Event 
	public void	contextInitialized(ServletContextEvent ce) {
		System.out.println("Server Started.In Servlet Listner Code");
		
		try {
		UtilService utilService  = new UtilServiceImp();
		TitleServiceImpl service=new TitleServiceImpl();
		ConfigurationService conService=new ConfigurationServiceImp();
		HomePageContentService homeService = new HomePageContentServiceImpl();
		ce.getServletContext().setAttribute("commonURL", utilService.getCommonURLs());
		ce.getServletContext().setAttribute("languages", utilService.getLanguage());
		
		//ce.getServletContext().setAttribute("titleList", service.getActiveTitles("en"));
		String path=ce.getServletContext().getRealPath("/pykkaUpLoadedDocument");
		
		// Automatically setting the context path for windows and linux
		
		if(System.getProperty("file.separator").equals("\\")) {
			path = path.replaceAll("\\\\", "\\\\\\\\");
			path=path.concat("\\\\");
		}else
			if(System.getProperty("file.separator").equals("/")) {
				path = path.replaceAll("////", "////////");
				path=path.concat("////");
			}
			
		conService.updateConfigurationParameterValueByName("UPLOADED_DOCUMENT_REAL_PATH", path, "ADMIN");
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	 Application Shutdown	Event 
	public void	contextDestroyed(ServletContextEvent ce) {}

	
	
	

}
*/