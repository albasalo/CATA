<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/table.css'/>" rel="stylesheet" type="text/css" />
		
		<title>Detalhes da regra</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<h2>Detalhes da regra</h2>
				<br />
				<div class="indentation grid">
					<table>
						<tr>
							<td><b>ID:</b></td>
							<td><c:out value="${rule.ruleID}" /></td>
						</tr>
						<tr>
							<td><b>Tipo:</b></td>
							<td><c:out value="${rule.type.typeDescription}" /></td>
						</tr>
						<tr>
							<td><b>Categoria:&nbsp;</b></td>
							<td><c:out value="${rule.category.categoryDescription}" /></td>
						</tr>
						
						<c:if test="${rule.lemmaElement != null}">
							<tr>
								<td><b>Lemas</b></td>
								<td></td>
							</tr>
							<tr>
								<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Padrão incorreto:&nbsp;</b></td>
								<td><c:out value="${rule.lemmaElement.pattern}" /></td>
							</tr>
							<tr>
								<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Sugestão:</b></td>
								<td><c:out value="${rule.lemmaElement.suggestion}" /></td>
							</tr>
							<c:if test="${rule.lemmaElements != null}">
								<c:forEach items='${rule.lemmaElements}' var="lemma">
									<tr>
										<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Padrão incorreto:&nbsp;</b></td>
										<td><c:out value="${lemma.pattern}" /></td>
									</tr>
									<tr>
										<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Sugestão:</b></td>
										<td><c:out value="${lemma.suggestion}" /></td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
						
						<c:if test="${rule.exactMatchingElement != null}">
							<tr>
								<td><b>Expressões exatas</b></td>
							</tr>
							<tr>
								<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Padrão incorreto:&nbsp;</b></td>
								<td><c:out value="${rule.exactMatchingElement.pattern}" /></td>
							</tr>
							<tr>
								<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Sugestão:&nbsp;</b></td>
								<td><c:out value="${rule.exactMatchingElement.suggestion}" /></td>
							</tr>
							<c:if test="${rule.exactMatchingElements != null}">
								<c:forEach items='${rule.exactMatchingElements}' var="matching">
									<tr>
										<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Padrão incorreto:&nbsp;</b></td>
										<td><c:out value="${matching.pattern}" /></td>
									</tr>
									<tr>
										<td><b>&nbsp;&nbsp;&nbsp;&nbsp;Sugestão:</b></td>
										<td><c:out value="${matching.suggestion}" /></td>
									</tr>
								</c:forEach>
							</c:if>
						</c:if>
						
						<c:if test="${rule.explanation != null}">
							<tr>
								<td><b>Explicação:</b></td>
								<td><c:out value="${rule.explanation}" /></td>
							</tr>
						</c:if>
						
						<tr>
							<td><b>Cadastrada por:&nbsp;</b></td>
							<td><c:out value="${rule.user.name}" /></td>
						</tr>
						
					</table>
				</div>
				
				<button class="button" onclick="window.location.href='<c:url value='/rules'/>'">Voltar</button>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>