/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 12 Dec, 2021
*
********************************************************************************/
package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClaimQueries;
import dao.DBConn;
import model.Auth;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
    	Auth auth = new Auth();
    	if(!auth.validateUrlAccessibility(session, forCustomer)) {
    		response.sendRedirect(request.getContextPath() + "/logout");
    		return;
    	}   
		var deviceId = request.getParameter("device");
		String url = "/submit_claim_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		request.setAttribute("deviceId", deviceId);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		response.setCharacterEncoding("UTF-8");
		if(request.getParameter("desc").trim() == "") {
			response.getWriter().write("claimError");
			return;
		}
		HttpSession session = request.getSession(false);
		String description = request.getParameter("desc");
		Date date = Date.valueOf(request.getParameter("date"));
		String username = (String) session.getAttribute("username");
		int deviceId = Integer.parseInt(request.getParameter("deviceId"));
		try {
			ClaimQueries claimQuery = new ClaimQueries(DBConn.getConnection());
			int count = claimQuery.addClaim(username, deviceId, date, description);
			if(count > 0) {
				response.getWriter().write("Done");
			} else {
				response.getWriter().write("Error");
			}
		} catch (Exception e) {
			response.getWriter().write("Error");
			e.printStackTrace();
		}
	}

}
