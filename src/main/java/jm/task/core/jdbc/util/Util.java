package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jm.task.core.jdbc.model.User;

public class Util {
    
	private Connection connection;
	private SessionFactory sessionFactory;
	private Properties prop;
	
	public Util() {
		
    }
    
	public Util(String dbName, String username, String password) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		prop = new Properties();
		prop.setProperty("hibernate.connection.url", url);
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.connection.username", username);
        prop.setProperty("hibernate.connection.password", password);
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("show_sql", "true"); //to see the generated sql query
        prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        prop.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
        sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(prop).buildSessionFactory();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
