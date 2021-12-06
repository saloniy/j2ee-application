/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: ____________________
*
********************************************************************************/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Devices;

public class DeviceQueries {
	private Connection conn;
	
	public DeviceQueries(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<Devices> getAllRegisteredDevicesForUser(String username) {
		String sql = "Select * from regd_devices where username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			ArrayList<Devices> devices = new ArrayList<Devices>();
			while(result.next()) {
				String sql_product = "Select * from products where product_id = ?";
				PreparedStatement ps_product = conn.prepareStatement(sql_product);
				ps_product.setInt(1, result.getInt("product_id"));
				ResultSet result_product = ps_product.executeQuery();
				Devices device = new Devices();
				if(result_product.next()) {
					device.setProductName(result_product.getString("product_name"));
				} else {
					device.setProductName("Not Found");
				}
				device.setId(result.getString("id"));
				device.setSerialNumber(result.getString("serial_number"));
				device.setPurchaseDate(result.getDate("date"));
				device.setUsername(result.getString("username"));
				devices.add(device);
			}
			return devices;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
