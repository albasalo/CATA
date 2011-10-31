<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/advanced-form.js'/>"></script>
		
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
							<select id="selectCategory" name="filter" class="input_border width250">
								<option value="0">Todas as regras cadastradas</option>
								<option value="1">Filtrar regras</option>
							</select>
						</div>
						
						<div id="filterForm" class="indentation" style="display:none">
							<br />
							<input type="checkbox" name="regrasDefault" value="1"> <b>Incluir as regras padrão do Sistema CATA</b>
							<br /><br />
							
							<div class="single_form_element">
								<label class="label" for="name">Usar regras dos seguintes usuários:</label>
								<br />
								<select id="selectUser" class="input_border width250" name="selectedUsers">
									<c:forEach var="user" items="${users}"  >
										<option value="${user.userID}">${user.name}</option>									
									</c:forEach>
								</select>
							</div>
							
							<div class="single_form_element">
								<label class="label" for="name">Usar regras das seguintes referências bibbliográficas:</label>
								<br />
								<select id="selectSource" name="selectedSources" class="input_border width250">
									<c:forEach var="source" items="${sources}"  >
										<option value="${source.sourceID}">${source.title}</option>									
									</c:forEach>
								</select>
							</div>
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