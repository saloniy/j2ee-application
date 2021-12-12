/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 8 Dec, 2021
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

import dao.ClaimQueries;
import dao.DBConn;
import dao.DeviceQueries;
import dao.UserQueries;
import model.Claims;
import model.Devices;

/**
 * Servlet implementation class ClaimsController
 */
@WebServlet("/view-claims")
public class ClaimsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaimsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/view_claims.jsp";
		String user = request.getParameter("username");
		Integer deviceId = Integer.parseInt(request.getParameter("deviceId"));
		String productName = request.getParameter("product");
		RequestDispatcher rd = request.getRequestDispatcher(url);
		try {
			ClaimQueries claimQuery = new ClaimQueries(DBConn.getConnection());
			ArrayList<Claims> claims = claimQuery.getAllClaimsForDeviceAndUser(user, deviceId);
			request.setAttribute("claims", claims);
			request.setAttribute("username", user);
			request.setAttribute("productName", productName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
