<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/coin-slider-styles.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/index.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/coin-slider.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/login-form.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				$("#index-menu").addClass('selected');
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#coin-slider').coinslider({ width: 950, navigation: true, delay: 5000 });
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				if($('#modal').length > 0) {
				    $('#modal').fadeIn().css({ 'width': Number(500)})
				    	.prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
				    var popMargTop = ($('#modal').height() + 80) / 2;
				    var popMargLeft = ($('#modal').width() + 80) / 2;
				
				    $('#modal').css({
				        'margin-top' : -popMargTop,
				        'margin-left' : -popMargLeft
				    });
				
				    $('body').append('<div id="fade"></div>');
				    $('#fade').css({ 'filter': 'alpha(opacity=80)'}).fadeIn(); 
				
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
		<title>CATA: Collaborative Academic Text Advisor</title>
	</head>

	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>
		
		<div id="page">
			<div id="content">
				<h1>CATA: Collaborative Academic Text Advisor</h1>
				Um verificador de estilo para textos acadêmicos de Computação.<br />
				<br />
				
				<div id='coin-slider'>
					<a href="<c:url value='/advice'/>">
						<img src="<c:url value='/css/images/test.png'/>">
						<span>
							<b>Envie um arquivo .txt</b><br>
							Selecione um arquivo de texto sem formatação e envie para verificação.
						</span>
					</a>
					<a href="<c:url value='/advice'/>">
						<img src="<c:url value='/css/images/test.png'/>">
						<span>
							<b>Alternativas aos problemas de estilo</b><br>
							CATA marca os problemas de estilo encontrados em seu texto e
							oferece sugestões para corrigi-los.
						</span>
					</a>
					<a href="http://www.github.com/albasalo/CATA" target="_blank">
						<img src="<c:url value='/css/images/test.png'/>">
						<span>
							<b>Cadastre suas próprias sugestões de estilo</b><br>
							Cadastrando-se no Sistema CATA você poderá inserir e modificar suas
							próprias regras e sugestões de estilo.
						</span>
					</a>	
					<a href="http://www.github.com/albasalo/CATA" target="_blank">
						<img src="<c:url value='/css/images/test.png'/>">
						<span>
							<b>CATA é software livre!</b><br>
							O código-fonte do Sistema CATA está hospedado no GitHub.
						</span>
					</a>					
				</div>
				
				<div id="login">
					<form id="login_form" action="<c:url value='/login'/>" method="post">
						<fieldset>
						<legend>Entrar</legend>
							<div class="single_form_element">
								<label class="label" for="email">E-mail:</label>
								<br />
								<input id="email" class="input_border" type="text" maxlength=100 name="user.email"/>
							</div>
							<div class="single_form_element">
								<label class="label" for="pass">Senha:</label>
								<br />
								<input id="pass" class="input_border" type="password" maxlength=100 name="user.password"/>
								<div class="small align-right"><a href=#>Esqueci a senha</a></div>
							</div>
							<center>
								<input class="button" type="submit" value="Entrar">
								<div class="small">Novo por aqui? <a href="<c:url value='/signup'/>">Cadastre-se</a></div>
							</center>
						</fieldset>
					</form>						
				</div>
				
				<div id="advice">
					<h2>Verifique o estilo de seus textos</h2>
					<form action="<c:url value="/advice"/>" enctype="multipart/form-data" method="post">
						Selecione um arquivo .txt para análise:<br>
						<div class="single_form_element">
							<input type="file" name="file" size="30"><br>
						</div>
						<center>
							<input class="button" type="submit" value="Enviar">
							<div class="small">
								Por padrão, apenas algumas regras cadastradas no Sistema serão aplicadas ao seu texto. Para
								configurar a análise de seus arquivos, use a opção<br>
								<a href=#>Verificação Avançada</a>.
							</div>
						</center>
					</form>
				</div>
				
				<div class="spacer"></div>
				
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>