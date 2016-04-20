<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<div class="panel panel-default">
	<div class="panel-heading">
		<h4>${pagename}</h4>
	</div>
	<div class="panel-body">
		<div class="container col-md-12">        
			<table class="table table-striped text-left">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Date of Birth</th>
						<c:if test="${pagename == 'Patients'}">
							<th>Doctor</th>
						</c:if>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
		    	<tbody>
		    		<c:forEach var="user" items="${patientList}">
					<tr>
						<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${user.id}'">${user.firstName}</td>
						<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${user.id}'">${user.lastName}</td>
						<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${user.id}'"><joda:format pattern="dd.MM.yyyy" value="${user.date}"/></td>
						<c:if test="${pagename == 'Patients'}">
							<c:if test="${not empty user.doctor}">
								<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${user.doctor.id}'">${user.doctor}</td>
							</c:if>
							<c:if test="${empty user.doctor}">
								<td>
									<form action="/hospital/EditServlet" method="post">		
										None				
										<input type = "hidden" name = "id1" value = "${user.id}"/>
										<button type="submit" value="linkpage" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Link to doctor">
	  										<span class="glyphicon glyphicon-link" aria-hidden="true"></span>
										</button>
									</form>
								</td>
							</c:if>
						</c:if>
						<td class="text-center col-md-1">
							<form  action="http://localhost:8080/hospital/UserServlet?id=${user.id}">
								<button type="submit" class="btn btn-md btn-primary" data-toggle="tooltip" title="Information">
  									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</button>
								<input type = "hidden" name = "id" value = "${user.id}"/>
							</form>
						</td>
						<td class="text-center col-md-1">
							<form  action="http://localhost:8080/hospital/EditServlet?action&id=${user.id}">
								<button type="submit" value="edit" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Edit">
  									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								</button>
								<input type = "hidden" name = "id" value = "${user.id}"/>
							</form>
						</td>
						<td class="text-center col-md-1">
							<form action="/hospital/EditServlet" method="post">
								<button type="submit" value="delete" name="action" class="btn btn-md btn-danger" data-toggle="tooltip" title="Delete" onclick="return confirm('Are you sure you want to delete ${user.firstName} ${user.lastName}');">
  									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>								
								<input type = "hidden" name = "id" value = "${user.id}"/>
							</form>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${pagename == 'Patients'}">
		<button onclick="window.location.href='UsersServlet?action=patients&sort=true'" class="btn btn-md btn-success" data-toggle="tooltip" title="Sort">
  			<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>
		</button>
	</c:if>
	<c:if test="${pagename == 'Doctors'}">
		<button onclick="window.location.href='UsersServlet?action=doctors&sort=true'" class="btn btn-md btn-success" data-toggle="tooltip" title="Sort">
  			<span class="glyphicon glyphicon-signal" aria-hidden="true"></span>
		</button>
	</c:if>
</div>