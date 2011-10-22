<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/newsource-form.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				if($('#modal').length > 0) {
				    $('#modal').fadeIn().css({ 'width': Number(450)}).prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
				    var popMargTop = ($('#modal').height() + 80) / 2;
				    var popMargLeft = ($('#modal').width() + 80) / 2;
				
				    $('#modal').css({
				        'margin-top' : -popMargTop,
				        'margin-left' : -popMargLeft
				    });
				
				    $('body').append('<div id="fade"></div>');
				    $('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn();
				
				    return false;
				}
			});
			
			$('a.close, #fade').live('click', function() {
				$('#fade , .popup_block').fadeOut( function() {
				    $('#fade, a.close').remove();
				});
				return false;
			});
		</script>
		
		<title>Cadastrar nova Referência</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>		
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="custom_form" class="width650" action="<c:url value='/rules/newsource'/>" method="post">
				<fieldset>
					<legend>Cadastrar nova Referência</legend>
					
					<div class="single_form_element">
						<label class="label" for="name">Tipo*</label>
						<br />
						<select id="selectType" name="newSource.type" class="input_border width250">
							<c:forEach var="type" items="${typesOfSources}"  >
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
					
					<div id="fieldTitle" class="single_form_element" style="display: none">
						<label class="label" for="title">Título*</label>
						<br />
						<input id="title" class="input_border width350" type="text" maxlength=200 name="newSource.title" />
						<br />
					</div>
					
					<div id="fieldURL" class="single_form_element" style="display: none">
						<label class="label" for="url">URL (disponível em)*</label>
						<br />
						<input id="url" class="input_border width350" type="text" maxlength=300 name="newSource.url" />
						<br />
					</div>
					
					<div id="fieldAuthors" class="single_form_element" style="display: none">
						<label class="label" for="authors">Autor(es)*</label>
						<br />
						<input id="authors" class="input_border width350" type="text" maxlength=300 name="newSource.authors" />
						<br />
					</div>

					<div id="fieldPublisher" class="single_form_element" style="display: none">
						<label class="label" for="publisher">Editora</label>
						<br />
						<input id="publisher" class="input_border width350" type="text" maxlength=100 name="newSource.publisher" />
						<br />
					</div>	
					
					<div id="fieldInstitution" class="single_form_element" style="display: none">
						<label class="label" for="institution">Instituição</label>
						<br />
						<input id="institution" class="input_border width350" type="text" maxlength=100 name="newSource.institution" />
						<br />
					</div>								
					
					<div id="fieldDate" class="single_form_element" style="display: none">
						<label id="dateAcademic" class="label" for="date">Data</label>
						<label id="dateBook" class="label" for="date" style="display: none">Data de publicação</label>
						<br />
						<input id="date" class="input_border width250" type="text" maxlength=40 name="newSource.date" />
						<br />
					</div>			
					
					<div id="fieldInfo" class="single_form_element">
						<label class="label" for="info">Descrição (mais informações)<span id="mandatoryInfo" style="display: none">*</span></label>
						<br />
						<textarea id="info" style="width: 600px; height: 100px !important" class="input_border" maxlength=400
							name="newSource.moreInformation"></textarea>
					</div>		
					<button type="button" class="button" onclick="javascript:history.go(-1);return false;">Voltar</button>					
					<input class="button" type="submit" value="Cadastrar">
				</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>