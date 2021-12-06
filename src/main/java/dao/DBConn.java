/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 6 Dec, 2021
*
********************************************************************************/
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
