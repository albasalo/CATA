$(document).ready(function(){
	var form = $("#changepassword_form");
	var current = $("#current");
	var pass1 = $("#pass1");
	var pass1Info = $("#pass1Info");
	var pass2 = $("#pass2");
	var pass2Info = $("#pass2Info");
	
	current.blur(validateCurrent);
	pass1.blur(validatePass1);
	pass2.blur(validatePass2);
	
	current.keyup(validateCurrent);
	pass1.keyup(validatePass1);
	pass2.keyup(validatePass2);

	form.submit(function() {
		if(validateCurrent() & validatePass1() & validatePass2()) {
			return true;
		}
		else {
			return false;
		}
	});
	
	function validateCurrent() {
		if(current.val().length < 6) {
			current.removeClass("input_border");
			current.addClass("error");
			return false;
		}
		else {
			current.removeClass("error");
			current.addClass("input_border");
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
			pass2Info.text("Confirme a nova senha");
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