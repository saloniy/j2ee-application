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
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Auth;
import model.Users;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/login_registration.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		if(request.getParameter("email") == "") {
			rd = request.getRequestDispatcher("/login_registration.jsp");
			request.setAttribute("emptyEmail", "Please enter email ID");
			if(request.getParameter("password") == "") {
				request.setAttribute("emptyPassword", "Please enter password");
			}
			rd.forward(request, response);
			return;
		}
		String username = request.getParameter("email").toString();
		String password = request.getParameter("password").toString();
 
		Auth validator = new Auth();
		ResultSet result = validator.validateLogin(username, password);
		if (result != null) {
			try {
				Users user = new Users();
				user.setName(result.getString("name"));
				user.setContact(result.getString("contact"));
				user.setUsername(username);
				user.setPassword(password);
				user.setIsAdmin(result.getBoolean("isAdmin"));
				HttpSession session = request.getSession(false);
				session.setAttribute("username", user.getUsername());
				session.setAttribute("isAdmin", user.getIsAdmin());
				response.sendRedirect(request.getContextPath() + "/dashboard");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			rd = request.getRequestDispatcher("/login_registration.jsp");
			request.setAttribute("invalidLogin", "Incorrect Email ID or Password");
			request.setAttribute("email", username);
			rd.forward(request, response);
		}
	}
}
