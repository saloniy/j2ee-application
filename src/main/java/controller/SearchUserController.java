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
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClaimQueries;
import dao.DBConn;
import dao.UserQueries;
import model.Users;

/**
 * Servlet implementation class SearchUserController
 */
@WebServlet("/search-user")
public class SearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("search");
		response.setContentType("text/html");  // Set content type of the response so that jQuery knows what it can expect.
		response.setCharacterEncoding("UTF-8");
		String htmlString = "";
	    try {
			UserQueries userQuery = new UserQueries(DBConn.getConnection());
			ArrayList<Users> users = userQuery.getUserBySearchTerm(searchTerm);
			if(users.size() > 0) {
				for(int i=0; i< users.size(); i++) {
					htmlString += "<tr>" + 
							"<td>" + users.get(i).getName() + "</td>" + 
							"<td>" + users.get(i).getContact() + "</td>" +
							"<td>" + users.get(i).getUsername() + "</td>" + 
							"<td>" +
	           "<a class=\"btn btn-primary\" href=\"view-registered-device?username=" + users.get(i).getUsername() + "\">" + 
							"View Registered Products</a></td></tr>";
				}
			} else {
				htmlString = "<tr class=\"text-center\"><td colspan=\"4\">No data to display</td></tr>";
			}
			response.getWriter().write(htmlString);   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			htmlString = "<tr class=\"text-center\"><td colspan=\"4\">No data to display</td></tr>";
			e.printStackTrace();
			response.getWriter().write(htmlString);   
		}
	}

}
