package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;

public class DBConn {
		
		private static Connection connection;
		
		private DBConn() {
			
		}
		
		public static Connection getConnection() throws Exception {
			Properties prop = new Properties();
			InputStream input = DBConn.class.getResourceAsStream("/database.properties");
	        // load a properties file
	        prop.load(input);
			try {  
				 Class.forName("com.mysql.jdbc.Driver");
			} catch(ClassNotFoundException e) { 
				 e.printStackTrace();
			}	
			if(connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(
		                "jdbc:mysql://" + prop.getProperty("hostName") + "/" + prop.getProperty("databaseName"), prop.getProperty("username"), prop.getProperty("password"));
			}
			return connection;
		}

}
