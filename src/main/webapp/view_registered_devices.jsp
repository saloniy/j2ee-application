<!--/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 6 Dec, 2021
*
********************************************************************************/-->
<%
	if(request.getSession().getAttribute("username") == null) {
		response.sendRedirect(request.getContextPath() + "/login");
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   	<jsp:include page="header.jsp"></jsp:include>
    <div class="container-fluid app-container min-vh-100">
	    <div class="container">
	        <div class="row mt-4">
	            <div class="col-12 text-center">
	                <h1>All Registered Devices for ${requestScope['name']}</h1>
	            </div>
	        </div>
	        <div class="row my-3">
	            <div class="col-12 d-flex justify-content-end">
			    	<a href="dashboard" class="btn btn-success">Back to Dashboard</a>
	            </div>
	        </div>
	        <table class="table table-light">
	            <thead>
	            <tr>
	                <th scope="col">Product Name</th>
	                <th scope="col">Serial Number</th>
	                <th scope="col">Purchase Date</th>
	                <th scope="col">Claims</th>
	            </tr>
	            </thead>
	            <tbody id="allUsersData">
	            	<c:forEach var="device" items="${requestScope['devices']}">
		                <tr>
		                    <td>${device.getProductName()}</td>
		                    <td>${device.getSerialNumber()}</td>
		                    <td>${device.getPurchaseDate()}</td>
		                    <td>
		               			<c:if test="${device.getClaimCount() > 0}">
	                           		<a class="btn btn-primary" href="view-claims?username=${requestScope['username']}&deviceId=${device.getId()}&product=${device.getProductName()}">View Claims</a>
		                    	</c:if>
		                    	<c:if test="${device.getClaimCount() == 0}">
		                    		<p>No claims submitted</p>
		                    	</c:if>
		                    </td>
		                </tr>
		            </c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>