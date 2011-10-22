<c:if test="${userSession.user != null}">
	<div id="user-menu">
		<ul>
			<li><a href="<c:url value='/user/profile'/>"><c:out value="${userSession.name}"/></a> |</li>
			<li><a href="<c:url value='/logout'/>">Sair</a></li>
		</ul>
	</div>
</c:if>