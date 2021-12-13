package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import model.Devices;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ProductQueries productQuery = new ProductQueries(DBConn.getConnection());
			ArrayList<Products> products = productQuery.getAllProducts();
			request.setAttribute("products", products);
			String url = "/register_device_form.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String invalidProduct = null, invalidSerial = null, invalidDate = null;
		String url = "/register_device_form.jsp";
		String result = "";

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		long millis = System.currentTimeMillis();
		java.sql.Date today = new java.sql.Date(millis);
		String purchaseDate = request.getParameter("purchaseDate");

		if (request.getParameter("productName").trim() == "") {
			invalidProduct = "Please enter a valid name";
		}
		if (request.getParameter("serialNum").trim() == "") {
			invalidSerial = "Please enter a valid email ID";
		}
		if (request.getParameter("purchaseDate").trim() == "") {
			invalidDate = "Please enter a value in purchase date";
		}
		if (request.getParameter("purchaseDate").trim() != "") {
			Date dt_purchaseDate = Date.valueOf(purchaseDate);
			if (dt_purchaseDate.compareTo(today) > 0)
				invalidDate = "Please enter a valid date, which is in past";
		}

		if ((invalidProduct != null) || (invalidSerial != null) || (invalidDate != null)) {
			rd = request.getRequestDispatcher("/login_registration.jsp");
			request.setAttribute("invalidProduct", invalidProduct);
			request.setAttribute("invalidSerial", invalidSerial);
			request.setAttribute("invalidDate", invalidDate);

			if (request.getParameter("productName").trim() != "") {
				request.setAttribute("productName", request.getParameter("productName"));
			}
			if (request.getParameter("serialNum").trim() != "") {
				request.setAttribute("serialNum", request.getParameter("serialNum"));
			}
			if (request.getParameter("purchaseDate").trim() != "") {
				request.setAttribute("purchaseDate", request.getParameter("purchaseDate"));
			}
			rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			return;
		}
		String username = request.getParameter("username");
		String str_productID = request.getParameter("productName");
		int productID = Integer.parseInt(str_productID);
		String serialNum = request.getParameter("serialNum");
		Date dt_purchaseDate = Date.valueOf(purchaseDate);

		/*
		 * ProductQueries pq; Products newProduct; try { pq = new
		 * ProductQueries(DBConn.getConnection()); newProduct =
		 * pq.getProductFromName(productName); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		Devices newDevice = new Devices();
		// newDevice.setProductName(productName);
		newDevice.setProductId(productID);
		newDevice.setUsername(username);
		newDevice.setPurchaseDate(dt_purchaseDate);
		newDevice.setSerialNumber(serialNum);

		try {
			DeviceQueries dq = new DeviceQueries(DBConn.getConnection());
			result = dq.addDevice(newDevice);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == "success") {
			url = "/dashboard";
		} else {
			url = "/error.jsp";
		}
		rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
