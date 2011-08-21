$(document).ready(function() {
	var form = $("#custom_form");
	var title = $("#title");
	var url = $("#url");
	var authors = $("#authors");
	var date = $("#date");
	var info = $("#info");
	
	title.blur(validateTitle);
	url.blur(validateURL);
	authors.blur(validateAuthors);
	date.blur(validateDate);
	info.blur(validateInfo);
	
	title.keyup(validateTitle);
	url.keyup(validateURL);
	authors.keyup(validateAuthors);
	date.keyup(validateDate);
	info.keyup(validateInfo);
	
	form.submit(function() {
		if(validateTitle() & validateURL() & validateAuthors() & validateDate() & validateInfo()) {
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
	
	function validateDate() {
		if($("[name='newSource.type'] option:selected").val() == "INTERNET") {
			if(date.val().length == 0) {
				date.removeClass("input_border");
				date.addClass("error");
				return false;
			}
			else {
				date.removeClass("error");
				date.addClass("input_border");
				return true;
			}
		}
		else {
			date.removeClass("error");
			date.addClass("input_border");
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