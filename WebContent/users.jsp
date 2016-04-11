<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

Hospital Users
<hr>

<div class="dropdown">
  <button class="dropbtn">Select Role</button>
  <div class="dropdown-content">
    <a href = "http://localhost:8080/hospital/UsersServlet?action=users">All</a>
    <a href="http://localhost:8080/hospital/UsersServlet?action=doctors">Doctors</a>
    <a href="http://localhost:8080/hospital/UsersServlet?action=patients">Patients</a>
  </div>
</div>

<hr>

<table>
    <tr>
        <th>Users</th>
    </tr>
	<c:forEach var="user" items="${list}">
    <tr>
        <td>
        	<button class="button" onclick="document.location = 'http://localhost:8080/hospital/UserServlet?id=${user.id}';">${user}</button>
        </td>
        <td>
        	<form action="http://localhost:8080/hospital/EditServlet?action&id=${user.id}">
        		<button class="button"  type="submit" value="edit" name="action">Edit</button>
        		<button class="button"  type="submit" value="delete" name="action" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
        		<input type = "hidden" name = "id" value = "${user.id}"/>
        	</form>
		</td>
    </tr>
    </c:forEach>
</table>



<style>
    body { font-size: 62.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>
  <script>
  $(function() {
		$( "#datepicker" ).datepicker({
			dateFormat: 'dd.mm.yy',
			maxDate: "+0d",
			minDate: "-100y"
			});
		});
  
  $(function() {
    var dialog, form,
 
      
      fname = $( "#fname" ),
      lname = $( "#lname" ),
      date = $("#date"),
      allFields = $( [] ).add( fname ).add( lname ).add ( date ),
      tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );
        return false;
      } else {
        return true;
      }
    }
 
    function addUser() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );

      valid = valid && checkLength( fname, "first name", 2, 16 );
      valid = valid && checkLength( lname, "last name", 2, 16 );

      valid = valid && checkRegexp( fname, /^[A-Z][a-z]+$/, "First name may consist of letters with first capital." );
      valid = valid && checkRegexp( lname, /^[A-Z][a-z]+$/, "Last name may consist of letters with first capital." );
      //valid = valid && checkRegexp( date, /^\d{2}\.\d{2}\.\d{4}$/, "Date must be like 23.02.1965" );
 
      if ( valid ) {
        $( "#users tbody" ).append( "<tr>" +
          "<td>" + fname.val() + "</td>" +
          "<td>" + lname.val() + "</td>" +
          "<td>" + date + "</td>" +
        "</tr>" );
        dialog.dialog( "close" );
      }
      return valid;
    }
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 300,
      width: 350,
      modal: true,
      buttons: {
        "Add New User": addUser,
        Cancel: function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });
 
    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
      addUser();
    });
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
  });
  </script>
</head>
<body>
 
<div id="dialog-form" title="Add new user">
 	<p class="validateTips">All form fields are required.</p>
		<form action="http://localhost:8080/hospital/EditServlet?action&fname&lname&date&role">
			<fieldset>
				<label for="fname">First Name</label>
				<input type="text" name="fname" id="fname" value="" class="text ui-widget-content ui-corner-all">
				<label for="lname">Last Name</label>
				<input type="text" name="lname" id="lname" value="" class="text ui-widget-content ui-corner-all">
				<label for="date">Date of birth</label>
				<input type="text" name="date" id="datepicker" value="" class="text ui-widget-content ui-corner-all">

				<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
			</fieldset>
			<button type="submit" value="add" name="action">Save</button>
		</form>
</div>
 
 
<div id="users-contain" class="ui-widget">
  <h1>Existing Users:</h1>
  <table id="users" class="ui-widget ui-widget-content">
    <thead>
      <tr class="ui-widget-header ">
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>01.01.1970</td>
      </tr>
    </tbody>
  </table>
</div>
<button id="create-user">Create new user</button>