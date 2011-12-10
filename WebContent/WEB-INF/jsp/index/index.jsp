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
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.5.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/coin-slider.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/messages-modal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/advice-form.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/checkbox.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/login-form.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#index-menu").addClass('selected');			
				$('#coin-slider').coinslider({ width: 950, navigation: true, delay: 5000 });
				showModal("#modal", '<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
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
					<a href="<c:url value='/#checkYourTexts'/>">
						<img src="<c:url value='/css/images/files.png'/>">
						<span>
							<b>Envie um arquivo .txt ou .pdf</b><br>
							Selecione um arquivo de texto sem formatação ou um PDF e envie para verificação.
						</span>
					</a>
					<a href="<c:url value='/#checkYourTexts'/>">
						<img src="<c:url value='/css/images/advice.png'/>">
						<span>
							<b>Alternativas aos problemas de estilo</b><br>
							CATA marca os problemas de estilo encontrados em seu texto e
							oferece sugestões para corrigi-los.
						</span>
					</a>
					<a href="<c:url value='/signup'/>">
						<img src="<c:url value='/css/images/newrule.png'/>">
						<span>
							<b>Cadastre suas próprias sugestões de estilo</b><br>
							Cadastrando-se no Sistema CATA você poderá inserir e modificar suas
							próprias regras e sugestões de estilo.
						</span>
					</a>	
					<a href="http://www.github.com/albasalo/CATA" target="_blank">
						<img src="<c:url value='/css/images/free-github.png'/>">
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
								<label class="label" for="email">E-mail</label>
								<br />
								<input id="email" class="input_border width285" type="text" maxlength=100 name="user.email"/>
							</div>
							<div class="single_form_element">
								<label class="label" for="pass">Senha</label>
								<br />
								<input id="pass" class="input_border width285" type="password" maxlength=100 name="user.password"/>
								<div class="small align-right"><a href="<c:url value='/recover'/>">Esqueci a senha</a></div>
							</div>
							<center>
								<input class="button" type="submit" value="Entrar">
								<div class="small">Novo por aqui? <a href="<c:url value='/signup'/>">Cadastre-se</a></div>
							</center>
						</fieldset>
					</form>						
				</div>
				
				<div id="advice">
					<a name="checkYourTexts"></a>
					<center>
						<h2>Verifique o estilo de seus textos</h2>
					</center>
					<form id="advice_form" action="<c:url value="/advice"/>" enctype="multipart/form-data" method="post">
						Selecione um arquivo .txt ou .pdf para análise:<br>
						<div class="single_form_element">
							<input id="file" type="file" name="file" size="30"><br />
							<span class="small" style="position: relative">
								<input id="pt" name="language" value="0" type="checkbox">Português <input id="en" name="language" value="1" type="checkbox">Inglês<br />
							</span>
						</div>
						<center>
							<input class="button" style="margin-top: 0px;" type="submit" value="Enviar">
							<div class="small">
								Por padrão, apenas algumas regras cadastradas no Sistema serão aplicadas ao seu texto. Para
								configurar a análise de seus arquivos, use a opção<br>
								<a href="<c:url value="/advanced"/>">Verificação Avançada</a>.
							</div>
						</center>
					</form>
				</div>
				
				<div class="spacer"></div>
				
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>