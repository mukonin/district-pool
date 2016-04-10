<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<table border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date Of Birth</th>
    </tr>
	<c:forEach var="user" items="${list}">
    <tr>
        <td onclick="document.location = 'http://localhost:8080/hospital/UserServlet?id=${user.id}';">
            <c:out value="${user.firstName}"/>
        </td>
        <td onclick="document.location = 'http://localhost:8080/hospital/UserServlet?id=${user.id}';">
            <c:out value="${user.lastName}"/>
        </td>
        <td onclick="document.location = 'http://localhost:8080/hospital/UserServlet?id=${user.id}';">
        	<joda:format pattern="dd.MM.yyyy" value="${user.date}"/>
        </td>
        <td>
        	<a href="http://localhost:8080/hospital/EditServlet?action=delete&id=${user.id}" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
        	<a href="http://localhost:8080/hospital/EditServlet?action=edit&id=${user.id}">Edit</a>
        	<input type = "hidden" name = "id" value = "${user.id}"/>
		</td>
    </tr>
    </c:forEach>
</table>