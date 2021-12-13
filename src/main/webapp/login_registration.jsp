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
	if(request.getSession().getAttribute("username") != null) {
		response.sendRedirect(request.getContextPath() + "/dashboard");
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
		    <div class="col-6">
		        <div class="card login-card mt-3">
		            <div class="card-body">
		                <h5 class="card-title">Login</h5>
                        <form method="post" action="login" novalidate>
                        <c:if test="${requestScope['invalidLogin'] != null}">
                            <div class="alert alert-danger">${requestScope['invalidLogin']}</div>
                         </c:if>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email address</label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="Email Address" value="${ requestScope['email'] != null ? requestScope['email'] : ''}">
                                <c:if test="${requestScope['emptyEmail'] != null}">
		                    	<div class="error-msg">${requestScope['emptyEmail']}</div>
		                    	</c:if>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" name="password" id="password" placeholder="Password" autocomplete="new-password">
                                <c:if test="${requestScope['emptyPassword'] != null}">
		                    	<div class="error-msg">${requestScope['emptyPassword']}</div>
		                    	</c:if>
                            </div>
                            <div class="mb-3">
                                <button type="submit" class="btn btn-primary">Log In</button>
                            </div>
                        </form>
		            </div>
		        </div>
		    </div>
		    <div class="col-6 d-flex justify-content-center">
		        <div class="card login-card mt-3">
		            <div class="card-body">
		                <h5 class="card-title">Sign Up</h5>
		                <form method="post" action="signup" novalidate>
		                	<c:if test="${requestScope['userExists'] != null}">
		                    	<div class="alert alert-danger">${requestScope['userExists']}</div>
		                    </c:if>
		                    <div class="mb-3">
		                        <label for="name" class="form-label">Name</label>
		                        <input type="text" name="name" class="form-control" id="name" placeholder="Full Name" value="${ requestScope['name'] != null ? requestScope['name'] : ''}">
		                        <c:if test="${requestScope['invalidName'] != null}">
		                    	<div class="error-msg">${requestScope['invalidName']}</div>
		                    	</c:if>
		                    </div>
		                    <div class="mb-3">
		                        <label for="email" class="form-label">Email address</label>
		                        <input type="email" name="email" class="form-control" id="email" placeholder="Email Address" value="${ requestScope['signupEmail'] != null ? requestScope['signupEmail'] : ''}">
		                       	<c:if test="${requestScope['invalidEmail'] != null}">
		                    	<div class="error-msg">${requestScope['invalidEmail']}</div>
		                    	</c:if>
		                    </div>
		                    <div class="mb-3">
		                        <label for="contact" class="form-label">Contact</label>
		                        <input type="tel" maxlength="10" name="contact" class="form-control" id="contact" placeholder="Contact Number" value="${ requestScope['contact'] != null ? requestScope['contact'] : ''}">
		                        <c:if test="${requestScope['invalidContact'] != null}">
		                    	<div class="error-msg">${requestScope['invalidContact']}</div>
		                    	</c:if>
		                    </div>
		                    <div class="mb-3">
		                        <label for="password" class="form-label">Password</label>
		                        <input type="password" name="password" class="form-control" id="password" placeholder="Password" autocomplete="new-password">
		                        <c:if test="${requestScope['invalidPassword'] != null}">
		                    	<div class="error-msg">${requestScope['invalidPassword']}</div>
		                    	</c:if>
		                    </div>
		                    <div class="mb-3">
		                        <label for="cpassword" class="form-label">Confirm Password</label>
		                        <input type="password" name="cpassword" class="form-control" id="cpassword" placeholder="Re-type Password">
		                        <c:if test="${requestScope['passwordMismatch'] != null}">
		                    	<div class="error-msg">${requestScope['passwordMismatch']}</div>
		                    	</c:if>
		                    </div>
		                    <div class="mb-3">
		                        <button type="submit" class="btn btn-primary">Sign Up</button>
		                    </div>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- form content ends -->
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>