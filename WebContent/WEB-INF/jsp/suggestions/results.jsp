<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
	<title>Sugest&otilde;es de Corre&ccedil;&atilde;o</title>
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
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
		<div id="logo">
			<h1><a href="<c:url value="/"/>">NOME</a></h1>
			<p>Automa&ccedil;&atilde;o de corre&ccedil;&atilde;o de estilo de textos t&eacute;cnicos de computa&ccedil;&atilde;o</p>
		</div>
	</div>
	
	<div id="page">
		<div id="content">
			<div class="post">
				<h1 class="title">Sugest&otilde;es</h1>
				<div class="entry">
					<br>
					${output}<br>
					<br>
					<div class="output">
						<c:forEach items="${text}" var="line">
						${line.lineText}<br>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>