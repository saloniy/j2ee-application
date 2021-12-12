<!--/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 8 Dec, 2021
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
	                <h1>All Claims for Device: ${requestScope['productName']}</h1>
	            </div>
	        </div>
	        <div class="row my-3">
	            <div class="col-12 d-flex justify-content-end">
	            	<c:if test="${sessionScope.isAdmin == false}">
	            		<a href="dashboard" class="btn btn-success">Back to Dashboard</a>
	            	</c:if>
	            	<c:if test="${sessionScope.isAdmin == true}">
			    		<a href="view-registered-device?username=${requestScope['username']}" class="btn btn-success">Back to Registered Devices</a>
			    	</c:if>
	            </div>
	        </div>
	        <table class="table table-light">
	            <thead>
	            <tr>
	                <th scope="col">Claim Date</th>
	                <th scope="col">Description</th>
	                <th scope="col">Claim Status</th>
	            </tr>
	            </thead>
	            <tbody id="allUsersData">
	            	<c:forEach var="claim" items="${requestScope['claims']}">
		                <tr>
		                    <td>${claim.getClaimDate()}</td>
		                    <td>${claim.getDescription()}</td>
		                    <td>
		                    <c:if test="${sessionScope.isAdmin == false}">
		                    	<p>${claim.getStatus()}</p>
		                    </c:if>
		                    <c:if test="${sessionScope.isAdmin == true}">
			                    <select class="form-select" id="claimStatus${claim.getId()}" 
			                    name="claimStatus" ${claim.getStatus() == "Approved" ? 'disabled': ''} 
			                    onChange="claimStatusChanged(${claim.getId()})">
			                    	<option value="Pending" ${claim.getStatus() == "Pending" ? 'selected' : ''}>Pending</option>
			                    	<option value="Approved" ${claim.getStatus() == "Approved" ? 'selected' : ''}>Approved</option>
			                    	<option value="Rejected" ${claim.getStatus() == "Rejected" ? 'selected' : ''}>Rejected</option>
			                    </select>
			                    <span id="updatedMsg${claim.getId()}" class="success-msg hide">Claim status updated</span>
			                    <span id="errorMsg${claim.getId()}" class="error-msg hide">Some error occurred</span>
			                </c:if>
		                  	</td>  
		                    
		                </tr>
		            </c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
   	<jsp:include page="footer.jsp"></jsp:include>