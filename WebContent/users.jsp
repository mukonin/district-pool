<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<h3><c:out value="${pagename }"></c:out></h3>
<br>

<div class="container col-md-12">        
	<table class="table table-striped text-left">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Date of Birth</th>
			</tr>
		</thead>
    	<tbody>
    		<c:forEach var="user" items="${list}">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td><joda:format pattern="dd.MM.yyyy" value="${user.date}"/></td>
				<td class="text-right">
					<form  action="http://localhost:8080/hospital/EditServlet?action&id=${user.id}">
						<button type="submit" value="edit" name="action" class="btn btn-warning btn-sm">Edit</button>
						<input type = "hidden" name = "id" value = "${user.id}"/>
					</form>
				</td>
				<td class="text-right">
					<form action="/hospital/es" method="post">
						<button type="submit" value="delete" name="action" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
						<input type = "hidden" name = "id" value = "${user.id}"/>
					</form>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
