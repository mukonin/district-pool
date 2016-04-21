<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="container col-sm-12">	
	<div class="panel panel-default">
	  	<div class="panel-heading">
			<c:if test="${role == 'patient'}">
				<h4>Link patient ${user.firstName} ${user.lastName} to doctor</h4>	
			</c:if>	
			<c:if test="${role == 'doctor'}">
				<h4>Doctor ${user.firstName} ${user.lastName} add patient</h4>	
			</c:if> 	
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
			    		<c:if test="${role == 'doctor' }">
				    		<c:forEach var="user1" items="${list}">
								<tr>
									<td>${user1.firstName} ${user1.lastName}</td>
									<td class="text-center col-md-1">
										<form action="/hospital/EditServlet" method="post">
											<button type="submit" value="link" name="action" class="btn btn-danger btn-sm">Select</button>
											<input type = "hidden" name = "id1" value = "${user.id}"/>
											<input type = "hidden" name = "id2" value = "${user1.id}"/>
										</form>
									</td>							
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${role == 'patient' }">
				    		<c:forEach var="user1" items="${list}">
								<tr>
									<td>${user1.person.firstName} ${user1.person.lastName}</td>
									<td class="text-center col-md-1">
										<form action="/hospital/EditServlet" method="post">
											<button type="submit" value="link" name="action" class="btn btn-danger btn-sm">Select</button>
											<input type = "hidden" name = "id1" value = "${user1.person.id}"/>
											<input type = "hidden" name = "id2" value = "${user.id}"/>
										</form>
									</td>							
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</c:if>				
		</div>						
	</div>
</div>