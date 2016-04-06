<%@page import="entities.Person"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
 
<h3><a href = "../hospital">  Hospital</a></h3>


<table border="1" cellpadding="1" cellspacing="1" style="width:500px">
	<tbody>
		<tr>
			<td>
				<form action="MainServlet">
                                  <p><input name="get" type="submit" value="Doctors" /></p>
                                  <p><input name="get" type="submit" value="Patients" /></p>
                                  <p><input name="get" type="submit" value="Users" /></p>
                                  <p><input name="add" type="submit" value="New" /></p>
                </form>
            </td>
			<td>
				<% 				
				if (request.getAttribute("1") != null) {
				ArrayList<Person> list = (ArrayList<Person>)request.getAttribute("1");
				for (Person person : list) {
					out.println("<p><a href=\"http://localhost:8080/hospital/MainServlet?user=" + person.getId() + "\"> " + person.toString() + "</a></p>");
				}
				}
				
				//<a href="URL">!!!!!!!</a>
				
				%>
			</td>
		</tr>
	</tbody>
</table>
 
</body>
</html>