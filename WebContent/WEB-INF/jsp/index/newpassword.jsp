<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />	
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.5.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/messages-modal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/newpassword-form.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				showModal("#modal", '<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
			});
		</script>
		
		<title>Nova senha</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="newpassword_form" class="width510" action="<c:url value='/changepassword'/>" method="post">
				<fieldset>
					<legend>Nova senha</legend>
						<div class="single_form_element">
							<label class="label" for="email">E-mail</label>
							<br />
							<input id="email" class="input_border width285" type="text" maxlength=100 name="email" /><br />
						</div>
						<div class="single_form_element">
							<label class="label" for="pass1">Nova senha</label>
							<br />
							<input id="pass1" class="input_border width285" type="password" maxlength=32 name="password" /><br />
							<span id="pass1Info" class="description">No mínimo 6 caracteres</span>
						</div>
						<div class="single_form_element">
							<label class="label" for="pass2">Redigite a senha</label>
							<br />
							<input id="pass2" class="input_border width285" type="password" maxlength=32 name="password2" /><br />
							<span id="pass2Info" class="description">Confirme a senha</span>
						</div>
						<input type="hidden" name="newPasswordKey" value="${newPasswordKey}" /> 
					<input class="button" type="submit" value="Confirmar">
				</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>