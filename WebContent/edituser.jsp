<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<h3><c:out value="${pagename }"></c:out></h3>
<br>

<div class="container col-md-12">
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="control-label col-sm-2" for="fname">First Name:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="fname" placeholder="Enter First Name" value="${user.firstName }">
      		</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="lname">Last Name:</label>
			<div class="col-sm-10">          
				<input type="text" class="form-control" id="lname" placeholder="Enter Last Name" value="${user.lastName }">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="lname">Date of Birth:</label>
			<div class="col-sm-10">          
				<input type="text" name="date" class="form-control" id="datepicker" data-date-format="mm.dd.yyyy" placeholder="dd.mm.yyyy" value="<joda:format value="${user.date}" pattern="dd.MM.yyyy"/>">
			</div>
		</div>
		<div class="form-group text-left">        
			<div class="col-sm-offset-2 col-sm-10">
				<input type = "hidden" name = "id" value = "${user.id}"/>
				<button type="submit" class="btn btn-default">Save</button>
			</div>
		</div>
	</form>
</div>


<script>
	$(function() {
		$( "#datepicker" ).datepicker({
			dateFormat: "dd.mm.yy",
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
    	<td><input type="text" name="date" id="datepicker" required pattern="\d{2}\.\d{2}\.\d{4}" /></td>
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
<button type="submit" value="update" name="action">Save</button>
<button type="reset">Reset</button>
<input class="buttonSend" onclick="window.history.back();" type="button" value="Back"/>
</form>


  
			