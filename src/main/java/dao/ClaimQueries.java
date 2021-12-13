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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import model.Claims;

public class ClaimQueries {
	private Connection conn;

	public ClaimQueries(Connection conn) {
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
			while (result.next()) {
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

	public String addClaim(Claims claim) {
		int rows = 0;
		String dateSql = "Select date from regd_devices where id = ?";
		String claimsSql = "Select count(*) as claim_count from claims where device_id = ? and username= ?";
		String sql = "Insert into claims (username, device_id, date, description, status) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(dateSql);
			ps.setInt(1, claim.getDeviceId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				java.util.Date purchaseDate;
				try {
					purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date").toString());
					java.util.Date today = new java.util.Date();
					long diffInMillis = Math.abs(today.getTime() - purchaseDate.getTime());
					long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)/365;
					if(diff > 5) {
						return "warranty exceeded";
					} else {
						PreparedStatement ps_claims = conn.prepareStatement(claimsSql);
						ps_claims.setInt(1, claim.getDeviceId());
						ps_claims.setString(2, claim.getUsername());
						ResultSet result_claims = ps_claims.executeQuery();
						if(result_claims.next() && result_claims.getInt("claim_count") < 3) {
							PreparedStatement st = conn.prepareStatement(sql);
							st.setString(1, claim.getUsername());
							st.setInt(2, claim.getDeviceId());
							st.setDate(3, claim.getClaimDate_Date());
							st.setString(4, claim.getDescription());
							st.setString(5, claim.getStatus());
							rows = st.executeUpdate();
						} else {
							return "count exceeded";
						}
					}
			
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows > 0)
			return "success";
		else
			return "failure";
	}

}
