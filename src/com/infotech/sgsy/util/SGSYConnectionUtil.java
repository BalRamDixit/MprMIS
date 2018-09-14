package com.infotech.sgsy.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SGSYConnectionUtil {

	/**
	 * @param args
	 */
	
	private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void main(String arStr[]) {
    	
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
    	getSessionFactory();
		
	}
    /*public static Connection getConnection() throws SQLException {
    	Connection con = getSessionFactory().openStatelessSession().connection();
    	//Connection con =   getSessionFactory().openSession().connection();
    	con.setAutoCommit(true);
    	return con;
    	
    }*/
    
    
    

}
