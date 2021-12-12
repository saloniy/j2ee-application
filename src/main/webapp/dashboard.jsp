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
	    <c:if test="${sessionScope.isAdmin == false}">
		    <div class="container">
			    <div class="row mt-4">
			        <div class="col-12 text-center">
			            <h1>All Devices</h1>
			        </div>
			    </div>
			    <div class="row my-3">
			        <div class="col-12 d-flex justify-content-end">
			            <!-- only visible to customer -->
			            <a href="register-device" class="btn btn-success">Register New Device</a>
			        </div>
			    </div>
			    <table class="table table-light">
			        <thead>
			        <tr>
			            <th scope="col">#</th>
			            <th scope="col">Device Name</th>
			            <th scope="col">Registered On</th>
			            <th scope="col">Claim Submitted: Status</th>
			            <th scope="col">Submit New Claim</th>
			        </tr>
			        </thead>
			        <tbody>
			        	<c:forEach var="device" items="${requestScope['devices']}">
				            <tr>
				                <th scope="row">${device.getId()}</th>
				                <td>${device.getProductName()}</td>
				                <td>${device.getPurchaseDate()}</td>
				                <td>
				                	<c:set var="claimForDevice" value="claims${device.getId()}"/>
				                	<c:forEach var="claim" items="${requestScope[claimForDevice]}">
				                		${claim.getClaimDate()}: ${claim.getStatus()}<br/>
				                	</c:forEach>
				                </td>
				                <td>
				                	<c:if test="${device.getCanClaim() == false}">
				                		<p>The warranty period of 5 years is up. No more claims can be filed.</p>
				                	</c:if>
				                	<c:if test="${device.getCanClaim() == true}">
					                	<c:if test="${device.getClaimCount() < 3}">
					                	<!--  button shown only if claim count < 3 for the device -->
					                	<a href="add-claim?device=${device.getId()}" class="btn btn-success">Add Claim</a>
					                	<a href="view-claims?username=${sessionScope.username}&deviceId=${device.getId()}&product=${device.getProductName()}" class="btn btn-warning">View Claims</a>
					                	</c:if>
					                	<c:if test="${device.getClaimCount() >= 3}">
					                		<p>You have already submitted 3 claims for this device</p>
					                	</c:if>
					                </c:if>
				                </td>
				            </tr>
			            </c:forEach>
			        </tbody>
			    </table>
			</div>
		</c:if>
		<c:if test="${sessionScope.isAdmin == true}">
			<div class="container">
		        <div class="row mt-4">
		            <div class="col-12 text-center">
		                <h1>All Users</h1>
		            </div>
		        </div>
		        <div class="row my-3">
		        	<div class="col-6">
		        		<div class="input-group mb-3 p-20 white-text">
						  	<input type="text" name="searchUser" class="form-control" placeholder="Search User" aria-label="Search User" aria-describedby="search">
						  	<div onClick="searchUser()" class="input-group-append bg-primary p-20">
						    	<span class="fas fa-search" id="search"></span>
						  	</div>
						</div>
		        	</div>
		            <div class="col-6 d-flex justify-content-end my-3">
			        	<a href="addProduct" class="btn btn-success me-3 height-fit">Add New Product</a>
		            </div>
		        </div>
		        <table class="table table-light">
		            <thead>
		            <tr>
		                <th scope="col">Name</th>
		                <th scope="col">Contact</th>
		                <th scope="col">Email ID</th>
		                <th scope="col">Registered Devices</th>
		            </tr>
		            </thead>
		            <tbody id="allUsersData">
		            	<c:forEach var="user" items="${requestScope['allUsers']}">
			                <tr>
			                    <td>${user.getName()}</td>
			                    <td>${user.getContact()}</td>
			                    <td>${user.getUsername()}</td>
			                    <td>
		                           <a class="btn btn-primary" href="view-registered-device?username=${user.getUsername()}">View Registered Products</a>
			                    </td>
			                </tr>
			            </c:forEach>
		            </tbody>
		        </table>
		    </div>
		</c:if>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>