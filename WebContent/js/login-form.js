$(document).ready(function(){
	var form = $("#login_form");
	var email = $("#email");
	var pass = $("#pass");
	
	email.blur(validateEmail);
	pass.blur(validatePass);
	
	email.keyup(validateEmail);
	pass.keyup(validatePass);

	form.submit(function() {
		if(validateEmail() & validatePass()) {
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
	}
	function validatePass() {
		if(pass.val().length < 6) {
			pass.removeClass("input_border");
			pass.addClass("error");
			return false;
		}
		else {
			pass.removeClass("error");
			pass.addClass("input_border");
			return true;
		}
	}
});