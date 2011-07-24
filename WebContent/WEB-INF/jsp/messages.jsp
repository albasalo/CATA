<c:if test="${not empty errors}">
	<div id="basic-modal-content">
		<div class="error-messages">
			<h2>Erro!</h2>
			<c:forEach items="${errors}" var="error">
				${error.category}: ${error.message}<br />
			</c:forEach>
			<br />
		</div>
	</div>
</c:if>