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
			            <a href="" class="btn btn-success">Register New Device</a>
			        </div>
			    </div>
			    <table class="table table-light">
			        <thead>
			        <tr>
			            <th scope="col">#</th>
			            <th scope="col">Device Name</th>
			            <th scope="col">Status</th>
			            <th scope="col">Claim Submitted</th>
			        </tr>
			        </thead>
			        <tbody>
			            <tr>
			                <th scope="row">1</th>
			                <td></td>
			                <td></td>
			                <td></td>
			            </tr>
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
		            <div class="col-12 d-flex justify-content-end">
		                <a href="addProduct" class="btn btn-success me-3">Add New Product</a>
		                <a href="" class="btn btn-primary">Search User</a>
		            </div>
		        </div>
		        <table class="table table-light">
		            <thead>
		            <tr>
		                <th scope="col">#</th>
		                <th scope="col">Name</th>
		                <th scope="col">Email ID</th>
		                <th scope="col"></th>
		            </tr>
		            </thead>
		            <tbody id="allUsersData">
		                <tr>
		                    <th scope="row">1</th>
		                    <td></td>
		                    <td></td>
		                    <td>
	                           <a class="btn btn-primary">View Registered Products</a>
		                    </td>
		                </tr>
		            </tbody>
		        </table>
		    </div>
		</c:if>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>