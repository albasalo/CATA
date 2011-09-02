<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/table.css'/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#rules').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
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
								<td class="center">${rule.user.name}</td>
								<td class="center"><a href="<c:url value='/rules/viewrule/${rule.ruleID}'/>"><img src="<c:url value='/css/images/plus-icon.png'/>"></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="spacer"></div>
				</div>
				
				<button class="button" onclick="window.location.href='<c:url value='/rules/newrule'/>'">Cadastrar nova regra</button>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>