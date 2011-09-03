<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/table.css'/>" rel="stylesheet" type="text/css" />
		
		<title>Detalhes da referência</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<h2>Detalhes da referência</h2>
				<br />
				<div class="indentation grid width750">
					<table>
						<c:choose>
							<c:when test="${source.type == 'ACADEMIC_PUBLISHING'}">
									<tr>
										<td><b>Título:</b></td>
										<td><c:out value="${source.title}" /></td>
									</tr>
									<tr>
										<td><b>Autor(es):</b></td>
										<td><c:out value="${source.authors}" /></td>
									</tr>
									<c:if test="${source.institution != null}">
										<tr>
											<td><b>Instituição:</b></td>
											<td><c:out value="${source.institution}" /></td>
										</tr>
									</c:if>
									<c:if test="${source.date != null}">
										<tr>
											<td><b>Data:</b></td>
											<td><c:out value="${source.date}" /></td>
										</tr>
									</c:if>
							</c:when>
							<c:when test="${source.type == 'BOOK' || source.type == 'HANDBOOK'}">
									<tr>
										<td><b>Título:</b></td>
										<td><c:out value="${source.title}" /></td>
									</tr>
									<tr>
										<td><b>Autor(es):</b></td>
										<td><c:out value="${source.authors}" /></td>
									</tr>
									<c:if test="${source.publisher != null}">
										<tr>
											<td><b>Editora:</b></td>
											<td><c:out value="${source.publisher}" /></td>
										</tr>
									</c:if>
									<c:if test="${source.date != null}">
										<tr>
											<td><b>Data de publicação:&nbsp;</b></td>
											<td><c:out value="${source.date}" /></td>
										</tr>
									</c:if>
							</c:when>
							<c:when test="${source.type == 'INTERNET'}">
									<tr>
										<td><b>Título:</b></td>
										<td><c:out value="${source.title}" /></td>
									</tr>
									<tr>
										<td><b>URL (disponível em):&nbsp;</b></td>
										<td><a href="<c:out value="${source.url}" />"><c:out value="${source.url}"/></a></td>
									</tr>
									<tr>
										<td><b>Data de acesso:</b></td>
										<td><c:out value="${source.date}" /></td>
									</tr>
							</c:when>
						</c:choose>
						<c:if test="${source.moreInformation != null}">
							<tr>
								<td><b>Mais informações:&nbsp;</b></td>
								<td><c:out value="${source.moreInformation}" /></td>
							</tr>
						</c:if>
						<tr>
							<td><b>Cadastrada por:&nbsp;</b></td>
							<td><c:out value="${source.user.name}" /></td>
						</tr>
					</table>
				</div>
				<button class="button" onclick="window.location.href='<c:url value='/userrules/newrule#source'/>'">Voltar</button>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>