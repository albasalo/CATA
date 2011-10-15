<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/advice.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='js/jquery-1.3.1.min.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='js/popup.js'/>"></script>
		
		<title>Sugestões de Estilo</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>	
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<h1>Sugestões</h1>
				<h3>${output}</h3><br>
				<c:if test = "${numOfMistakes != 0}">
					<div id="text">
						<b>Arquivo: <c:out value="${fileName}"/></b>
						<br /><br />
						<c:forEach items="${text}" var="checkedLine">
							<c:forEach items="${checkedLine}" var="checkedSegment">
								<c:choose>
								<c:when test="${checkedSegment.mistakes != null}">
									<span class="popupConf">
										<span class="highlightedText">
											<font style="color:#950000">
												<b><c:out value="${checkedSegment.segment}"/></b>
											</font>
										</span>
										<div class="popup">
											<div class="popupTop"></div>
											<div class="popupMid">
											<span class="popupContent">
											<table>
												<c:forEach items="${checkedSegment.mistakes}" var="mistake">
												<tr>
													<td><b>Problema:</b></td>
													<td>${mistake.brokenRule.patternSuggestionElement.pattern}</td>
												</tr>
												<tr>
													<td><b>Sugestão:</b></td>
													<td>${mistake.brokenRule.patternSuggestionElement.suggestion}</td>
												</tr>
												</c:forEach>
												TODO: Colocar as informações completas das regras
											</table>
											</span>
											</div>
											<div class="popupBtm"></div>
										</div>
									</span>
								</c:when>
								<c:otherwise>
									<c:out value="${checkedSegment.segment}"/>
								</c:otherwise>
								</c:choose>
						</c:forEach>
						<br>
						</c:forEach>
					</div>
				</c:if>
				<button type="button" class="button" onclick="javascript:history.go(-1);return false;">Voltar</button>
			</div>
		</div>
	
<%@ include file="../shared/footer.jsp"%>