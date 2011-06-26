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
					<a href="#" title='CATA'><b>C</b>ollaborative <b>A</b>cademic <b>T</b>ext <b>A</b>dvisor</a>
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
				TODO.
				
			</div>
		</div>	

<%@ include file="../footer.jsp"%>