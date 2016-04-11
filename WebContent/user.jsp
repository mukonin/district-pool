<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<form action="http://localhost:8080/hospital/EditServlet?action&id=${user.id}">
	<c:out value="${user}"/>, <c:out value="${role}"/>
	<button type="submit" value="edit" name="action">Edit</button>
	<button type="submit" value="delete" name="action" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
	<input type = "hidden" name = "id" value = "${user.id}"/>
</form>
	
<c:if test="${not empty doctor}">
	<hr>
	Doctor: <a href = "http://localhost:8080/hospital/UserServlet?id=${doctor.id}"> ${doctor }</a>
</c:if>

<c:if test="${not empty list}">
<hr>
<table>
    <tr>
        <th>Patients</th>
	</tr>
	<c:forEach var="user" items="${list}">
	<tr>
		<td onclick="document.location = 'http://localhost:8080/hospital/UserServlet?id=${user.id}';">
			<a href="http://localhost:8080/hospital/UserServlet?id=${user.id}"><c:out value="${user}"/></a>
		</td>
		<td>
        	<form action="http://localhost:8080/hospital/EditServlet?action&id=${user.id}">
        		<button type="submit" value="edit" name="action">Edit</button>
        		<button type="submit" value="delete" name="action" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
        		<input type = "hidden" name = "id" value = "${user.id}"/>
        	</form>
		</td>
    </tr>
    </c:forEach>
</table>
</c:if>