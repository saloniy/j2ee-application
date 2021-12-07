package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClaimQueries;
import dao.DBConn;

/**
 * Servlet implementation class ClaimStatusController
 */
@WebServlet("/update-claim-status")
public class ClaimStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClaimStatusController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		Integer claimId = Integer.parseInt(request.getParameter("claimId"));
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	    try {
			ClaimQueries claimQuery = new ClaimQueries(DBConn.getConnection());
			int count = claimQuery.updateClaimStatusById(status, claimId);
			if(count > 0) {
				response.getWriter().write("Done");   
			} else {
				response.getWriter().write("Failure");   
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("Failure");   
		}
	    
	}

}
