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
				
				    $('body').append('<div id="fade"></div>'); //Add the fade layer to bottom of the body tag.
				    $('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn(); //Fade in the fade layer - .css({'filter' : 'alpha(opacity=80)'}) is used to fix the IE Bug on fading transparencies 
				
				    return false;
				}
			});
			
			$('a.close, #fade').live('click', function() { //When clicking on the close or fade layer...
				$('#fade , .popup_block').fadeOut(function() {
				    $('#fade, a.close').remove();  //fade them both out
				});
				return false;
			});
		</script>
		<title>Cadastre-se</title>		
	</head>
	<body>	
		<div id="header">
			<div id="header-content">
				<div id="logo">
					<a href="<c:url value='/'/>" title='CATA'><b>C</b>ollaborative <b>A</b>cademic <b>T</b>ext <b>A</b>dvisor</a>
				</div>
				
				<div id="menu" class="nav_bar">
					<ul>
						<li><a href="<c:url value='/'/>" title='Início'>Início</a></li>
						<li><a href="<c:url value='/about'/>" title='Sobre'>Sobre</a></li>
						<li><a href="<c:url value='/advice'/>" title='Advice'>Advice</a></li>
					</ul>	
				</div>
			</div>
		</div>
		
		<%@ include file="../messages.jsp"%>
		
		<div id="page">
			<div id="content">			
				<center>
				<form id="custom_form" class="width500" action="<c:url value='/signup'/>" method="post">
					<h2>Cadastre-se</h2>
					<table>
						<tr>
							<td class="align-top">
								<div class="single_form_element">
									<label class="label width150" for="name">Nome* :</label>
								</div>
							</td>
							<td>
								<div class="single_form_element">
									<input id="name" class="input_border width300" type="text"
										maxlength=100 name="newUser.name" value="${newUser.name}"/>
									<span id="nameInfo" class="description">Você poderá mudar este campo depois</span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="align-top">
								<div class="single_form_element">
									<label class="label width150" for="email">E-mail* :</label>
								</div>
							</td>
							<td>
								<div class="single_form_element">
									<input id="email" class="input_border width300" type="text"
										maxlength=100 name="newUser.email" value="${newUser.email}"/>
									<span id="emailInfo" class="description">Uma confirmação será enviada para este endereço</span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="align-top">
								<div class="single_form_element">
									<label class="label width150" for="pass1">Senha* :</label>
								</div>
							</td>
							<td>
								<div class="single_form_element">
									<input id="pass1" class="input_border width300 right" type="password"
										maxlength=32 name="newUser.password" value="${newUser.password}"/>
									<span id="pass1Info" class="description">No mínimo 6 caracteres</span>
								</div>
							</td>
						</tr>
						<tr>
							<td class="align-top">
								<div class="single_form_element">
									<label class="label width150" for="pass2">Redigite a senha* :</label>
								</div>
							</td>
							<td>
								<div class="single_form_element">
									<input id="pass2" class="input_border width300" type="password"
										maxlength=32 name="password" value="${password}"/>
									<span id="pass2Info" class="description">Confirme sua senha</span>
								</div>
							</td>
						</tr>
					</table>
					<br />
					<input class="button" type="submit" value="Criar minha conta">		
				</form>		
				</center>
			</div>
		</div>
		
<%@ include file="../footer.jsp"%>