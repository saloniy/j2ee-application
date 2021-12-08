<!--/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Saloni Yadav, Preeti Kshirsagar; Student ID: N01414159, N01494576; Date: ____________________
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
			                <h5 class="card-title">Add a claim</h5>
	                        <form method="post" action="add-claim" novalidate>
	                        <!--<c:if test="${requestScope['error'] != null}">
	                            <div class="alert alert-danger">${requestScope['error']}</div>
	                         </c:if>-->
	                            <div class="mb-3">
	                                <label for="claimDate" class="form-label">Claim Date: </label>
	                                <input type="date" class="form-control" name="claimDate" id="claimDate" readonly value="{add today's date}">
	                            </div>
	                            <div class="mb-3">
	                                <label for="description" class="form-label">Description: </label>
	                                <textarea name="description" maxlength="45" class="form-control"></textarea>
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