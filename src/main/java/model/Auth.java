/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159,  Date: 6 Dec, 2021
*
********************************************************************************/
package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;

import dao.DBConn;
import dao.UserQueries;

public class Auth {
	
	public static String MD5(String s) throws Exception {
		MessageDigest m = MessageDigest.getInstance("MD5");
	    m.update(s.getBytes(),0,s.length());
	    return new BigInteger(1,m.digest()).toString(16);
	}

	public ResultSet validateLogin(String username, String password) {
		try {
			UserQueries user = new UserQueries(DBConn.getConnection());
			String encPassword = MD5(password);
			ResultSet result = user.validateUser(username, encPassword);
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
		String encPassword = "";
		try {
			encPassword = MD5(password);
			user = new UserQueries(DBConn.getConnection());
			String result = user.createUser(name, contact, username, encPassword, isAdmin);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
