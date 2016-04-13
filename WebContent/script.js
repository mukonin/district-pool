$(function() {
	$( "#datepicker" ).datepicker({
		dateFormat: "dd.mm.yy",
		maxDate: "+0d",
		minDate: "-100y"
	});
});

$(document).ready(function () {	 
	window.setTimeout(function() {
	    $(".alert").fadeTo(1500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 3000);
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