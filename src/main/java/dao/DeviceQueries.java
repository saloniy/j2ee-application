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
import java.sql.Date;

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
				String sql_claims = "Select count(*) as claim_count from claims where device_id = ? and username= ?";
				PreparedStatement ps_product = conn.prepareStatement(sql_product);
				ps_product.setInt(1, result.getInt("product_id"));
				PreparedStatement ps_claims = conn.prepareStatement(sql_claims);
				ps_claims.setInt(1, result.getInt("id"));
				ps_claims.setString(2, username);
				ResultSet result_product = ps_product.executeQuery();
				ResultSet result_claims = ps_claims.executeQuery();
				Devices device = new Devices();
				if(result_product.next()) {
					device.setProductName(result_product.getString("product_name"));
				} else {
					device.setProductName("Not Found");
				}
				if(result_claims.next()) {
					device.setClaimCount(result_claims.getInt("claim_count"));
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
	
	public int registerDevice(String username, int productId, String serialNumber, Date date) {
		int count = 0;
		String sql = "Insert into regd_devices(product_id, serial_number, date, username) values (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			ps.setString(2, serialNumber);
			ps.setDate(3, date);
			ps.setString(4, username);
			count = ps.executeUpdate();
			return count;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
