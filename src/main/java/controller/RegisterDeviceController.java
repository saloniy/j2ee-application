package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBConn;
import dao.DeviceQueries;
import dao.ProductQueries;
import model.Products;

/**
 * Servlet implementation class RegisterDeviceController
 */
@WebServlet("/register-device")
public class RegisterDeviceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String url = "/register_device_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		try {
			ProductQueries productQuery = new ProductQueries(DBConn.getConnection());
			ArrayList<Products> products = productQuery.getAllProducts();
			request.setAttribute("products", products);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		response.setCharacterEncoding("UTF-8");
		boolean isProductError = false, isSerialNumError = false, isPurchaseDateError = false;
		String serialNumRegex = "^[A-Za-z0-9]*$";
		if(request.getParameter("productId").trim() == "") {
			isProductError = true;
			response.getWriter().append("productError");   
		} 
		if(request.getParameter("serialNum").trim() == "" || !request.getParameter("serialNum").matches(serialNumRegex)) {
			isSerialNumError = true;
			response.getWriter().append("serialNumError");   
		}
		if(request.getParameter("purchaseDate").trim() == "") {
			isPurchaseDateError = true;
			response.getWriter().append("purchaseDateError");   
		}
		if(isProductError || isSerialNumError || isPurchaseDateError) {
			return;
		}

		String username = request.getParameter("username");
		String serialNum = request.getParameter("serialNum");
		Integer productId = Integer.parseInt(request.getParameter("productId") != "" ? request.getParameter("productId") : "0");
		Date date = Date.valueOf(request.getParameter("purchaseDate"));
		
		try {
			DeviceQueries deviceQuery = new DeviceQueries(DBConn.getConnection());
			int count = deviceQuery.registerDevice(username, productId, serialNum, date);
			if(count > 0) {
				response.getWriter().write("Done");   
			} else {
				response.getWriter().write("Error");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("Error");   
		}
	}

}
