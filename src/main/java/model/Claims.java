/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: ____________________
*
********************************************************************************/
package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Claims {
	private String id;
	private Date date;
	private String description;
	private String status;
	
	public Claims(){}

	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
	
	public String getClaimDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		return dateString;
	}
 
	public void setClaimDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
}
