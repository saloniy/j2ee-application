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
			<!--form content -->
			<div class="row">
				<div class="row my-3">
			        <div class="col-12">
			            <a href="dashboard" class="btn btn-success">Back to Dashboard</a>
			        </div>
			    </div>
			    <div class="col-6">
			        <div class="card login-card mt-3">
			            <div class="card-body">
			                <h5 class="card-title">Register Device</h5>
	                        <form method="post" action="register-device" novalidate>
	                        <c:if test="${requestScope['success'] != null}">
	                            <div class="alert alert-success" id="successMsg">${requestScope['success']}</div>
	                        </c:if>
	                        <c:if test="${requestScope['error'] != null}">    
                            	<div class="alert alert-danger" id="errorMsg">${requestScope['error']}</div>
	                         </c:if>
	                            <div class="mb-3">
	                                <label for="username" class="form-label">Email ID:</label>
	                                <input type="text" class="form-control" name="username" id="username" value="${sessionScope['username']}" readonly>
	                            </div>
	                            <div class="mb-3">
	                                <label for="productName" class="form-label">Product Name: </label>
	                                <select name="productName" class="form-select">
	                                	<option value="">Select Product</option>
		                                <c:forEach var="product" items="${requestScope['products']}">
		                               		<option value="${product.getId()}">${product.getName()}</option>
						       			</c:forEach>
	                                </select>
	                                <c:if test="${requestScope['invalidProduct'] != null}">
		                    	<div class="error-msg">${requestScope['invalidProduct']}</div>
		                    	</c:if>
	                            </div>
	                            <div class="mb-3">
	                                <label for="serialNum" class="form-label">Serial Number: </label>
	                                <input type="text" class="form-control" name="serialNum" id="serialNum" placeholder="Enter Serial Number" value="${ requestScope['serialNum'] != null ? requestScope['serialNum'] : ''}">
	                                <c:if test="${requestScope['invalidSerial'] != null}">
		                    	<div class="error-msg">${requestScope['invalidSerial']}</div>
		                    	</c:if>
	                            </div>
	                            <div class="mb-3">
	                                <label for="purchaseDate" class="form-label">Purchase Date: </label>
	                                <input type="date" class="form-control" name="purchaseDate" id="purchaseDate" placeholder="yyyy/MM/dd" value="${ requestScope['purchaseDate'] != null ? requestScope['purchaseDate'] : ''}">
	                                <c:if test="${requestScope['invalidDate'] != null}">
		                    	<div class="error-msg">${requestScope['invalidDate']}</div>
		                    	</c:if>
	                            </div>
	                            <div class="mb-3">
	                                <button type="submit" class="btn btn-primary">Register Device</button>
	                            </div>
	                        </form>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
	</div>
   	<jsp:include page="footer.jsp"></jsp:include>