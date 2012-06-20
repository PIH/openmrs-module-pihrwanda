<%@ include file="/WEB-INF/template/include.jsp" %>
<style>
	.appButton { height:150px; width:150px; color:white; font-weight:bold; background-color:#009384; }
</style>

<%@ include file="/WEB-INF/template/header.jsp" %>

<table border="0" align="center" cellspacing="20" style="padding-top:50px;">
	<tr>
		<c:choose>
			<c:when test="${!empty apps}">
				<td align="center" colspan="${fn:length(apps)}">
					<spring:message code="pihrwanda.chooseYourApp"/>
				</td>
			</c:when>
			<c:otherwise>
				<td align="center">
					<spring:message code="pihrwanda.noAccessToAnyApps"/>
				</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<c:forEach items="${apps}" var="app">
			<td align="center">
				<a href="${pageContext.request.contextPath}/module/pihrwanda/chooseApp.form?appName=${app.name}">
					<spring:message code="pihrwanda.app.${app.name}" text="${app.name}" var="buttonLabel"/>
					<button class="appButton">
						<c:forEach items="${fn:split(buttonLabel, ' ')}" var="l" varStatus="ls">
							${l}<c:if test="${!ls.last}"><br/></c:if>
						</c:forEach>
					</button>
				</a>
			</td>					
		</c:forEach>
	</tr>
</table>

<%@ include file="/WEB-INF/template/footer.jsp" %> 