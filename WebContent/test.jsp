	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>		
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
  $(function() {
		$( "#datepicker" ).datepicker({
			dateFormat: "dd.mm.yy",
			maxDate: "+0d",
			minDate: "-100y"
			});
		});
  
$(function() {
	var dialog, 
	form,
	fname = $( "#fname" ),
	lname = $( "#lname" ),
	date = $('#datepicker'),
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
		valid = valid && checkRegexp( fname, /^[A-Z][a-z]+$/, "First name may consist of letters with first capital." );
		valid = valid && checkLength( lname, "last name", 2, 16 );
		valid = valid && checkRegexp( lname, /^[A-Z][a-z]+$/, "Last name may consist of letters with first capital." );
		valid = valid && checkRegexp( date, /^\d{2}\.\d{2}\.\d{4}$/, "Wrong date format")
		return valid;
	}
 
	$( "#create-user" ).button().on( "click", function() {
		//event.preventDefault();
		if ( !(addUser()) )  {			
		event.preventDefault();
		};
	});
	
});
  </script>
  
  

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
 
<div title="Add new user">
 	<p class="validateTips">All form fields are required.</p>
		<form action="http://google.com">
			<fieldset>
				<label for="fname">First Name</label>
				<input type="text" name="fname" id="fname" value="" class="text ui-widget-content ui-corner-all">
				<label for="lname">Last Name</label>
				<input type="text" name="lname" id="lname" value="" class="text ui-widget-content ui-corner-all">
				<label for="date">Date of birth</label>
				<input name="date" id="datepicker" value="" class="text ui-widget-content ui-corner-all">
					<label for="role">Select role</label>
					<select name="role" id="role" class="text ui-widget-content ui-corner-all">
						<option selected="selected">Patient</option>
						<option>Doctor</option>
					</select>
			</fieldset>
					<button type="submit" id="create-user" class="button">Continue</button>
		</form>
</div>			
