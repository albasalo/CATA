$(document).ready(function() {
	$("#pt").attr('checked', true);
				
	$("#pt").click(function() {
		$("#en").attr('checked', false);
	});
	
	$("#en").click(function() {
		$("#pt").attr('checked', false);
	});
});