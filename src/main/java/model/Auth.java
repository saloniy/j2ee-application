package model;

import java.sql.ResultSet;

import dao.DBConn;
import dao.UserQueries;

public class Auth {
	public ResultSet validateLogin(String username, String password) {
		try {
			UserQueries user = new UserQueries(DBConn.getConnection());
			ResultSet result = user.validateUser(username, password);
			if (result != null) {
				return result;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String createUser(String name, String contact, String username, String password, Boolean isAdmin) {
		UserQueries user = null;
		try {
			user = new UserQueries(DBConn.getConnection());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = user.createUser(name, contact, username, password, isAdmin);
		return result;
	}
}
