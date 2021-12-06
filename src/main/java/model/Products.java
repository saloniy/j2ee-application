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

public class Products {
	private String product_id;
	private String product_name;
	
	public Products(){}

	public String getId() {
		return product_id;
	}
 
	public void setId(String id) {
		this.product_id = id;
	}
	
	public String getName() {
		return product_name;
	}
 
	public void setName(String name) {
		this.product_name = name;
	}
}
