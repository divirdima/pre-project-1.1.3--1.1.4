package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jm.task.core.jdbc.model.User;

public class Util {
	
	private static final String URL = "jdbc:mysql://localhost:3306/" + "first";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "1111";
	private static Util INSTANCE;
	private static SessionFactory sessionFactory;
	private static Connection connection;
	
	private Util() {
		
    }
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Properties prop = new Properties();
			prop.setProperty("hibernate.connection.url", URL);
	        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
	        prop.setProperty("hibernate.connection.username", LOGIN);
	        prop.setProperty("hibernate.connection.password", PASSWORD);
	        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
	        prop.setProperty("show_sql", "true"); //to see the generated sql query
	        prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	        prop.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
			
			sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(prop).buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
	
}
