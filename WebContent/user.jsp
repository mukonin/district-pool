<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="container col-sm-12">	
	<c:if test="${role == 'patient'}">
		<div class="panel panel-default">
	  		<div class="panel-heading">
				<h4>Patient ${user.firstName} ${user.lastName}</h4>			
			</div>			
			<div class="panel-body">
				<c:if test="${not empty doctor}">
					<a href = "http://localhost:8080/hospital/UserServlet?id=${doctor.id}">Doctor: ${doctor.firstName} ${doctor.lastName}</a>
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
						<input type = "hidden" name = "id1" value = "${user.id}"/>
						<button type="submit" value="linkpage" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Link to doctor">
  							<span class="glyphicon glyphicon-link" aria-hidden="true"></span>
						</button>
					</form>	
				</c:if>
				<hr>
				other patient stuff	
			</div>						
		</div>
	</c:if>	
	
	<c:if test="${role == 'doctor'}">
		<div class="panel panel-default">
	  		<div class="panel-heading">
				<h4>Doctor ${user.firstName} ${user.lastName} patients</h4>
			</div>
			<div class="panel-body">
				<c:if test="${not empty list}">
					<table class="table table-striped text-left">
						<thead>
							<tr>
								<th>Name</th>
								<th></th>
							</tr>
						</thead>
				    	<tbody>
				    		<c:forEach var="patient" items="${list}">
								<tr>
									<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${patient.id}'">${patient.firstName} ${patient.lastName}</td>
									<td class="text-center col-md-1">
										<form action="/hospital/EditServlet" method="post">
											<button type="submit" value="unlink" name="action" class="btn btn-md btn-danger" data-toggle="tooltip" title="Remove patient">
  												<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
											</button>
											<input type = "hidden" name = "id1" value = "${user.id}"/>
											<input type = "hidden" name = "id2" value = "${patient.id}"/>
										</form>
									</td>							
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty list}">		
					Doctor has no patients
					<br>
				</c:if>		
				<form action="/hospital/EditServlet" method="post">
					<input type = "hidden" name = "id1" value = "${user.id}"/>
					<button type="submit" value="linkpage" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Add patient">
  							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</button>
				</form>		
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