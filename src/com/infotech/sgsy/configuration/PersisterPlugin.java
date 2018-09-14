/*
 * Created on Oct 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.infotech.sgsy.configuration;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.hibernate.HibernateException;


/**
 * @author Henry Martin
 * @since 3-Oct-@007
 * 
 * This class is used as a plug in class to perform certain operations
 * such database connection initialization operations and release of connection
 * on server start up and stop.
 */
public class PersisterPlugin implements PlugIn {

	private static final Logger log = Logger.getLogger(PersisterPlugin.class);
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy() {
		
		try{
			Persister persister = PersisterImpl.getPersister();
			
			if(persister.isOpen()){
			   persister.close();			   
			   log.info("Persister closed successfully : "+persister.isOpen());
			}else{
				log.info("Persister already closed ");
			}
			
		}catch (HibernateException e) {
			log.error("Some error occured while closing the persister ",e);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
	 */
	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		
		try{
			PersisterImpl.getPersister();
			log.info("Persister initialize successfully.");
		}catch (HibernateException e) {
			log.error("Some error occured while initializing the persister ",e);
		} 

	}

}
