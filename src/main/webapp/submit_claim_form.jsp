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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Devices" %>

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
	                        <form method="post" action="add-claim" novalidate>
	                        <!--<c:if test="${requestScope['error'] != null}">
	                            <div class="alert alert-danger">${requestScope['error']}</div>
	                         </c:if>-->
	                         <div class="mb-3">
	                                <label for="claimProductId" class="form-label">Claim Product Id : </label>
	                                <input type="text" class="form-control" name="claimProductId" id="claimProductId" readonly value="${requestScope['claimDevice'].getId()}">
	                            </div>
	                            <div class="mb-3">
	                                <label for="claimProductSerial" class="form-label">Claim Product Serial No : </label>
	                                <input type="text" class="form-control" name="claimProductSerial" id="claimProductSerial" readonly value="${requestScope['claimDevice'].getSerialNumber()}">
	                            </div>
	                         <div class="mb-3">
	                                <label for="claimProduct" class="form-label">Claim Product Name : </label>
	                                <input type="text" class="form-control" name="claimProduct" id="claimProduct" readonly value="${requestScope['claimDevice'].getProductName()}">
	                            </div>	                            
	                            <div class="mb-3">
	                                <label for="claimDate" class="form-label">Claim Date: </label>
	                                <fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
	                                <input type="date" class="form-control" name="claimDate" id="claimDate" readonly value="${today}">
	                            </div>
	                            <div class="mb-3">
	                                <label for="description" class="form-label">Description: </label>
	                                <textarea name="description" maxlength="45" class="form-control" ></textarea>
	                                <c:if test="${requestScope['emptyDesc'] != null}">
		                    	<div class="error-msg">${requestScope['emptyDesc']}</div>
		                    	</c:if>
	                            </div>
	                            <div class="mb-3">
	                                <button type="submit" class="btn btn-primary">Submit Claim</button>
	                            </div>
	                        </form>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
	</div>
   	<jsp:include page="footer.jsp"></jsp:include>