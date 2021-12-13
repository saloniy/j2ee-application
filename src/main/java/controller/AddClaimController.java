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
import model.Auth;
import model.Claims;
import model.Devices;

/**
 * Servlet implementation class AddClaimController
 */
@WebServlet("/add-claim")
public class AddClaimController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean forCustomer = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClaimController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
    	Auth auth = new Auth();
    	if(!auth.validateUrlAccessibility(session, forCustomer)) {
    		response.sendRedirect(request.getContextPath() + "/logout");
    		return;
    	}
		String id = request.getParameter("id");
		Devices claimDevice = new Devices();

		try {
			DeviceQueries dq = new DeviceQueries(DBConn.getConnection());
			claimDevice = dq.getDeviceFromId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = "/submit_claim_form.jsp";
		request.setAttribute("claimDevice", claimDevice);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String claimsAdded = "";
		HttpSession session = request.getSession(false);
		String url = "/submit_claim_form.jsp";

		String description = request.getParameter("description");
		String username = session.getAttribute("username").toString();
		String status = "Pending";
		RequestDispatcher dispatcher = null;
		String emptyDesc = null;
		String device_id = request.getParameter("claimProductId");
		Devices claimDevice = new Devices();
		claimDevice.setId(device_id);
		claimDevice.setProductName(request.getParameter("claimProduct"));
		claimDevice.setSerialNumber(request.getParameter("claimProductSerial"));

		if (description.trim() == "") {
			emptyDesc = "Please enter a description";
		}
		if (emptyDesc != null) {
			dispatcher = request.getRequestDispatcher(url);
			request.setAttribute("emptyDesc", emptyDesc);
			request.setAttribute("claimDevice", claimDevice);
			dispatcher.forward(request, response);
			return;
		}

		long millis = System.currentTimeMillis();
		java.sql.Date today = new java.sql.Date(millis);

		Claims newClaim = new Claims();
		newClaim.setDescription(description);
		newClaim.setStatus(status);
		newClaim.setClaimDate(today);
		newClaim.setDeviceId(Integer.parseInt(device_id));
		newClaim.setUsername(username);

		ClaimQueries cq;
		try {
			cq = new ClaimQueries(DBConn.getConnection());
			claimsAdded = cq.addClaim(newClaim);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (claimsAdded == "success") {
			request.setAttribute("success", "Claim Added Successfully");
			request.setAttribute("claimDevice", claimDevice);
		} else if(claimsAdded == "warranty exceeded"){
			request.setAttribute("error", "Warranty period of 5 years exceeded");
			request.setAttribute("claimDevice", claimDevice);
		} else if(claimsAdded == "count exceeded"){
			request.setAttribute("error", "Only 3 claims can be filed for a device");
			request.setAttribute("claimDevice", claimDevice);
		} else {
			request.setAttribute("error", "Some Error Occurred. Please try again");
			request.setAttribute("claimDevice", claimDevice);
		}
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
