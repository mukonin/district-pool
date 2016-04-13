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