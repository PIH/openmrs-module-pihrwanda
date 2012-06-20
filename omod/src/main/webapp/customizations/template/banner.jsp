<%@ taglib prefix="pihrwanda" uri="/WEB-INF/view/module/pihrwanda/resources/pihrwanda.tld" %>
<a href="${pageContext.request.contextPath}">
	<img style="width:100px; height:25px; vertical-align:middle;" src="${pageContext.request.contextPath}/moduleResources/pihrwanda/images/openmrs_logo_white_large.png" alt="OpenMRS" border="0"/>
</a>

<div class="fullBar topBar">
	<a class="boldWhiteLink" href="${pageContext.request.contextPath}"><spring:message code="Navigation.home"/></a>
	<c:set var="app" value='<%= session.getAttribute("pihrwanda_selected_app") %>'/>
	<c:if test="${!empty app}">
		<a class="boldWhiteLink" href="${pageContext.request.contextPath}${app.indexUrl}">
			&nbsp;&nbsp;&gt;&nbsp;&nbsp;<spring:message code="pihrwanda.app.${app.name}"/>
		</a>
	</c:if>
</div>

