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
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBConn;
import dao.DeviceQueries;
import dao.UserQueries;
import model.Auth;
import model.Devices;

/**
 * Servlet implementation class DeviceController
 */
@WebServlet("/view-registered-device")
public class DeviceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean forCustomer = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeviceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
    	Auth auth = new Auth();
    	if(!auth.validateUrlAccessibility(session, forCustomer)) {
    		response.sendRedirect(request.getContextPath() + "/logout");
    		return;
    	}  
		String url = "/view_registered_devices.jsp";
		String user = request.getParameter("username").toString();
		RequestDispatcher rd = request.getRequestDispatcher(url);
		try {
			UserQueries userQuery = new UserQueries(DBConn.getConnection());
			request.setAttribute("name", userQuery.getCustomerNameByUsername(user));
			DeviceQueries deviceQuery = new DeviceQueries(DBConn.getConnection());
			ArrayList<Devices> devices = deviceQuery.getAllRegisteredDevicesForUser(user);
			request.setAttribute("username", user);
			request.setAttribute("devices", devices);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

}
