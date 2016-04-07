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
	
<table border="1" cellpadding="1" cellspacing="1" style="width:500px">
	<tbody>
		<tr>
			<td>
				<form action="MainServlet">
                	<p><input name="action" type="submit" value="Doctors" /></p>
                    <p><input name="action" type="submit" value="Patients" /></p>
                    <p><input name="action" type="submit" value="Users" /></p>
                    <p><input name="action" type="submit" value="New" /></p>
                </form>
            </td>
			<td>
			
						
				<c:if test="${added == true}">
					Added to DB
				</c:if>
				
				<c:if test="${user == true}">
					<c:out value="${person}"/><p>
					<c:if test="${patientslist == true}">
						Patients:<p>
						<c:forEach var="user" items="${list}">
   							<a href = "http://localhost:8080/hospital/MainServlet?action=user&id=${user.id}"><c:out value="${user}"/> </a><p>
						</c:forEach>
					</c:if>					
					<c:if test="${doctorslist == true}">
						Doctor:<p>
   						<a href = "http://localhost:8080/hospital/MainServlet?action=user&id=${doctor.id}"><c:out value="${doctor}"/> </a><p>
					</c:if>
				</c:if>
				
				
				
				
				
			
			
				
			</td>
		</tr>
	</tbody>
</table>
 
</body>
</html>