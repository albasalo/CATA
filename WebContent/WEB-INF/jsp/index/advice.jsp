<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<title>Advice</title>
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
					<li><a href='#' title='Sobre'>Sobre</a></li>
					<li><a href='#' class='selected' title='Advice'>Advice</a></li>
					</ul>	
				</div>
			</div>
		</div>
		
		<div id="page">
			<div id="content">
				<h1>Verifique o estilo de seus textos</h1>
				<c:forEach var="error" items="${errors}">
  						<font style="color: #780000">${error.message}</font>
				</c:forEach>
				
				<form action="<c:url value="/advice"/>" enctype="multipart/form-data" method="post">
					<p>
					Selecione um arquivo .txt para an&aacute;lise:<br>
					<input type="file" name="file" size="40"><br>
					</p>
					<input type="submit" value="Enviar">
				</form>
				
			</div>
		</div>
		
<%@ include file="../footer.jsp"%>