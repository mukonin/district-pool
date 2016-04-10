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