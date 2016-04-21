<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="container col-sm-12">
		<div class="panel panel-default">
	  		<div class="panel-heading">
				<h4>Patient ${user.firstName} ${user.lastName}</h4>			
			</div>			
			<div class="panel-body">
				<c:if test="${not empty user.doctor}">
					<a href = "http://localhost:8080/hospital/UserServlet?id=${user.doctor.person.id}">Doctor: ${user.doctor.firstName} ${user.doctor.lastName}</a>
					<br>
					<br>
					<form action="/hospital/EditServlet" method="post">
						<button type="submit" value="unlink" name="action" class="btn btn-md btn-danger" data-toggle="tooltip" title="Remove doctor">
  							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</button>
						<input type = "hidden" name = "id1" value = "${user.id}"/>
						<input type = "hidden" name = "id2" value = "${doctor.id}"/>						
						<button type="submit" value="linkpage" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Link to doctor">
  							<span class="glyphicon glyphicon-link" aria-hidden="true"></span>
						</button>
					</form>				
				</c:if>
				<c:if test="${empty doctor}">
					Doctor: none
					<br>	
					<form action="/hospital/EditServlet" method="post">						
						<input type = "hidden" name = "id" value = "${user.id}"/>
						<button type="submit" value="linkpage" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Link to doctor">
  							<span class="glyphicon glyphicon-link" aria-hidden="true"></span>
						</button>
					</form>	
				</c:if>
				<hr>
				other patient stuff	
			</div>						
		</div>
</div>