package com.infotech.sgsy.language;

import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.MessageResources;


public class ConfigurationMessageResourceFactory extends MessageResourcesFactory {

   public MessageResources createResources(String config) {
        return new ConfigurationMessageResources(this, config, this.returnNull);
     }
   
 }

