/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 6 Dec, 2021
*
********************************************************************************/
package controller;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClaimQueries;
import dao.DBConn;
import dao.DeviceQueries;
import dao.UserQueries;
import model.Claims;
import model.Devices;
import model.Users;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "/dashboard.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(url);
		if(session != null && session.getAttribute("isAdmin") != null) {
			// admin dashboard code
			if ((Boolean)session.getAttribute("isAdmin") == true) {
				try {
					UserQueries userQuery = new UserQueries(DBConn.getConnection());
					ArrayList<Users> users = userQuery.getAllCustomers();
					request.setAttribute("allUsers", users);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rd.forward(request, response);
				return;
			} else if((Boolean)session.getAttribute("isAdmin") == false) {
				//user dashboard code
				try {
					DeviceQueries deviceQuery = new DeviceQueries(DBConn.getConnection());
					ArrayList<Devices> devices = deviceQuery.getAllRegisteredDevicesForUser(session.getAttribute("username").toString());
					if(devices.size() > 0) {
						for(int i=0; i< devices.size(); i++) {
							Date purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(devices.get(i).getPurchaseDate());
							Date today = new Date();
							long diffInMillis = Math.abs(today.getTime() - purchaseDate.getTime());
							long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)/365;
							ClaimQueries claimQuery = new ClaimQueries(DBConn.getConnection());
							ArrayList<Claims> claims = claimQuery.getAllClaimsForDeviceAndUser(
									session.getAttribute("username").toString(),
									Integer.parseInt(devices.get(i).getId()
								)
							);
							request.setAttribute("claims" + devices.get(i).getId(), claims);
							if(diff < 5) {
								devices.get(i).setCanClaim(true);
							} else {
								devices.get(i).setCanClaim(false);
							}
						}
					}
					request.setAttribute("devices", devices);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rd.forward(request, response);
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
