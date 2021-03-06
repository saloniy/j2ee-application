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

import model.Auth;
import model.Users;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String nameRegex = "^[A-Za-z\\s\\S]+$";
		String contactRegex = "^[0-9]{10}$";
		String emailRegex = "^([\\w\\.\\-]+)@([\\w\\-]+)((\\.(\\w){2,3})+)$";
		String passwordRegex = "^[A-Za-z0-9@#*%$!]*$";
		String invalidName = null, invalidEmail = null, invalidContact = null, invalidPassword = null, passwordMismatch = null;
		if(request.getParameter("name").trim() == "" || !request.getParameter("name").matches(nameRegex)) {
			invalidName = "Please enter a valid name";
		}
		if(request.getParameter("email").trim() == "" || !request.getParameter("email").matches(emailRegex)) {
			invalidEmail = "Please enter a valid email ID";
		}
		if(request.getParameter("contact").trim() == "" || !request.getParameter("contact").matches(contactRegex)) {
			invalidContact = "Please enter a valid contact number";
		}
		if(request.getParameter("password") == "" || !request.getParameter("password").matches(passwordRegex)) {
			invalidPassword = "Please enter a valid password";
		}
		if(request.getParameter("cpassword") == "" || !request.getParameter("cpassword").contentEquals(request.getParameter("password"))) {
			passwordMismatch = "Password mismatch";
		}
		if((invalidName != null) || (invalidEmail != null) || (invalidContact != null) || (invalidPassword != null) || (passwordMismatch != null)) {
			rd = request.getRequestDispatcher("/login_registration.jsp");
			request.setAttribute("invalidName", invalidName);
			request.setAttribute("invalidEmail", invalidEmail);
			request.setAttribute("invalidContact", invalidContact);
			request.setAttribute("invalidPassword", invalidPassword);
			request.setAttribute("passwordMismatch", passwordMismatch);
			if(request.getParameter("name").trim() != "") {
				request.setAttribute("name", request.getParameter("name"));
			}
			if(request.getParameter("email").trim() != "") {
				request.setAttribute("signupEmail", request.getParameter("email"));
			}
			if(request.getParameter("contact").trim() != "") {
				request.setAttribute("contact", request.getParameter("contact"));
			}
			rd.forward(request, response);
			return;
		}
		String name = request.getParameter("name").toString();
		String contact = request.getParameter("contact").toString();
		String username = request.getParameter("email").toString();
		String password = request.getParameter("password").toString();
		Boolean isAdmin = false;
		
		Auth createUser = new Auth();
		String result = createUser.createUser(name, contact, username, password, isAdmin);
		if (result == "success") {
			try {
				Users user = new Users();
				user.setName(name);
				user.setContact(contact);
				user.setUsername(username);
				user.setPassword(password);
				user.setIsAdmin(isAdmin);
				HttpSession session = request.getSession(false);
				session.setAttribute("username", user.getUsername());
				session.setAttribute("isAdmin", user.getIsAdmin());
				response.sendRedirect(request.getContextPath() + "/dashboard");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			rd = request.getRequestDispatcher("/login_registration.jsp");
			request.setAttribute("userExists", "User Exists. Try Logging In");
			rd.forward(request, response);
		}
	}

}
