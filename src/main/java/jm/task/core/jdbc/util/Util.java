package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    
	private Connection connection;
	
	public Util() {
    	
    }
    
	public Util(String dbName, String username, String password) {
		
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		
		
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
	
	
}
