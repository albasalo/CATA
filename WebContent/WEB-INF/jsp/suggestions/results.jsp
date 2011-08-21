<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/css/advice.css'/>" rel="stylesheet" type="text/css" />
	
	<title>Sugestões de Estilo</title>
    <script type="text/javascript" src="<c:url value='js/jquery-1.3.1.min.js'/>"></script>
    <script type="text/javascript">
	    $(function () {
	        $('.popupConf').each(function() {
	            var duration = 200;
	            var hideDelay = 200;
	            var hideDelayTimer = null;
	
	            var beingDisplayed = false;
	            var displayed = false;
	
	            var trigger = $('.highlightedText', this);
	            var conf = $('.popup', this);
	
	            $([trigger.get(0), conf.get(0)])
					.mouseover(function() {
			            if (hideDelayTimer)
							clearTimeout(hideDelayTimer);
			            if (displayed || beingDisplayed) {
			                return;
			            }
						else {
			                beingDisplayed = true;
	
							var t = $('.highlightedText');
							var position = t.position();
							var left = position.left;
	
			                conf.css({
			                    top: +20,
			                    left: +left,
			                    display: 'block'
			                }).animate({
			                    opacity: 1
			                }, duration, 'linear', function() {
			                    beingDisplayed = false;
			                    displayed = true;
			                });
			            }
	
			            return false;
	            	})
					.mouseout(function () {
	                	if (hideDelayTimer)
							clearTimeout(hideDelayTimer);
		                hideDelayTimer = setTimeout(function () {
	                    	hideDelayTimer = null;
	                    	conf.animate({
	                        	opacity: 0
	                    	}, duration, 'linear', function () {
	                        	displayed = false;
	                        	conf.css('display', 'none');
	                    	});
	    	            }, hideDelay);
	
	    	            return false;
	            	});
	        });
	    });
	</script>
</head>


<body>
	<%@ include file="../shared/header.jsp"%>
	
	<div id="page">
		<div id="content">
			<h1>Sugestões</h1>
			${output}<br>
			<br>
			<c:if test = "${numOfMistakes != 0}">
				<div id="text">
					<b>Arquivo: <c:out value="${fileName}"/></b>
					<br />
					<br />
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
											<tr>
												<td><b>Referência:&nbsp;</b></td>
												<td>${mistake.brokenRule.rule.source.description}</td>
											</tr>
											</c:forEach>
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
		</div>
	</div>
	
<%@ include file="../shared/footer.jsp"%>