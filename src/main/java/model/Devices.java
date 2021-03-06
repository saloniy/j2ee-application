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

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Devices {
	private String id;
	private String product_name;
	private int product_id;
	private String serial_number;
	private Date date;
	private String username;
	private Integer claim_count;
	private boolean can_claim;

	public Devices() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return product_name;
	}

	public void setProductName(String name) {
		this.product_name = name;
	}

	public String getSerialNumber() {
		return serial_number;
	}

	public void setSerialNumber(String serial_num) {
		this.serial_number = serial_num;
	}

	public String getPurchaseDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		return dateString;
	}

	public Date getPurchaseDate_dt() {
		return date;
	}

	public void setPurchaseDate(Date date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getClaimCount() {
		return claim_count;
	}

	public void setClaimCount(Integer claimCount) {
		this.claim_count = claimCount;
	}

	public boolean getCanClaim() {
		return can_claim;
	}

	public void setCanClaim(boolean canClaim) {
		this.can_claim = canClaim;
	}

	public int getProductId() {
		return product_id;
	}

	public void setProductId(int product_id) {
		this.product_id = product_id;
	}

}
