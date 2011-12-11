<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/advice.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-table.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.3.2.min.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/js/jquery.qtip-1.0.0-rc3.min.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/js/tooltip.js'/>"></script>
	    
	    <script type="text/javascript">
		    function giveOpinion(type, pairID, mistakeID) {
		    	var mistake = "#mistake" + mistakeID;
		    	var url = "<c:url value='/suggestions/opinion'/>";
		    	url = url + "/" + type + "|" + pairID + "|" + $("#keywords").text();
		    	if(type == "agree") {
		    		$.ajax(
		    		{		
		    			type: "POST",
		    			url: url
		    		});
		    		var agree = "#agree" + mistakeID;
		    		var val = parseInt($(agree).text()) + 1;
		    		var agreed = "#agreed" + mistakeID;
		    		$(agreed).text(val);
		    		
		    		$(mistake).qtip("destroy");
		    		var giveOpinion = "#giveOpinion" + mistakeID;
		    		var opinion = "#opinion" + mistakeID;
		    		$(giveOpinion).hide();
		    		$(opinion).show();
		    		var content = "#tooltipcontent" + mistakeID;
		    		showToolTip(mistake, content);
		    	}
		    	else {
		    		$.ajax(
		    		{		
		    			type: "POST",
		    			url: url
		    		});
		    		$(mistake).qtip("destroy");
		    		$(mistake).removeClass("highlightedText");
		    		$(mistake).addClass("disagree");
		    	}
		    }
	    </script>
		
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
					<div id="keywords" style="display: none">
						${keywords}
					</div>
					<div id="text">
						<b>Arquivo: <c:out value="${fileName}"/></b>
						<br /><br />
						<%! int mistakeIndex = 0;
							int getMistakeIndex() { return mistakeIndex; }
						%>
						<% mistakeIndex = 0; %>
						<c:forEach items="${text}" var="checkedLine">
							<c:forEach items="${checkedLine}" var="checkedSegment">
								<c:choose>
								<c:when test="${checkedSegment.mistakes != null}">
									<span class="tooltipConf">
										<span id="mistake<%=getMistakeIndex()%>" class="highlightedText">
											<b>&nbsp;<c:out value="${checkedSegment.segment}"/></b>
										</span>
										&nbsp;
										<div class="tooltip">
											<span id="tooltipcontent<%=getMistakeIndex()%>" class="tooltipContent">
												<%! int i = 0;
													int getBrokenRuleIndex() { return i; }
												%>
												<% i = 0; %>
												<c:forEach items="${checkedSegment.mistakes}" var="mistake">
													<c:set var="rule" value="${mistake.brokenRule.rule}"/>
													<c:set var="brokenRuleIndex" value="<%= getBrokenRuleIndex()%>"/>
														<c:if test="${brokenRuleIndex > 0}">
															<center>
															_______________________________<br /><br />
															</center>
														</c:if>
														<% i = i + 1; %>
														<table class="modal-table">
															<tr>
																<td><b>Sugestões:&nbsp;</b></td>
																<td>
																	<span class="suggestions">
																		<b>&nbsp;<c:out value="${mistake.brokenRule.patternSuggestionPair.suggestions}"/>&nbsp;</b>
																	</span>
																</td>
															</tr>
															<tr>
																<td><b><c:out value="${rule.category.categoryDescription}"/>: </b></td>
																<td><c:out value="${rule.type.typeDescription}"/></td>
															</tr>
															<tr>
																<td></td>
																<td>Padrão incorreto encontrado: <i><c:out value="${checkedSegment.segment}"/></i>
															</tr>
															<c:if test="${rule.explanation != null}">
																<tr>
																	<td><b>Explicação: </b></td>
																	<td><c:out value="${rule.explanation}"/></td>
																</tr>
															</c:if>
															<tr>
																<td><b>Referência: </b></td>
																<td><%@ include file="../shared/table-sources.jsp"%></td>
															</tr>
															<tr>
																<td><b>Usuário:</b></td>
																<td>Esta regra foi cadastrada por <c:out value="${rule.user.name}"/></td>
															</tr>
															<tr id="giveOpinion<%=getMistakeIndex()%>">
																<td><b>Opine:</b></td>
																<td>
																	<span class="hand" onclick="giveOpinion('agree', '<c:out value="${mistake.brokenRule.patternSuggestionPair.patternSuggestionPairID}"/>', <%=getMistakeIndex()%>)"><img src="<c:url value='/css/images/up.png'/>"/></span>&nbsp;&nbsp;&nbsp;
																	<span class="hand" onclick="giveOpinion('disagree', '<c:out value="${mistake.brokenRule.patternSuggestionPair.patternSuggestionPairID}"/>', <%=getMistakeIndex()%>)"><img src="<c:url value='/css/images/down.png'/>"/></span>
																</td>
															</tr>
															<tr id="opinion<%=getMistakeIndex()%>" style="display: none">
																<td><b>Opine:</b></td>
																<td>
																	<span><img src="<c:url value='/css/images/up.png'/>"/></span> Concordo!
																</td>
															</tr>
														</table>
												</c:forEach>
											</span>
										</div>
									</span>
									<% mistakeIndex = mistakeIndex + 1; %>
								</c:when>
								<c:otherwise>
									<c:out value="${checkedSegment.segment}"/>
								</c:otherwise>
								</c:choose>
						</c:forEach>
						<br>
						</c:forEach>
					</div>
					<br />
				</c:if>
				<button type="button" class="button" onclick="javascript:history.go(-1);return false;">Voltar</button>
			</div>
		</div>
	
<%@ include file="../shared/footer.jsp"%>