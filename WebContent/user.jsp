<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

${role} : <a href = "../hospital">${person }</a><br>

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
<input name="action" type="submit" value="Update User"><input name="action" type="submit" value="Delete User">
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
	
<c:if test="${role == 'patient'}">
	<c:if test="${not empty doctor}">
		Doctor: <a href = "http://localhost:8080/hospital/MainServlet?action=user&id=${doctor.id}"> ${doctor }</a>
	</c:if>
	<c:if test="${empty doctor}">
		Select doctor: <br>
		<input type = "hidden" name = "id" value = "${person.id}"/>
<table border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date Of Birth</th>
    </tr>
	<c:forEach var="user" items="${list2}">
    <tr onclick="document.location = 'http://localhost:8080/hospital/MainServlet?action=bind&id1=${user.id}&id2=${person.id}';">
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
</c:if>