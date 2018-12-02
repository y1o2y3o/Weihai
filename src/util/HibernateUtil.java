package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = bulidSessionFactory();
	
	
	@SuppressWarnings("deprecation")
	private static SessionFactory bulidSessionFactory(){
		try{
			Configuration cfg = new Configuration().configure(); 
	        
	        // Create the SessionFactory from hibernate.cfg.xml
	        return cfg.buildSessionFactory();	
		}catch(Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
