<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<title>Cadastrar nova Referência</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="custom_form" class="width710" action="<c:url value='/signup'/>" method="post">
				<fieldset>
					<legend>Cadastrar nova Referência</legend>
					
					<div class="single_form_element">
						<label class="label" for="name">Tipo*:</label>
						<br />
						<select id="selectType" name="source.Type" class="input_border">
							<c:forEach var="type" items="${typesOfSources}"  >						
									<option value="${type}" selected="${type}">${type.typeDescription}</option>										
							</c:forEach>
						</select>
					</div>
					
					<input class="button" type="submit" value="Cadastrar">	
				</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>