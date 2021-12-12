<!--/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: 12 Dec, 2021
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
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
			                <h5 class="card-title">Add a claim</h5>
                        	<div class="alert alert-success hide" id="successMsg">Claim Added Successfully</div>
                           	<div class="alert alert-danger hide" id="errorMsg">Some Error Occurred. Please try again.</div>
                            <div class="mb-3">
                                <label for="claimDate" class="form-label">Claim Date: </label>
                                <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
                                <input type="hidden" name="deviceId" id="deviceId" value="${requestScope['deviceId']}"/>
                                <input type="date" class="form-control" name="claimDate" id="claimDate" readonly value="${today}"/>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description: </label>
                                <textarea name="description" id="description" maxlength="45" class="form-control"></textarea>
                            	<div class="error-msg hide" id="claimError">Please enter a description</div>
                            </div>
                            <div class="mb-3">
                                <button type="button" onClick="addClaim()" class="btn btn-primary">Add Claim</button>
                            </div>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
	</div>
   	<jsp:include page="footer.jsp"></jsp:include>