<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/jquery-ui-1.8.16.custom.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/jquery-ui-multiselect-widget.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/jquery.multiselect.filter.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.5.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.8.16.custom.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/advanced-form.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.multiselect.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.multiselect.br.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.multiselect.filter.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.multiselect.filter.br.js'/>"></script>
		
		<title>Verificação avançada</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="advice_form" class="width650" action="<c:url value="/advanced"/>" enctype="multipart/form-data" method="post">
					<fieldset>
						<legend>Verificação avançada</legend>
						<div class="single_form_element">
							<select id="selectCategory" name="filter" class="input_border width250">
								<option value="0">Todas as regras cadastradas</option>
								<option value="1">Escolher regras</option>
							</select>
						</div>
						
						<div id="filterForm" class="bordered-element" style="display:none">
							<div class="indentation" style="margin-top: 18px">
							<div class="single_form_element">
								<b>Escolher regras por</b>&nbsp;
								<select id="selectFilter" name="selectedFilter" class="input_border width250">
									<option value="0">Usuário</option>
									<option value="1">Referência Bibliográfica</option>
								</select>
							</div>
							
							<div id="selectUserDiv" style="display:none">
								<br />
								<select id="selectUser" class="width500" name="selectedUsers" multiple="multiple">
									<c:forEach var="user" items="${users}">
										<option value="${user.userID}">${user.name}</option>									
									</c:forEach>
								</select>
								<br /><br />
							</div>
							
							<div id="selectSourceDiv" style="display:none">
								<br />
								<select id="selectSource" class="width500" name="selectedSources" multiple="multiple">
									<c:forEach var="source" items="${sources}">
										<option value="${source.sourceID}">${source.title}</option>									
									</c:forEach>
								</select>
								<br /><br />
							</div>
							
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