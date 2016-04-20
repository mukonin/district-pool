<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4>Doctors</h4>
	</div>
	<div class="panel-body">
		<div class="container col-md-12">        
			<table class="table table-striped text-left">
				<thead>
					<tr>
						<th>Doctor name</th>
						<th>Date of birth</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				
		    	<tbody>
		    		<c:forEach var="user" items="${doctorList}">
					<tr>
						<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${user.person.id}'">${user.person.lastName}, ${user.person.firstName}</td>
						<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${user.person.id}'"><joda:format pattern="dd.MM.yyyy" value="${user.person.date}"/></td>						
						<td class="text-center col-md-1">
							<form  action="http://localhost:8080/hospital/UserServlet?id=${user.person.id}">
								<button type="submit" class="btn btn-md btn-primary" data-toggle="tooltip" title="Information">
  									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</button>
								<input type = "hidden" name = "id" value = "${user.person.id}"/>
							</form>
						</td>
						<td class="text-center col-md-1">
							<form  action="http://localhost:8080/hospital/EditServlet?action&id=${user.person.id}">
								<button type="submit" value="edit" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Edit">
  									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								</button>
								<input type = "hidden" name = "id" value = "${user.person.id}"/>
							</form>
						</td>
						<td class="text-center col-md-1">
							<form action="/hospital/EditServlet" method="post">
								<button type="submit" value="delete" name="action" class="btn btn-md btn-danger" data-toggle="tooltip" title="Delete" onclick="return confirm('Are you sure you want to delete Doctor ${user.person.firstName} ${user.person.lastName}');">
  									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</button>								
								<input type = "hidden" name = "id" value = "${user.person.id}"/>
							</form>
						</td>
					</tr>
					</c:forEach>
				</tbody>				
			</table>
		</div>
	</div>
</div>