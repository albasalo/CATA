<table>
	<c:if test = "${rule.source.type == 'ACADEMIC_PUBLISHING'}">
		<tr><td><c:out value="${rule.source.authors}"/>, <i><c:out value="${rule.source.title}"/></i></td></tr>
		<c:choose>
			<c:when test="${rule.source.institution != null}">
				<c:choose>
					<c:when test="${rule.source.date != null}">
						<tr><td><c:out value="${rule.source.institution}"/>, <c:out value="${rule.source.date}"/></td></tr>
					</c:when>
					<c:otherwise>
						<tr><td><c:out value="${rule.source.institution}"/></td></tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr><td><c:out value="${rule.source.date}"/></td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${rule.source.moreInformation != null}">
			<tr><td><c:out value="${rule.source.moreInformation}"/></td></tr>
		</c:if>
	</c:if>
	<c:if test = "${rule.source.type == 'BOOK'}">
		<tr><td><c:out value="${rule.source.authors}"/>, <i><c:out value="${rule.source.title}"/></i></td></tr>
		<c:choose>
			<c:when test="${rule.source.publisher != null}">
				<c:choose>
					<c:when test="${rule.source.date != null}">
						<tr><td><c:out value="${rule.source.publisher}"/>, <c:out value="${rule.source.date}"/></td></tr>
					</c:when>
					<c:otherwise>
						<tr><td><c:out value="${rule.source.publisher}"/></td></tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr><td><c:out value="${rule.source.date}"/></td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${rule.source.moreInformation != null}">
			<tr><td><c:out value="${rule.source.moreInformation}"/></td></tr>
		</c:if>
	</c:if>
	<c:if test = "${rule.source.type == 'HANDBOOK'}">
		<tr><td><c:out value="${rule.source.authors}"/>, <i><c:out value="${rule.source.title}"/></i></td></tr>
		<c:choose>
			<c:when test="${rule.source.publisher != null}">
				<c:choose>
					<c:when test="${rule.source.date != null}">
						<tr><td><c:out value="${rule.source.publisher}"/>, <c:out value="${rule.source.date}"/></td></tr>
					</c:when>
					<c:otherwise>
						<tr><td><c:out value="${rule.source.publisher}"/></td></tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr><td><c:out value="${rule.source.date}"/></td></tr>
			</c:otherwise>
		</c:choose>
		<c:if test="${rule.source.moreInformation != null}">
			<tr><td><c:out value="${rule.source.moreInformation}"/></td></tr>
		</c:if>
	</c:if>
	<c:if test = "${rule.source.type == 'INTERNET'}">
		<tr><td><a href="<c:out value="${rule.source.url}" />" target="_blank"><c:out value="${rule.source.title}"/></a></td></tr>
		<c:if test="${rule.source.moreInformation != null}">
			<tr><td><c:out value="${rule.source.moreInformation}"/></td></tr>
		</c:if>
	</c:if>
	<c:if test = "${rule.source.type == 'OTHER'}">
		<tr><td><c:out value="${rule.source.moreInformation}"/></td></tr>
	</c:if>
</table>