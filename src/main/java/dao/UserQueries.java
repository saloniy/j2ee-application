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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueries {
	private Connection conn;
	
	public UserQueries(Connection conn){
		this.conn = conn;
	}
	
	public ResultSet validateUser(String username, String password) throws Exception {
		String sql = "select * from users where username = ? and password = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, username);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return rs;
		} else {
			return null;
		}
	}
	
	public String createUser(String name, String contact, String email, String password, Boolean isAdmin) {
		String sql = "Insert into users values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, contact);
			st.setString(3, email);
			st.setString(4, password);
			st.setBoolean(5, isAdmin);
			int count = st.executeUpdate();
			if(count > 0) {
				return "success";
			} else {
				return "failure";
			}
		} catch (SQLException e) {
			return "failure";
			//e.printStackTrace();
			
		}
	}
}
