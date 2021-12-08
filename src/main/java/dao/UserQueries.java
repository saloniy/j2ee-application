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
import java.sql.Statement;
import java.util.ArrayList;

import model.Users;

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
	
	public ArrayList<Users> getAllCustomers() {
		String sql = "Select * from users where isAdmin = 0";
		try {
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sql);
			ArrayList<Users> users = new ArrayList<Users>();
			while(result.next()) {
				Users user = new Users();
				user.setName(result.getString("name"));
				user.setContact(result.getString("contact"));
				user.setUsername(result.getString("username"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String getCustomerNameByUsername(String username) {
		String sql = "Select name from users where username = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, username);
			ResultSet result = st.executeQuery();
			if(result.next()) {
				return result.getString("name");
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Users> getUserBySearchTerm(String searchTerm) {
		String sql = "Select * from users where name like ? or username like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, searchTerm);
			ps.setString(2, searchTerm);
			ResultSet result = ps.executeQuery();
			ArrayList<Users> users = new ArrayList<Users>();
			while(result.next()) {
				Users user = new Users();
				user.setName(result.getString("name"));
				user.setContact(result.getString("contact"));
				user.setUsername(result.getString("username"));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
