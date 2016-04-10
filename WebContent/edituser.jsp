<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			dateFormat: 'dd.mm.yy',
			maxDate: "+0d",
			minDate: "-100y"
			});
		});
</script>

<form action="http://localhost:8080/hospital/EditServlet?action&fname&lname&date&role">
<table>
    <tr>
    	<td>First name:</td>
    	<td><input type="text" name="fname" value="${user.firstName }" required pattern="[A-Z][a-z]+"></td>
    </tr>
    <tr>
    	<td>Last name:</td>
    	<td><input type="text" name="lname" value="${user.lastName }" required pattern="[A-Z][a-z]+"></td>
    </tr>
    <tr>
    	<td>Birth date:</td>
    	<td><input type="text" name="date" id="datepicker" required pattern="\d{2}\.\d{2}\.\d{4}" value="${user.date }"></td>
    </tr>
    <tr>
    	<td>Role</td>
    	<td><select name="role">   
			<option value="doc">Doctor</option>
			<option selected value="pat">Patient</option>
			</select>
		</td>
    </tr>
</table>
<br>
<input type = "hidden" name = "id" value = "${user.id}"/>
<button type="submit" value="add" name="action">Add User</button>
<button type="reset">Clear form</button>
<input class="buttonSend" onclick="window.history.back();" type="button" value="Back"/>
</form>