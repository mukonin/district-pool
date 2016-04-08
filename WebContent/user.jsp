<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style type="text/css">
</style>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hospital</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
  $(function() {
    $( "#datepicker" ).datepicker({dateFormat: 'dd.mm.yy'});
  });
  </script>
</head>
<body>
 
<h1>Hospital</h1>
<h4><a href = "../hospital">Main Page</a>/${page}</h4>

	<form action="MainServlet">
		<input name="action" type="submit" value="Doctors" />
		<input name="action" type="submit" value="Patients" />
		<input name="action" type="submit" value="Users" />
		<input name="action" type="submit" value="New" />
	</form>
	<br>
	
	<c:if test="${role == 'doctor'}">
		Doctor
		<br>
	</c:if>
	
	<c:if test="${role == 'patient'}">
		Patient
		<br>
	</c:if>
	${person }<br>

<form action="http://localhost:8080/hospital/MainServlet?action&fname&lname&date&id&role">
<table>
    <tr>
    	<td>First name:</td>
    	<td><input type="text" name="fname" value="${person.firstName }"></td>
    </tr>
    <tr>
    	<td>Last name:</td>
    	<td><input type="text" name="lname" value="${person.lastName }"></td>
    </tr>
    <tr>
    	<td>Birth date:</td>
        	<joda:format pattern="dd.MM.yyyy" value="${user.date}"/>
    	<td><input type="text" name="date" id="datepicker" value = "${date}"/></td>
    </tr>  
    <tr>
    	<td>ID:</td>
    	<td>${id}<input type = "hidden" name = "id" value = "${id}"/></td>
    </tr>    
</table>
<br>
<input name="action" type="submit" value="Update User">
</form>

<c:if test="${role == 'doctor'}">
		Patients:
		<br>
<table border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date Of Birth</th>
    </tr>
	<c:forEach var="user" items="${list}">
    <tr onclick="document.location = 'http://localhost:8080/hospital/MainServlet?action=user&id=${user.id}';">
        <td>
            <c:out value="${user.firstName}"/>
        </td>
        <td>
            <c:out value="${user.lastName}"/>
        </td>
        <td>
        	<joda:format pattern="dd.MM.yyyy" value="${user.date}"/>
        </td>
    </tr>
    </c:forEach>
</table>
</c:if>
	
	
	
	
	
	

</body>
</html>