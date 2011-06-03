<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
	
	<title>Sugest&otilde;es de Estilo</title>
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
	<div id="header">
		<div id="header-content">
			<div id="logo">
				<a href='/sistemacorrecao' title='CATA'><b>C</b>ollaborative <b>A</b>cademic <b>T</b>ext <b>A</b>dvisor</a>
			</div>
			
			<div id="menu" class="nav_bar">
				<ul>
				<li><a href='/sistemacorrecao' title='Início'>Início</a></li>
				<li><a href=# title='Sobre'>Sobre</a></li>
				<li><a href='/sistemacorrecao/advice' title='Advice'>Advice</a></li>
				</ul>	
			</div>
		</div>
	</div>
	
	<div id="page">
		<div id="content">
			<h1>Sugest&otilde;es</h1>
			${output}<br>
			<br>
			<c:if test = "${numOfErrors != 0}">
			<div id="text">
				<b>Arquivo: <c:out value="${fileName}"/></b>
				<br />
				<br />
				<c:forEach items="${text}" var="analyzedLine">
					<c:forEach items="${analyzedLine.analyzedLine}" var="segment">
						<c:choose>
						<c:when test="${segment.brokenRule != null}">
							<span class="popupConf">
								<span class="highlightedText">
									<c:set var="category" value="Erro" />
									<c:choose>
									<c:when test="${segment.brokenRule.category == category}">
										<c:set var="color" value="#780000" />
									</c:when>
									<c:otherwise>
										<c:set var="color" value="#ffcc33" />
									</c:otherwise>
									</c:choose>
									<font style="color: ${color}">
										<b><c:out value="${segment.text}"/></b>
									</font>
								</span>
								<div class="popup">
									<div class="popupTop"></div>
									<div class="popupMid">
									<span class="popupContent">
									&nbsp;<b>Problema:</b>&nbsp;${segment.brokenRule.type}&nbsp;<br>
									&nbsp;<b>Sugest&atilde;o:</b>&nbsp;${segment.brokenRule.suggestion}&nbsp;<br>
									&nbsp;<b>Refer&ecirc;ncia:</b>&nbsp;<a href="${segment.brokenRule.reference}" target="_blank">Tradução de Textos de Computação e Informática para o Português</a>&nbsp;
									</span>
									</div>
									<div class="popupBtm"></div>
								</div>
							</span>
						</c:when>
						<c:otherwise>
							<c:out value="${segment.text}"/>
						</c:otherwise>
						</c:choose>
				</c:forEach>
				<br>
				</c:forEach>
			</div>
			</c:if>
		</div>
	</div>
	
	<div id="footer" align="center">
		<ul>
			<li><a href="/sistemacorrecao" title="Início" class="hover">Início</a>|</li>
			<li><a href="#" title="Sobre">Sobre</a>|</li>
			<li><a href="/sistemacorrecao/advice" title="Advice">Advice</a></li>
		</ul><br/><br/><br class="spacer" />
	</div>
</body>

</html>