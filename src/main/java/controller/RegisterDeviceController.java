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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Auth;

/**
 * Servlet implementation class RegisterDeviceController
 */
@WebServlet("/register-device")
public class RegisterDeviceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean forCustomer = true;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterDeviceController() {
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
		String url = "/register_device_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
