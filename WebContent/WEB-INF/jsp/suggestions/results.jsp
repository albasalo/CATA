<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		<%@ include file="/css/style.css" %>
	</style>
	<title>Sugest&otilde;es de Corre&ccedil;&atilde;o</title>
</head>


<body>
	<div id="header">
		<div id="logo">
			<h1><a href="<c:url value="/"/>">NOME</a></h1>
			<p>Automa&ccedil;&atilde;o de corre&ccedil;&atilde;o de estilo de textos t&eacute;cnicos de computa&ccedil;&atilde;o</p>
		</div>
	</div>
	
	<div id="page">
		<div id="content">
			<div class="post">
				<h1 class="title">Sugest&otilde;es</h1>
				<div class="entry">
					<br>
					${output}<br>
					<ul>
					<c:forEach items="${suggestions}" var="suggestion">
					    <li> <b>Linha</b> ${suggestion.lineNum}:<br>
						<p>
						<b>Termo inadequado:</b> ${suggestion.suggestion.error}<br>
						<b>Sugestão:</b> ${suggestion.suggestion.suggestion}
						</p>
					    </li>    
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>

</html>