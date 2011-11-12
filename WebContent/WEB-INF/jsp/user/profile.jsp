<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />	
		<link href="<c:url value='/css/modal-table.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/table.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/messages-modal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/custom-modal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/pagination.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/profile.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				showModal("#modal", '<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
			});
		</script>
		<script type="text/javascript">
			function showRuleModal(ruleID) {
				var modal = "#rule" + ruleID;
				showCustomModal(modal, '<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
			};
		</script>
		<script type="text/javascript">
			function showEditModal() {
				showModal("#edit-modal", '<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
			};			
		</script>
		<script type="text/javascript">
			function showDeleteModal() {
				showModal("#delete-modal", '<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
			};			
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#rules').dataTable({
					"aaSorting": [[ 0, "asc" ]],
					"sPaginationType": "four_button"
				});
			});
		</script>
		
		<title>Perfil</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<h1><c:out value="${userSession.user.name}"/></h1>
				<h2>(<c:out value="${userSession.user.email}"/>)</h2>
				<div class="indentation">
					<button type="button" class="button" onclick="window.location.href='<c:url value='/user/changepassword'/>'">Alterar senha</button><br />
					<b>Regras cadastradas por você</b><br />
					<div class="grid">
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="rules">
						<thead>
							<tr>
								<th>ID</th>
								<th>Idioma</th>
								<th>Tipo</th>
								<th>Categoria</th>
								<th>Erro</th>
								<th>Sugestões</th>
								<th>Mais</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${rules}" var="rule">
								<tr id="row${rule.ruleID}">
									<td><c:out value="${rule.ruleID}"/></td>
									<td class="center"><c:out value="${rule.language.languageDescription}"/></td>
									<td class="center"><c:out value="${rule.type.typeDescription}"/></td>
									<td class="center"><c:out value="${rule.category.categoryDescription}"/></td>
									<c:choose>
										<c:when test="${!empty rule.lemmas}">
											<c:forEach items="${rule.lemmas}" var="lemma">
												<c:if test="${lemma.pair.defaultPair == true}">
													<td class="center"><c:out value="${lemma.pair.pattern}"/></td>
													<td class="center"><c:out value="${lemma.pair.suggestions}"/></td>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${rule.exactMatchings}" var="exactMatching">
												<c:if test="${exactMatching.pair.defaultPair == true}">
													<td class="center"><c:out value="${exactMatching.pair.pattern}"/></td>
													<td class="center"><c:out value="${exactMatching.pair.suggestions}"/></td>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<td class="center">
										<img onclick="showRuleModal(${rule.ruleID});" src="<c:url value='/css/images/plus-icon.png'/>">
										<div id="rule${rule.ruleID}" class="popup_block" style="display:none">
											<div class="popup_content" style="text-align: left">
												<b>Mais informações sobre a Regra de Estilo</b><br />
												<br />
												<b><c:out value="${rule.category.categoryDescription}" />:</b> <c:out value="${rule.type.typeDescription}" />
												<br />
												<c:if test = "${!empty rule.lemmas}">
												<b>Lemas:</b><br />
												<div class="indentation">
													<table class="modal-table">
														<tr><td><b>Padrão Incorreto</b></td><td><b>Sugestões</b></td></tr>
														<c:forEach items="${rule.lemmas}" var="lemma">
															<tr>
																<td><c:out value="${lemma.pair.pattern}" /></td>
																<td><c:out value="${lemma.pair.suggestions}" /></td>
															</tr>
														</c:forEach>
													</table>
												</div>
												</c:if>
												<c:if test = "${!empty rule.exactMatchings}">
												<b>Expressões exatas:</b><br />
												<div class="indentation">
													<table class="modal-table">
														<tr><td><b>Padrão Incorreto</b></td><td><b>Sugestões</b></td></tr>
														<c:forEach items="${rule.exactMatchings}" var="exactMatching">
															<tr>
																<td><c:out value="${exactMatching.pair.pattern}" /></td>
																<td><c:out value="${exactMatching.pair.suggestions}" /></td>
															</tr>
														</c:forEach>
													</table>
												</div>
												</c:if>
												<c:if test = "${rule.explanation != null}">
													<b>Explicação:</b><br />
													<div class="indentation">
														<table>
															<tr><td><c:out value="${rule.explanation}"/></td></tr>
														</table>
													</div>
												</c:if>
												<b>Referência Bibliográfica:</b><br />
												<div class="indentation">
													<%@ include file="../shared/table-sources.jsp"%>
												</div>
											</div>
										</div>
									</td>								
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="spacer"></div>
					</div>
					<form action="<c:url value='/rules/editrule'/>" method="post" id="editrule-form">
						<div id="inputRuleID" style="display:none">
							<input id="selectedRuleID" name="ruleToBeUpdated.ruleID" value="0">
						</div>
						<input class="single_form_element button" type="submit" value="Editar regra">
						<div id="edit-modal" class="popup_block" style="display:none">
							<div class="error-messages">
								<h2>Erro!</h2>
								Selecione uma Regra para editar.
							</div>
						</div>
						<button id="deleteRule" class="button">Remover regra</button>
						<button class="button" onclick="window.location.href='<c:url value='/rules/newrule'/>';return false;">Cadastrar nova regra</button>
					</form>
					<form action="<c:url value='/rules/deleterule'/>" method="post" id="deleterule-form" style="display:none"></form>
					<div id="delete-modal" class="popup_block" style="display:none">
							<div class="error-messages">
								<h2>Erro!</h2>
								Selecione uma Regra para remover.
							</div>
						</div>
					<br />
				</div>
				<button type="button" class="button" onclick="javascript:history.go(-1);return false;">Voltar</button>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>