<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>		
		<link rel="stylesheet" type="text/css" href="styletest.css">		
		<title>Hospital</title>
	</head>
	<body>  
		<div class="container-fluid text-center">    
			<div class="row content">
				<div class="col-sm-3 sidenav">
					<ul class="nav nav-pills nav-stacked">
						<li class="active"><a href="../hospital">Home</a></li>
						<li><a href="UsersServlet?action=users">Users</a></li>
						<li><a href="UsersServlet?action=doctors">Doctors</a></li>
						<li><a href="UsersServlet?action=patients">Patients</a></li>
						<li><a href="EditServlet?action=new">Add User</a></li>
					</ul>
				<br>
				</div>
            
				<div class="col-sm-9 text-center"> 
					<c:if test="${not empty message}">
						<div class="alert alert-warning fade in">							
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<c:out value="${message }"></c:out>
						</div>						
					</c:if>
					<c:if test="${not empty contentpage}">
						<jsp:include page="${contentpage}" />		
					</c:if>
					AAAAA
            
				</div>
			</div>
		</div>        
		<footer class="container-fluid text-center">
			<p>SoftServeInc Ch-039.Java</p>
		</footer>
	</body>
</html>
