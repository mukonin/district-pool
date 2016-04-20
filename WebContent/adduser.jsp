<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="container col-md-12">
	<div class="panel panel-default">
  		<div class="panel-heading">
  			<h4>
  				<c:if test="${not empty user.id}">
  					Edit user ${user}
  				</c:if>
  				<c:if test="${empty user.id}">
  					Add user
  				</c:if>
  			</h4>
  		</div>
  		<div class="panel-body">
  			<p class="validateTips">All form fields are required.</p>
			<form class="form-horizontal" role="form" action="/hospital/EditServlet" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="fname">First Name:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="fname" id="fname" placeholder="Enter First Name" value="${user.firstName }">
		      		</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="lname">Last Name:</label>
					<div class="col-sm-10">          
						<input type="text" class="form-control" name="lname" id="lname" placeholder="Enter Last Name" value="${user.lastName }">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="date">Date of Birth:</label>
					<div class="col-sm-10">          
						<input type="text" class="form-control" name="date" id="datepicker" data-date-format="mm.dd.yyyy" placeholder="dd.mm.yyyy" value="<joda:format value="${user.date}" pattern="dd.MM.yyyy"/>">
					</div>
				</div>
				<c:if test="${empty user.id}">
					<div class="form-group">
						<label class="control-label col-sm-2" for="lname">Role:</label>
						<div class="col-sm-10  text-left">          
							<select name="role" class="form-control">   
								<option selected value="pat">Patient</option>
								<option value="doc">Doctor</option>
							</select>
						</div>
					</div>
				</c:if>
				<div class="form-group text-left">        
					<div class="col-sm-offset-2 col-sm-10">
						<input type = "hidden" name = "id" value = "${user.id}"/>
						<button type="submit" value="add" name="action" class="btn btn-default" id="create-user">Add</button>				
						<button type="reset" class="btn btn-default">Reset</button>
					</div>
				</div>
			</form>
  		</div>
	</div>	
</div>