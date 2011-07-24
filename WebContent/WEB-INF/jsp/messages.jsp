<c:if test="${not empty errors}">
	<div id="modal" class="popup_block">
		<div class="error-messages">
			<h2>Erro!</h2>
			<table>
				<c:forEach items="${errors}" var="error">
					<tr>
					<td class='align-top'><b><c:out value="${error.category}"/></b>: </td>
					<td><c:out value="${error.message}"/></td>
					</tr>
				</c:forEach>
			</table>
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