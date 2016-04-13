<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="container col-sm-12">
	
	<div class="panel panel-default">
  		<div class="panel-heading">
  			<h2>User Information</h2>
  		</div>
  		<div class="panel-body">
  			<div class="row">
				<div class="col-sm-6 text-right">
					First name<br>
					Last Name<br>
					Date of birth<br>
					ID<br>
					Role<br>
				</div>
				<div class="col-sm-6 text-left">
					<c:out value="${user.firstName}"/><br>
					<c:out value="${user.lastName}"/><br>
					<joda:format pattern="d MMMM yyyy" value="${user.date}" locale="US"/><br>
					<c:out value="${user.id}"/><br>
					<c:out value="${role}"/><br>
				</div>
			</div>
  		</div>
	</div>	

	<c:if test="${role == 'patient'}">
		<div class="panel panel-default">
	  		<div class="panel-heading">
				<h2>Patient Information</h2>			
			</div>			
			<div class="panel-body">
				<c:if test="${not empty doctor}">
					Doctor: ${doctor.firstName} ${doctor.lastName} 
					<button class="btn btn-primary btn-sm" onclick="window.location.href='UserServlet?id=${doctor.id}'">View</button>
					<hr>
					other patient stuff	
				</c:if>
				<c:if test="${empty doctor}">
						
				</c:if>
			</div>						
		</div>
	</c:if>	
	
	<c:if test="${role == 'doctor'}">
		<div class="panel panel-default">
	  		<div class="panel-heading">
				<h2>Doctor Information</h2>
			</div>
			<div class="panel-body">
				<c:if test="${not empty list}">
					<h4>Patients</h4>
					<table class="table table-striped text-left">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
							</tr>
						</thead>
				    	<tbody>
				    		<c:forEach var="user" items="${list}">
								<tr>
									<td>${user.firstName}</td>
									<td>${user.lastName}</td>
									<td class="text-center col-md-1">
										<button class="btn btn-primary btn-sm" onclick="window.location.href='UserServlet?id=${user.id}'">View</button>
									</td>							
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty list}">
						
				</c:if>
				<hr>
				other doctor stuff
			</div>
		</div>
	</c:if>
	
	
	
	
</div>

<!--

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

-->