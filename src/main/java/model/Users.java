package model;

public class Users {
	private String name;
	private String contact;
	private String username;
	private String password;
	private Boolean isAdmin;
 
	public Users(String name, String contact, String username, String password, Boolean isAdmin){
		this.name = name;
		this.contact = contact;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

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
