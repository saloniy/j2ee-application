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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   	<jsp:include page="header.jsp"></jsp:include>
    <div class="container-fluid app-container min-vh-100">
        <div class="container">
			<!--form content -->
			<div class="row">
				<div class="row my-3">
			        <div class="col-12 d-flex justify-content-end">
			            <a href="dashboard" class="btn btn-success">Back to Dashboard</a>
			        </div>
			    </div>
			    <div class="col-6">
			        <div class="card login-card mt-3">
			            <div class="card-body">
			                <h5 class="card-title">Add New Product</h5>
	                        <form method="post" action="addProduct" novalidate>
	                        <c:if test="${requestScope['error'] != null}">
	                            <div class="alert alert-danger">${requestScope['error']}</div>
	                         </c:if>
	                            <div class="mb-3">
	                                <label for="productName" class="form-label">Product Name: </label>
	                                <input type="text" class="form-control" name="productName" id="productName" placeholder="Enter Product Name">
	                            </div>
	                            <div class="mb-3">
	                                <button type="submit" class="btn btn-primary">Add Product</button>
	                            </div>
	                        </form>
			            </div>
			        </div>
			    </div>
			    <div class="col-6 mt-4">
			        <div class="col-6">
			            <h1>All Products</h1>
			        </div>
				    <table class="table table-light" >
				        <thead>
				        <tr>
				            <th scope="col">Product ID</th>
				            <th scope="col">Product Name</th>
				        </tr>
				        </thead>
				        <tbody>
					        <c:forEach var="product" items="${requestScope['products']}">
					            <tr>
					                <th scope="row">${product.getId()}</th>
					                <td>${product.getName()}</td>
					            </tr>
					       	</c:forEach>
				        </tbody>
				    </table>
			    </div>
			</div>
	    </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>