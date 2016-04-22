<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="container col-sm-12">
	<div class="panel panel-default">
  		<div class="panel-heading">
			<h4>Doctor ${doctor.firstName} ${doctor.lastName} patients</h4>
		</div>
		<div class="panel-body">
			<c:if test="${not empty doctor.patients}">
				<table class="table table-striped text-left">
					<thead>
						<tr>
							<th>Name</th>
							<th></th>
						</tr>
					</thead>
			    	<tbody>
			    		<c:forEach var="patient" items="${doctor.patients}">
							<tr>
								<td onclick="location.href='http://localhost:8080/hospital/UserServlet?id=${patient.id}'">${patient.lastName}, ${patient.firstName}</td>
								<td class="text-center col-md-1">
									<form action="/hospital/EditServlet" method="post">
										<button type="submit" value="unlink" name="action" class="btn btn-md btn-danger" data-toggle="tooltip" title="Remove patient">
											<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
										</button>
										<input type = "hidden" name = "id1" value = "${doctor.id}"/>
										<input type = "hidden" name = "id2" value = "${patient.id}"/>
									</form>
								</td>							
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${empty doctor.patients}">		
				Doctor has no patients
				<br>
			</c:if>		
			<form action="/hospital/EditServlet" method="post">
				<input type = "hidden" name = "id" value = "${doctor.id}"/>
				<button type="submit" value="linkpage" name="action" class="btn btn-md btn-warning" data-toggle="tooltip" title="Add patient">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
			</form>		
			<hr>
			other doctor stuff
		</div>
	</div>
</div>