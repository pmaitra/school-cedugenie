package com.qts.icam.listner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class ConfigurationListener implements ServletContextListener {


	
	@Override
    public void contextDestroyed(ServletContextEvent event){
		LogManager.shutdown();
    }
 
    @Override
    public void contextInitialized(ServletContextEvent event){
    	// Loading logger
        Logger logger = null;
        ServletContext servletContext = event.getServletContext();
        String log4jFile = servletContext.getInitParameter("icamLog4jPath");
        //System.out.println("log4jfile::"+log4jFile);
        DOMConfigurator.configure(event.getServletContext().getRealPath(log4jFile));
        logger = LogManager.getLogger(ConfigurationListener.class.getName());
        logger.info("Loaded Log4j Configuration File : " + log4jFile);
   }
    
   
    
    		
}
