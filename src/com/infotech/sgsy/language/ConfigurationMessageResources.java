package com.infotech.sgsy.language;

import java.net.URL;
import java.util.Locale;
import java.util.Map;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

public class ConfigurationMessageResources extends MessageResources {
	
	    /** Configurations associées à leur Locale respective. */
	    protected Map configurations;

     public ConfigurationMessageResources(MessageResourcesFactory factory, String config, boolean returnNull) {
	           super(factory, config, returnNull);
	           getConfiguration(config+".properties") ;
	     }
	 
	       public String getMessage(Locale locale, String key) {
	   
	          // get the configuration for the specified locale
	          Configuration resource = getConfiguration(this.config + "_" + locale.getLanguage() + ".properties");
	   
	           if (resource == null || !resource.containsKey(key)) {
	               // look for the key in the root configuration
	               resource = getConfiguration(this.config + ".properties");
	           }
	   
	           return resource != null ? resource.getString(key, null) : null;
	       }
	   
	       /**
	        * Load the specified configuration from the classpath and initialize it.
	        */ 
	       private Configuration getConfiguration(String name) {
	   
	           Configuration configuration = null;
	           
	           URL url = Thread.currentThread().getContextClassLoader().getResource("../../propFiles/ApplicationResources.properties");
	          
	           /*URL url=null;
			try {
				if(!name.equalsIgnoreCase("ApplicationResources_en.properties"))
				url = new URL("http://localhost:8080/SGSY/propFiles/"+name);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  */
	         
	         
	           if (url != null  && !name.equalsIgnoreCase("ApplicationResources_en.properties")) {
	        	   String aa=url.getFile().split("propFiles/")[0];
	        	
	               PropertiesConfiguration pc = new org.apache.commons.configuration.PropertiesConfiguration();

	               PropertiesConfiguration.setDelimiter('\uffff'); // disable string splitting
	               pc.setURL(url);
	               pc.setEncoding("UTF-8");
	              
	               try {
	            	   pc.setBasePath(aa+"propFiles");
		               pc.setReloadingStrategy(new FileChangedReloadingStrategy());
		               
	                  pc.load();
	                 
	                   configuration = pc;
	               } catch (Exception e) {
	                   e.printStackTrace();
	               }
	           }
	           
	           return configuration;
	       }
	       
}
