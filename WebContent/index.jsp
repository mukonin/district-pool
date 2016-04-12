<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
		<link rel="stylesheet" type="text/css" href="style.css">		
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<title>Hospital</title>
	</head>
		
<script>
function tempAlert(msg,duration)
{
 var el = document.createElement("div");
 el.setAttribute("style","position:absolute;top:40%;left:20%;background-color:white;");
 el.innerHTML = msg;
 setTimeout(function(){
  el.parentNode.removeChild(el);
 },duration);
 document.body.appendChild(el);
}
</script>
	
	
	
	<body>
	
	<div class="wrapper">
		<div class="header">
			<button class="button" onclick="window.location.href='../hospital'">Main</button>
			<button class="button" onclick="window.location.href='http://localhost:8080/hospital/UsersServlet?action=users'">Users</button>
		</div>
		<div class="main">
			<c:if test="${not empty message}">
				<body onload="tempAlert(${message}, 2000)">
			</c:if>
			<c:if test="${not empty contentpage}">
				<jsp:include page="${contentpage}" />		
			</c:if>
		</div>
		<div class="footer">
			SoftServeInc 2016 Ch-039.Java
		</div>
	</div>
	
	</body>
</html>
