$(document).ready(function(){
	var form = $("#advice_form");
	var file = $("#file");
	
	file.keyup(validateFile);
	file.blur(validateFile);

	form.submit(function() {
		if(validateFile()) {
			return true;
		}
		else {
			return false;
		}
	});

	function validateFile() {
		if(file.val().length == 0) {
			file.removeClass("input_border");
			file.addClass("error");
			return false;
		}
		else {
			file.removeClass("error");
			file.addClass("input_border");
			return true;
		}
	}
});