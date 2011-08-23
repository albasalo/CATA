<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="<c:url value='js/jquery-1.4.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='js/signup-form.js'/>"></script>
		<script type="text/javascript" src="<c:url value='js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				if($('#modal').length > 0) {
				    $('#modal').fadeIn().css({ 'width': Number(450)}).prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
				    var popMargTop = ($('#modal').height() + 80) / 2;
				    var popMargLeft = ($('#modal').width() + 80) / 2;
				
				    $('#modal').css({
				        'margin-top' : -popMargTop,
				        'margin-left' : -popMargLeft
				    });
				
				    $('body').append('<div id="fade"></div>');
				    $('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn();
				
				    return false;
				}
			});
			
			$('a.close, #fade').live('click', function() {
				$('#fade , .popup_block').fadeOut( function() {
				    $('#fade, a.close').remove();
				});
				return false;
			});
		</script>
		<title>Cadastre-se</title>		
	</head>
	<body>	
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="custom_form" class="width510" action="<c:url value='/signup'/>" method="post">
				<fieldset>
					<legend>Cadastre-se</legend>
						<div class="single_form_element">
							<label class="label" for="name">Nome*</label>
							<br />
							<input id="name" class="input_border width285" type="text" maxlength=100 name="newUser.name" /><br />
							<span id="nameInfo" class="description">Você poderá mudar este campo depois</span>
						</div>
						<div class="single_form_element">
							<label class="label" for="email">E-mail*</label>
							<br />
							<input id="email" class="input_border width285" type="text" maxlength=100 name="newUser.email" /><br />
							<span id="emailInfo" class="description">Uma confirmação será enviada para este endereço</span>
						</div>
						<div class="single_form_element">
							<label class="label" for="pass1">Senha*</label>
							<br />
							<input id="pass1" class="input_border width285" type="password" maxlength=32 name="newUser.password" /><br />
							<span id="pass1Info" class="description">No mínimo 6 caracteres</span>
						</div>
						<div class="single_form_element">
							<label class="label" for="pass2">Redigite a senha*</label>
							<br />
							<input id="pass2" class="input_border width285" type="password" maxlength=32 name="password" /><br />
							<span id="pass2Info" class="description">Confirme sua senha</span>
						</div>
					<input class="button" type="submit" value="Criar minha conta">	
				</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>