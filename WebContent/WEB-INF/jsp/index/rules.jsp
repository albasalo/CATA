<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />	
		<link href="<c:url value='/css/table.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript" src="<c:url value='js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#rules-menu").addClass('selected');
				
				$('#rules').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
			});
		</script>
		<script type="text/javascript">
			function showModal(ruleID) {
				var modal = "#rule" + ruleID;
				if($(modal).length > 0) {
				    $(modal).fadeIn().css({ 'width': Number(450)}).prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
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
		
		<title>Regras e sugestões de estilo</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<h1>Regras e sugestões de estilo</h1>
				<p>	
				Existem muitas maneiras de melhorar o estilo de um texto acadêmico: evitar repetição de termos,
				evitar construções linguísticas pouco comuns, entre outras.
				</p>
				<p>
				Por enquanto, no Sistema CATA, as sugestões de estilo referem-se ao uso inadequado de termos e
				expressões no texto, como uso de clichês, traduções incorretas e estrangeirismos.
				</p>
				<p>
				Confira abaixo todas as regras cadastradas atualmente no CATA:
				</p>
				<div class="grid">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="rules">
					<thead>
						<tr>
							<th>ID</th>
							<th>Tipo</th>
							<th>Categoria</th>
							<th>Erro</th>
							<th>Sugestão</th>
							<th>Cadastrada por</th>
							<th>Mais informações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${rules}" var="rule">
							<tr>
								<td><c:out value="${rule.ruleID}"/></td>
								<td class="center"><c:out value="${rule.type.typeDescription}"/></td>
								<td class="center"><c:out value="${rule.category.categoryDescription}"/></td>
								<c:choose>
									<c:when test="${rule.lemmaElement != null}">
										<td class="center"><c:out value="${rule.lemmaElement.pattern}"/></td>
										<td class="center"><c:out value="${rule.lemmaElement.suggestion}"/></td>
									</c:when>
									<c:otherwise>
										<td class="center"><c:out value="${rule.exactMatchingElement.pattern}"/></td>
										<td class="center"><c:out value="${rule.exactMatchingElement.suggestion}"/></td>
									</c:otherwise>
								</c:choose>
								<td class="center"><c:out value="${rule.user.name}"/></td>
								<td class="center">
									<img onclick="showModal(${rule.ruleID});" src="<c:url value='/css/images/plus-icon.png'/>">
									<div id="rule${rule.ruleID}" class="popup_block" style="display:none">
										<div style="text-align: left">
											<b>Tipo: </b><c:out value="${rule.type.typeDescription}"/><br />
											<b>Categoria: </b><c:out value="${rule.category.categoryDescription}" /><br />
											<b>Sugestões:</b><br />
											<c:if test="${rule.lemmaElement != null}">
												<div class="indent">
												<b>Lemas</b><br />
												<div class="indent">
												<b>Problema: </b><c:out value="${rule.lemmaElement.pattern}"/><br />
												<b>Sugestão: </b><c:out value="${rule.lemmaElement.suggestion}"/><br />
												<c:forEach items="${rule.lemmaElements}" var="lemmaElement">
													<b>Problema</b><c:out value="${lemmaElement.pattern}"/><br />
													<b>Sugestão</b><c:out value="${lemmaElement.suggestion}"/><br />
												</c:forEach>
												</div></div>
											</c:if>
											<c:if test="${rule.exactMatchingElement != null}">
												<div class="indent">
												<b>Padrões exatos</b><br />
												<div class="indent">
													<b>Problema: </b><c:out value="${rule.exactMatchingElement.pattern}"/><br />
													<b>Sugestão: </b><c:out value="${rule.exactMatchingElement.suggestion}"/><br />
												<c:forEach items="${rule.exactMatchingElements}" var="exactMatchingElement">
													<b>Problema</b><c:out value="${exactMatchingElement.pattern}"/><br />
													<b>Sugestão</b><c:out value="${exactMatchingElement.suggestion}"/><br />
												</c:forEach>
												</div></div>
											</c:if>
										<c:if test="${rule.explanation != null}">
												<b>Explicacao: </b> <c:out value="${rule.explanation}"/><br />
										</c:if>
										<b>Cadastrada por: </b><c:out value="${rule.user.name}"/><br />
										<b>Referência bibliográfica</b>
									</div>
								</div>
								</td>								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="spacer"></div>
				</div>
				
				<c:choose>
					<c:when test="${userSession.user != null}">
						<button class="button" onclick="window.location.href='<c:url value='/userrules/newrule'/>'">Cadastrar nova regra</button>
					</c:when>
					<c:otherwise>
						<br />
						<div class="small">
							<a href="<c:url value='/signup'/>">Registre-se</a> no Sistema CATA para poder cadastrar novas regras.
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>