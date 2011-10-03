$(document).ready(function(){
	var form = $("#recover_form");
	var email = $("#email");
	
	email.blur(validateEmail);
	email.keyup(validateEmail);

	form.submit(function() {
		if(validateEmail()) {
			return true;
		}
		else {
			return false;
		}
	});
	
	function validateEmail() {
		if(email.val().length == 0) {
			email.removeClass("input_border");
			email.addClass("error");
			return false;
		}
		else {
			email.removeClass("error");
			email.addClass("input_border");
			return true;
		}
	};
});