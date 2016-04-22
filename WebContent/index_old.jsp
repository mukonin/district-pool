<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style type="text/css">
</style>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hospital</title>
</head>
<body>
 
<h1>Hospital</h1>
<h4><a href = "../hospital">Main Page</a></h4>

	<form action="MainServlet">
		<input name="action" type="submit" value="Doctors" />
		<input name="action" type="submit" value="Patients" />
		<input name="action" type="submit" value="Users" />
		<input name="action" type="submit" value="New" />
	</form>
	<br>
	
	

${message }
 
</body>
</html>