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
import model.Claims;
import model.Devices;

/**
 * Servlet implementation class AddClaimController
 */
@WebServlet("/add-claim")
public class AddClaimController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String url = "";

		String description = request.getParameter("description");
		String username = session.getAttribute("username").toString();
		String status = "Pending";
		RequestDispatcher dispatcher = null;
		String emptyDesc = null;
		String device_id = request.getParameter("claimProductId");

		if (description.trim() == "") {
			emptyDesc = "Please enter a description";
		}
		if (emptyDesc != null) {
			Devices claimDevice = new Devices();
			claimDevice.setId(device_id);
			claimDevice.setProductName(request.getParameter("claimProduct"));
			claimDevice.setSerialNumber(request.getParameter("claimProductSerial"));
			dispatcher = request.getRequestDispatcher("/submit_claim_form.jsp");
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
			url = "/dashboard";
		} else {
			url = "/error.jsp";
		}
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
