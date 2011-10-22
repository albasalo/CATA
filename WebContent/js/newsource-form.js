$(document).ready(function() {
	$("[name='newSource.type']").change(createForm);
	
	function createForm() {
		if($("[name='newSource.type'] option:selected").val() == "ACADEMIC_PUBLISHING") {
			$("#fieldURL").hide();
			$("#fieldPublisher").hide();
			$("#dateBook").hide();
			$("#mandatoryInfo").hide();
			$("#fieldTitle").show();
			$("#fieldAuthors").show();
			$("#fieldInstitution").show();
			$("#fieldDate").show();
			$("#dateAcademic").show();
		}
		else if($("[name='newSource.type'] option:selected").val() == "BOOK" ||
				$("[name='newSource.type'] option:selected").val() == "HANDBOOK") {
			$("#fieldURL").hide();
			$("#fieldInstitution").hide();
			$("#dateAcademic").hide();
			$("#mandatoryInfo").hide();
			$("#fieldTitle").show();
			$("#fieldAuthors").show();
			$("#fieldPublisher").show();
			$("#fieldDate").show();
			$("#dateBook").show();
		}
		else if($("[name='newSource.type'] option:selected").val() == "INTERNET") {
			$("#fieldAuthors").hide();
			$("#fieldPublisher").hide();
			$("#fieldInstitution").hide();
			$("#dateAcademic").hide();
			$("#dateBook").hide();
			$("#fieldDate").hide();
			$("#mandatoryInfo").hide();
			$("#fieldTitle").show();
			$("#fieldURL").show();
		}
		else {
			$("#fieldTitle").hide();
			$("#fieldAuthors").hide();
			$("#fieldURL").hide();
			$("#fieldPublisher").hide();
			$("#fieldInstitution").hide();
			$("#dateAcademic").hide();
			$("#dateBook").hide();
			$("#fieldDate").hide();
			$("#mandatoryInfo").show();
		}
	}
	
	createForm();
	
	var form = $("#custom_form");
	var title = $("#title");
	var url = $("#url");
	var authors = $("#authors");
	var date = $("#date");
	var info = $("#info");
	
	title.blur(validateTitle);
	url.blur(validateURL);
	authors.blur(validateAuthors);
	info.blur(validateInfo);
	
	title.keyup(validateTitle);
	url.keyup(validateURL);
	authors.keyup(validateAuthors);
	info.keyup(validateInfo);
	
	form.submit(function() {
		if(validateTitle() & validateURL() & validateAuthors() & validateInfo()) {
			return true;
		}
		else {
			return false;
		}
	});
	
	function validateTitle() {
		if($("[name='newSource.type'] option:selected").val() != "OTHER") {
			if(title.val().length == 0) {
				title.removeClass("input_border");
				title.addClass("error");
				return false;
			}
			else {
				title.removeClass("error");
				title.addClass("input_border");
				return true;
			}
		}
		else {
			title.removeClass("error");
			title.addClass("input_border");
			return true;			
		}
	}
	
	function validateURL() {
		if($("[name='newSource.type'] option:selected").val() == "INTERNET") {
			if(url.val().length == 0) {
				url.removeClass("input_border");
				url.addClass("error");
				return false;
			}
			else {
				url.removeClass("error");
				url.addClass("input_border");
				return true;
			}
		}
		else {
			url.removeClass("error");
			url.addClass("input_border");
			return true;			
		}
	}
	
	function validateAuthors() {
		if($("[name='newSource.type'] option:selected").val() != "INTERNET" &
				$("[name='newSource.type'] option:selected").val() != "OTHER") {
			if(authors.val().length == 0) {
				authors.removeClass("input_border");
				authors.addClass("error");
				return false;
			}
			else {
				authors.removeClass("error");
				authors.addClass("input_border");
				return true;
			}
		}
		else {
			authors.removeClass("error");
			authors.addClass("input_border");
			return true;			
		}
	}
	
	function validateInfo() {
		if($("[name='newSource.type'] option:selected").val() == "OTHER") {
			if(info.val().length == 0) {
				info.removeClass("input_border");
				info.addClass("error");
				return false;
			}
			else {
				info.removeClass("error");
				info.addClass("input_border");
				return true;
			}
		}
		else {
			info.removeClass("error");
			info.addClass("input_border");
			return true;			
		}
	}
	
	$("[name='newSource.type']").change(removeErrors);
	
	function removeErrors() {
		title.removeClass("error");
		title.addClass("input_border");
		url.removeClass("error");
		url.addClass("input_border");
		authors.removeClass("error");
		authors.addClass("input_border");
		date.removeClass("error");
		date.addClass("input_border");
		info.removeClass("error");
		info.addClass("input_border");
	}
});