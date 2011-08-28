<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />	
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				var counter = -1;
				
				$("#plus").click(function() {
					if(counter < 200) {
						counter = counter + 1;
						$("#exactMatchingsDiv").append('<span id="exactMatching' + counter + '">' +
								'<br /><label class="label">Padrão incorreto</label><br />' +
								'<input class="input_border width450" type="text" maxlength=200 name="exactMatchings[' + counter + '].pattern"/><br/>' +
								'<label class="label">Sugestão</label><br />' +
								'<input class="input_border width450" type="text" maxlength=200 name="exactMatchings[' + counter + '].suggestion"/><br/>' +
								'</span>');
					}
				});
				
				$('#minus').click(function() {
					if(counter != -1) {
						var exactMatching = "#exactMatching" + counter;
						$(exactMatching).remove();
						counter = counter - 1;
					}
				});
			});
		</script>
		<title>Cadastrar nova Regra de estilo</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="custom_form" class="width650" action="<c:url value='/rules/newrule'/>" method="post">
				<fieldset>
					<legend>Cadastrar nova Regra de estilo</legend>
					
					<div class="single_form_element">
						<label class="label" for="name">Categoria *</label>
						<br />
						<select id="selectCategory" name="newRule.category" class="input_border width250">
							<c:forEach var="category" items="${ruleCategories}"  >
									<c:choose>
										<c:when test="${category == selectedCategory}">						
											<option value="${category}" selected="${category}">${category.categoryDescription}</option>
										</c:when>
										<c:otherwise>
											<option value="${category}">${category.categoryDescription}</option>
										</c:otherwise>
									</c:choose>										
							</c:forEach>
						</select>
					</div>
					
					<div class="single_form_element">
						<label class="label" for="name">Tipo *</label>
						<br />
						<select id="selectType" name="newRule.type" class="input_border width250">
							<c:forEach var="type" items="${typesOfRules}"  >
									<c:choose>
										<c:when test="${type == selectedType}">						
											<option value="${type}" selected="${type}">${type.typeDescription}</option>
										</c:when>
										<c:otherwise>
											<option value="${type}">${type.typeDescription}</option>
										</c:otherwise>
									</c:choose>										
							</c:forEach>
						</select>
					</div>
					<br />
					<div class="single_form_element">
						<b>Lema:</b><br />
						<div class="indent">
							<label class="label" for="lemmaPattern">Padrão incorreto</label>
							<br />
							<input id="lemmaPattern" class="input_border width450" type="text" maxlength=200 name="newRule.lemmaElement.pattern" />
							<br />
							<label class="label" for="lemmaSuggestion">Sugestão</label>
							<br />
							<input id="lemmaSuggestion" class="input_border width450" type="text" maxlength=200 name="newRule.lemmaElement.suggestion" />
							<br />
						</div>
					</div>
					<br />
					<b>Expressões exatas:</b><br />
					<div class="single_form_element">
						<div id="exactMatchingsDiv" class="indent">
							<label class="label" for="exactPattern">Padrão incorreto</label>
							<br />
							<input id="exactPattern" class="input_border width450" type="text" maxlength=200 name="newRule.exactMatchingElement.pattern"/>
							<br />
							<label class="label" for="exactSuggestion">Sugestão</label>
							<br />
							<input id="exactSuggestion" class="input_border width450" type="text" maxlength=200 name="newRule.exactMatchingElement.suggestion"/>
							<br />
						</div>
					</div>
					<div class="single_form_element">
						<div class="indent">
						<span id="plus" class="change_form_element">[+]</span>
						<span id="minus" class="change_form_element">[-]</span>
						</div>
					</div>
					<br />
					<div class="single_form_element">
						<label class="label" for="explanation">Explicação</label>
						<br />
						<textarea id="explanation" style="width: 600px; height: 100px !important" class="input_border" maxlength=400
							name="newRule.explanation"></textarea>
						<br />
					</div>
					
					<input class="button" type="submit" value="Cadastrar">
				</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>