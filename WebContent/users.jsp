<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

Hospital Users
<hr>

<div class="dropdown">
  <button class="dropbtn">Select Role</button>
  <div class="dropdown-content">
    <a href = "http://localhost:8080/hospital/UsersServlet?action=users">All</a>
    <a href="http://localhost:8080/hospital/UsersServlet?action=doctors">Doctors</a>
    <a href="http://localhost:8080/hospital/UsersServlet?action=patients">Patients</a>
  </div>
</div>

<hr>

<table>
    <tr>
        <th>Users</th>
    </tr>
	<c:forEach var="user" items="${list}">
    <tr>
        <td>
        	<button class="button" onclick="document.location = 'http://localhost:8080/hospital/UserServlet?id=${user.id}';">${user}</button>
        </td>
        <td>
        	<form action="http://localhost:8080/hospital/EditServlet?action&id=${user.id}">
        		<button class="button"  type="submit" value="edit" name="action">Edit</button>
        		<button class="button"  type="submit" value="delete" name="action" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
        		<input type = "hidden" name = "id" value = "${user.id}"/>
        	</form>
		</td>
    </tr>
    </c:forEach>
</table>