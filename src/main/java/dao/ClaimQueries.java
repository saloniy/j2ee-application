/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 8 Dec, 2021
*
********************************************************************************/
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import model.Claims;

public class ClaimQueries {
private Connection conn;
	
	public ClaimQueries(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<Claims> getAllClaimsForDeviceAndUser(String username, int deviceId) {
		String sql = "Select * from claims where username = ? and device_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, deviceId);
			ResultSet result = ps.executeQuery();
			ArrayList<Claims> claims = new ArrayList<Claims>();
			while(result.next()) {
				Claims claim = new Claims();
				claim.setId(result.getString("id"));
				claim.setDescription(result.getString("description"));
				claim.setClaimDate(result.getDate("date"));
				claim.setStatus(result.getString("status"));
				claims.add(claim);
			}
			return claims;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int updateClaimStatusById(String status, int claimId) {
		int count = 0;
		String sql = "Update claims set status = ? where id= ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, claimId);
			count = ps.executeUpdate();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int addClaim(String username, int deviceId, Date date, String description) {
		String dateSql = "Select date from regd_devices where id = ?";
		String sql = "Insert into claims(username, device_id, date, description, status) values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(dateSql);
			ps.setInt(1, deviceId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				java.util.Date purchaseDate;
				try {
					purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date").toString());
					java.util.Date today = new java.util.Date();
					long diffInMillis = Math.abs(today.getTime() - purchaseDate.getTime());
					long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)/365;
					if(diff > 5) {
						return 0;
					} else {
						PreparedStatement psClaims = conn.prepareStatement(sql);
						psClaims.setString(1, username);
						psClaims.setInt(2, deviceId);
						psClaims.setDate(3, date);
						psClaims.setString(4, description);
						psClaims.setString(5, "Pending");
						int count = psClaims.executeUpdate();
						return count;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
}
