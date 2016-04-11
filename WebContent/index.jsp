<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
		<link rel="stylesheet" type="text/css" href="style.css">		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<title>Hospital</title>
	</head>
	<body>
	
	<div class="wrapper">
		<div class="header">
			<button class="button" onclick="window.location.href='../hospital'">Main</button>
			<button class="button" onclick="window.location.href='http://localhost:8080/hospital/UsersServlet?action=users'">Users</button>
		</div>
		<div class="main">
			<c:if test="${not empty contentpage}">
				<jsp:include page="${contentpage}" />		
			</c:if>
			
			<hr>
			<button class="button" onclick="window.location.href='../hospital'">Add New User</button>
			
		</div>
		<div class="footer">
			SoftServeInc 2016 Ch-039.Java
		</div>
	</div>
	
	</body>
</html>