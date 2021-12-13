/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576  Date: 6 Dec, 2021
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
import dao.ProductQueries;
import model.Auth;
import model.Products;

/**
 * Servlet implementation class ProductsContoller
 */
@WebServlet("/addProduct")
public class ProductsContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean forCustomer = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsContoller() {
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
		try {
			ProductQueries productQuery = new ProductQueries(DBConn.getConnection());	
			ArrayList<Products> products = productQuery.getAllProducts();
			RequestDispatcher rd = request.getRequestDispatcher("/add_products_form.jsp");
			request.setAttribute("products", products);
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/add_products_form.jsp");
		ProductQueries productQuery = null;
		try {
			productQuery = new ProductQueries(DBConn.getConnection());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		ArrayList<Products> products = productQuery.getAllProducts();
		if(request.getParameter("productName") == "") {
			request.setAttribute("error", "Please enter a valid product name");
			request.setAttribute("products", products);
			rd.forward(request, response);
			return;
		}
		try {
			productQuery = new ProductQueries(DBConn.getConnection());	
			products = productQuery.addNewProduct(request.getParameter("productName"));
			if(products != null) {
				request.setAttribute("products", products);
			} else {
				request.setAttribute("error", "Some error occurred. Please try again");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
