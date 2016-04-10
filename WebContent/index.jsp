<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Hospital</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
	<body>
		<div id="header">
			Hospital
		</div>
		<div id="menu">
			<a href = "../hospital">Main Page</a>
			<a href = "http://localhost:8080/hospital/UserServlet?action=doctors">Doctors</a><br>
			<a href = "http://localhost:8080/hospital/UserServlet?action=patients">Patients</a><br>
			<a href = "http://localhost:8080/hospital/UserServlet?action=users">Users</a><br>
			<hr>
		</div>
		<div id="content">
			${contentpage}
		</div>
		<div id="footer">
			SoftServeInc 2016 Ch-039.Java
		</div>
	</body>
</html>