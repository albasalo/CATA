<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<title>Sobre</title>
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
					<li><a href='#' class='selected' title='Sobre'>Sobre</a></li>
					<li><a href="<c:url value='/advice'/>" title='Advice'>Advice</a></li>
					</ul>	
				</div>
			</div>
		</div>
		
		<div id="page">
			<div id="content">
				<h1>Sobre</h1>
				<b>CATA (Collaborative Academic Text Advisor)</b> é o Trabalho de Formatura Supervisionado da aluna Ana Luiza
				Domingues Fernandez Basalo para o <a href="http://www.ime.usp.br" target="_blank"/>Instituto de Matemática e
				Estatística</a> da <a href="http://www.usp.br" target="_blank"/>Universidade de São Paulo (USP)</a>.<br>
				<p>
					<h3>Um verificador de estilo</h3>
					CATA tem por finalidade detectar problemas de estilo em textos
					acadêmicos de Computação, bem como sugerir possíveis correções para tais problemas. CATA analisa textos levando
					em conta aspectos linguísticos e estéticos, para, por exemplo, detectar ocorrências de traduções incorretas e
					estrangeirismos: como o uso de "testes unitários" como tradução para "unit tests", em vez da forma correta
					"testes de unidade"; ou ainda, "a função retorna um determinado valor" (tradução de "return"), quando mais
					elegante seria "a função devolve um determinado valor".
				</p>
				<p>
					<h3>Cadastre suas próprias regras e sugestões</h3>
					Um usuário autenticado no Sistema CATA poderá cadastrar novas regras e sugestões de estilo, que ficarão
					disponíveis a todos os demais usuários.
				</p>
				<p>
				<h3>CATA é Software Livre</h3>
					O código-fonte do Sistema CATA está hospedado no GitHub, acessível a partir deste
					<a href="http://www.github.com/albasalo/CATA" target="_blank"/>link</a>.
				</p>
			</div>
		</div>	

<%@ include file="../footer.jsp"%>