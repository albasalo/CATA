$(document).ready(function(){
	var form = $("#newpassword_form");
	var email = $("#email");
	var pass1 = $("#pass1");
	var pass1Info = $("#pass1Info");
	var pass2 = $("#pass2");
	var pass2Info = $("#pass2Info");
	
	email.blur(validateEmail);
	pass1.blur(validatePass1);
	pass2.blur(validatePass2);
	
	email.keyup(validateEmail);
	pass1.keyup(validatePass1);
	pass2.keyup(validatePass2);

	form.submit(function() {
		if(validateEmail() & validatePass1() & validatePass2()) {
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
	
	function validatePass1() {
		if(pass1.val().length < 6) {
			pass1.removeClass("input_border");
			pass1.addClass("error");
			pass1Info.addClass("description_error");
			pass1Info.text("No mínimo 6 caracteres");
			return false;
		}
		else {			
			pass1.removeClass("error");
			pass1.addClass("input_border");
			pass1Info.removeClass("description_error");
			pass1Info.text("");
			validatePass2();
			return true;
		}
	}
	
	function validatePass2() {
		if(pass2.val().length == 0) {
			pass2.removeClass("input_border");
			pass2.addClass("error");
			pass2Info.addClass("description_error");
			pass2Info.text("Confirme sua senha");
			return false;				
		}
		else if(pass1.val() != pass2.val()) {
			pass2.removeClass("input_border");
			pass2.addClass("error");
			pass2Info.addClass("description_error");
			pass2Info.text("As senhas não são iguais");
			return false;
		}
		else {
			pass2.removeClass("error");
			pass2.addClass("input_border");
			pass2Info.removeClass("description_error");
			pass2Info.text("");
			return true;
		}
	}
});