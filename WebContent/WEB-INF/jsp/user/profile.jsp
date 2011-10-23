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
		<script type="text/javascript" src="<c:url value='/js/profile.js'/>"></script>
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
		<script type="text/javascript">
			$(document).ready(function() {
				$('#rules').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
			});
		</script>
		<script type="text/javascript">
			function showModal(ruleID) {
				var modal = "#rule" + ruleID;
				if($(modal).length > 0) {
				    $(modal).fadeIn().css({ 'width': Number(550)}).prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
				    var popMargTop = ($(modal).height() + 80) / 2;
				    var popMargLeft = ($(modal).width() + 80) / 2;
				
				    $(modal).css({
				        'margin-top' : -popMargTop,
				        'margin-left' : -popMargLeft
				    });
				
				    $('body').append('<div id="fade"></div>');
				    $('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn();
				
				    return false;
				}
			};
			
			$('a.close, #fade').live('click', function() {
				$('#fade , .popup_block').fadeOut( function() {
				    $('#fade, a.close').remove();
				});
				return false;
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
								<th>Tipo</th>
								<th>Categoria</th>
								<th>Erro</th>
								<th>Sugestão</th>
								<th>Mais informações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${rules}" var="rule">
								<tr id="row${rule.ruleID}">
									<td><c:out value="${rule.ruleID}"/></td>
									<td class="center"><c:out value="${rule.type.typeDescription}"/></td>
									<td class="center"><c:out value="${rule.category.categoryDescription}"/></td>
									<c:choose>
										<c:when test="${!empty rule.lemmas}">
											<c:forEach items="${rule.lemmas}" var="lemma">
												<c:if test="${lemma.pair.defaultPair == true}">
													<td class="center"><c:out value="${lemma.pair.pattern}"/></td>
													<td class="center"><c:out value="${lemma.pair.suggestion}"/></td>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${rule.exactMatchings}" var="exactMatching">
												<c:if test="${exactMatching.pair.defaultPair == true}">
													<td class="center"><c:out value="${exactMatching.pair.pattern}"/></td>
													<td class="center"><c:out value="${exactMatching.pair.suggestion}"/></td>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									<td class="center">
										<img onclick="showModal(${rule.ruleID});" src="<c:url value='/css/images/plus-icon.png'/>">
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
														<tr><td><b>Padrão Incorreto</b></td><td><b>Sugestão</b></td></tr>
														<c:forEach items="${rule.lemmas}" var="lemma">
															<tr>
																<td><c:out value="${lemma.pair.pattern}" /></td>
																<td><c:out value="${lemma.pair.suggestion}" /></td>
															</tr>
														</c:forEach>
													</table>
												</div>
												</c:if>
												<c:if test = "${!empty rule.exactMatchings}">
												<b>Expressões exatas:</b><br />
												<div class="indentation">
													<table class="modal-table">
														<tr><td><b>Padrão Incorreto</b></td><td><b>Sugestão</b></td></tr>
														<c:forEach items="${rule.exactMatchings}" var="exactMatching">
															<tr>
																<td><c:out value="${exactMatching.pair.pattern}" /></td>
																<td><c:out value="${exactMatching.pair.suggestion}" /></td>
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
													<table>
													<c:if test = "${rule.source.type == 'ACADEMIC_PUBLISHING'}">
														<tr><td><c:out value="${rule.source.authors}"/>, <i><c:out value="${rule.source.title}"/></i><br />
														<c:out value="${rule.source.institution}"/>, <c:out value="${rule.source.date}"/><br />
														<c:out value="${rule.source.moreInformation}"/></td></tr>
													</c:if>
													<c:if test = "${rule.source.type == 'BOOK'}">
														<tr><c:out value="${rule.source.authors}"/>, <i><c:out value="${rule.source.title}"/></i></tr>
														<tr><c:out value="${rule.source.publisher}"/>, <c:out value="${rule.source.date}"/></tr>
														<tr><c:out value="${rule.source.moreInformation}"/></tr>
													</c:if>
													<c:if test = "${rule.source.type == 'HANDBOOK'}">
														<tr><c:out value="${rule.source.authors}"/>, <i><c:out value="${rule.source.title}"/></i></tr>
														<tr><c:out value="${rule.source.publisher}"/>, <c:out value="${rule.source.date}"/></tr>
														<tr><c:out value="${rule.source.moreInformation}"/></tr>
													</c:if>
													<c:if test = "${rule.source.type == 'INTERNET'}">
														<tr><td><a href="<c:out value="${rule.source.url}" />" target="_blank"><c:out value="${rule.source.title}"/></a></td></tr>
														<tr><td><c:out value="${rule.source.moreInformation}"/></td></tr>
													</c:if>
													<c:if test = "${rule.source.type == 'OTHER'}">
														<tr><c:out value="${rule.source.moreInformation}"/></tr>
													</c:if>
													</table>
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
					<form action="<c:url value='/rules/editrule'/>" method="post">
						<div id="inputRuleID"></div>
						<input class="single_form_element button" type="submit" value="Editar regra">
						<button class="button" onclick="window.location.href='<c:url value='/rules/newrule'/>';return false;">Cadastrar nova regra</button>
					</form>
					<br />
				</div>
				<button type="button" class="button" onclick="javascript:history.go(-1);return false;">Voltar</button>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>