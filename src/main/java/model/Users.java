/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 6 Dec, 2021
*
********************************************************************************/
package model;

public class Users {
	private String name;
	private String contact;
	private String username;
	private String password;
	private Boolean isAdmin;
 
	public Users(){}

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}
 
	public void setContact(String contact) {
		this.contact = contact;
	}

 
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
 
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
