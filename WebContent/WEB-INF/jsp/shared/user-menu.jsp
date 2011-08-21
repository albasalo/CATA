<div id="user-menu">
	<ul>
		<li><c:out value="${userSession.name}"></c:out> |</li>
		<li><a href="<c:url value='/logout'/>">Sair</a></li>
	</ul>
</div>