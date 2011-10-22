$(document).ready(function(){
	var form = $("#custom_form");
	var name = $("#name");
	var nameInfo = $("#nameInfo");
	var email = $("#email");
	var emailInfo = $("#emailInfo");
	var pass1 = $("#pass1");
	var pass1Info = $("#pass1Info");
	var pass2 = $("#pass2");
	var pass2Info = $("#pass2Info");
	
	name.blur(validateName);
	email.blur(validateEmail);
	pass1.blur(validatePass1);
	pass2.blur(validatePass2);
	
	name.keyup(validateName);
	email.keyup(validateEmail);
	pass1.keyup(validatePass1);
	pass2.keyup(validatePass2);

	form.submit(function() {
		if(validateName() & validateEmail() & validatePass1() & validatePass2()) {
			return true;
		}
		else {
			return false;
		}
	});
	
	function validateName() {
		if(name.val().length == 0) {
			name.removeClass("input_border");
			name.addClass("error");
			nameInfo.addClass("description_error");
			nameInfo.text("Este campo é obrigatório");
			return false;
		}
		else {
			name.removeClass("error");
			name.addClass("input_border");
			nameInfo.removeClass("description_error");
			nameInfo.text("Como você será identificado pelos outros usuários");
			return true;
		}
	}
	function validateEmail() {
		var a = $("#email").val();
		var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
		if(filter.test(a)) {
			email.removeClass("error");
			email.addClass("input_border");
			emailInfo.removeClass("description_error");
			emailInfo.text("Uma confirmação será enviada para este endereço");
			return true;
		}
		else {
			email.removeClass("input_border");
			email.addClass("error");
			emailInfo.addClass("description_error");
			emailInfo.text("Este endereço não é válido");
			return false;
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