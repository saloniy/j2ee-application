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
import java.util.List;

import model.Products;

public class ProductQueries {
	private Connection conn;
	
	public ProductQueries(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<Products> getAllProducts() {
		String sql = "Select * from products";
		try {
			Statement st = conn.createStatement();
			ResultSet result = st.executeQuery(sql);
			ArrayList<Products> products = new ArrayList<Products>();
			while(result.next()) {
				Products product = new Products();
				product.setId(result.getString("product_id"));
				product.setName(result.getString("product_name"));
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Products> addNewProduct(String name) {
		String sql = "Insert into products(product_name) values (?)";
		ArrayList<Products> products = new ArrayList<Products>();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			int count = st.executeUpdate();
			if(count > 0) {
				products = getAllProducts();
				return products;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
