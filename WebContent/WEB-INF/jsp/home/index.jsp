<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/home.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/advice-form.js'/>"></script>
		
		<script type="text/javascript">
			$(document).ready(function () {
				$("#index-menu").addClass('selected');
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
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<h1>CATA: Collaborative Academic Text Advisor</h1>
				Um verificador de estilo para textos acadêmicos de Computação.<br />
				<br />
				
				<div id="advice">
					<h2>Verifique o estilo de seus textos</h2>
					<form id="advice_form" action="<c:url value="/advice"/>" enctype="multipart/form-data" method="post">
						<center>
							Selecione um arquivo .txt para análise:<br>
							<div class="single_form_element">
								<input id="file" type="file" name="file" size="30"><br>
							</div>
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