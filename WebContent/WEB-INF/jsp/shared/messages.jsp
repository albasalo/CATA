<c:if test="${not empty errors}">
	<div id="modal" class="popup_block">
		<div class="error-messages">
			<h2>Erro!</h2>
				<c:forEach items="${errors}" var="error">
					<b><c:out value="${error.category}"/></b>: <c:out value="${error.message}"/><br>
				</c:forEach>
		</div>
	</div>
</c:if>

<c:if test="${not empty messages}">
	<div id="modal" class="popup_block">
		<div class="messages">
				<c:forEach items="${messages}" var="message">
					<c:out value="${message}"/><br />
				</c:forEach>
		</div>
	</div>
</c:if>