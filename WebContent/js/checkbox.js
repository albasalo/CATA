$(document).ready(function() {
	$("#pt").attr('checked', true);
				
	$("#pt").click(function() {
		$("#pt").attr('checked', true);
		$("#en").attr('checked', false);
	});
	
	$("#en").click(function() {
		$("#en").attr('checked', true);
		$("#pt").attr('checked', false);
	});
});