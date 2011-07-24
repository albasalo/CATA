<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/coin-slider-styles.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/coin-slider.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#coin-slider').coinslider({ width: 565, navigation: true, delay: 5000 });
		
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				if($('#modal').length > 0) {
				    $('#modal').fadeIn().css({ 'width': Number(500)}).prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
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
		<title>CATA: Collaborative Academic Text Advisor</title>
	</head>

	<body>
		<div id="header">
			<div id="header-content">
				<div id="logo">
					<a href="#" title='CATA'><b>C</b>ollaborative <b>A</b>cademic <b>T</b>ext <b>A</b>dvisor</a>
				</div>
				
				<div id="menu" class="nav_bar">
					<ul>
					<li><a href='#' class='selected' title='Início'>Início</a></li>
					<li><a href="<c:url value='/about'/>" title='Sobre'>Sobre</a></li>
					<li><a href="<c:url value='/advice'/>" title='Advice'>Advice</a></li>
					</ul>	
				</div>
			</div>
		</div>
		
		<%@ include file="../messages.jsp"%>
		
		<div id="page">
			<div id="content">
				<h1>CATA: Collaborative Academic Text Advisor</h1>
				Um verificador de estilo para textos acadêmicos de Computação.<br />
				<br />
				
				<div id='coin-slider'>
					<a href="<c:url value='/advice'/>">
						<img src="<c:url value='/css/images/txt-file.png'/>">
						<span>
							<b>Envie um arquivo .txt</b><br>
							Selecione um arquivo de texto sem formatação e envie para verificação.
						</span>
					</a>
					<a href="<c:url value='/advice'/>">
						<img src="<c:url value='/css/images/advice.png'/>">
						<span>
							<b>Alternativas aos problemas de estilo</b><br>
							CATA marca os problemas de estilo encontrados em seu texto e
							oferece sugestões para corrigi-los.
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
				
			</div>
		</div>
		
<%@ include file="../footer.jsp"%>