<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
				
		<title>Verificação avançada</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="advice_form" class="width800" action="<c:url value="/advice"/>" enctype="multipart/form-data" method="post">
					<fieldset>
						<legend>Verificação avançada</legend>
						<div class="single_form_element">
							<select id="selectCategory" name="newRule.category" class="input_border width250">
								<option value="0">Todas as regras cadastradas</option>
								<option value="1">Filtrar regras</option>
							</select>
						</div>
						<br />
						
						Selecione um arquivo .txt ou .pdf para análise:<br>
						<div class="single_form_element">
							<input id="file" type="file" name="file" size="30"><br>
						</div>
						<button type="button" class="button" onclick="javascript:history.go(-1);return false;">Voltar</button>
						<input class="button" type="submit" value="Verificar">
					</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>