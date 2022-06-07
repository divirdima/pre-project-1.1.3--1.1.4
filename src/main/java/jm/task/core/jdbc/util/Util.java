package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jm.task.core.jdbc.model.User;

public class Util {
	
	private static Util INSTANCE;
	private static SessionFactory sessionFactory;
	
	private Util() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + "first");
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "1111");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("show_sql", "true"); //to see the generated sql query
        prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        prop.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
		
		sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(prop).buildSessionFactory();
    }
    
	
	public static Util getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Util();
		}
		return INSTANCE;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
