<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<title>Sistema de An&aacute;lise e Corre&ccedil;&atilde;o de Estilo de Textos T&eacute;cnicos de Computa&ccedil;&atilde;o</title>
	</head>

	<body>
		<div id="header">
			<div id="logo">
				<h1><a href="#">NOME</a></h1>
				<p>Automa&ccedil;&atilde;o de corre&ccedil;&atilde;o de estilo de textos t&eacute;cnicos de computa&ccedil;&atilde;o</p>
			</div>
		</div>
		
		<div id="page">
			<div id="content">
				<div class="post">
					<h1 class="title">P&aacute;gina inicial</h1>
					<div class="entry">
						<c:forEach var="error" items="${errors}">
    						${error.message}<br />
						</c:forEach>
						
						<form action="<c:url value="/analyzeText"/>" enctype="multipart/form-data" method="post">
							<p>
							Selecione um arquivo .txt para an&aacute;lise:<br>
							<input type="file" name="file" size="40"><br>
							</p>
							<input type="submit" value="Enviar">
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>