<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Hospital</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
	<body>
		<div id="header">
			Hospital ${pagename}
		</div>
		<div id="menu">
			<a href = "../hospital">Main Page</a>
			<a href = "http://localhost:8080/hospital/UsersServlet?action=doctors">Doctors</a><br>
			<a href = "http://localhost:8080/hospital/UsersServlet?action=patients">Patients</a><br>
			<a href = "http://localhost:8080/hospital/UsersServlet?action=users">Users</a><br>
			<hr>
			<a href = "http://localhost:8080/hospital/EditServlet?action=new">Add User</a><br>
			<hr>
			<a href = "" onclick="window.history.back();">Back</a>
		</div>
		<div id="content">
			${message}			
			<c:if test="${showcontent == true}">
				<jsp:include page="${contentpage}" />		
			</c:if>
		</div>
		<div id="footer">
			SoftServeInc 2016 Ch-039.Java
		</div>
	</body>
</html>