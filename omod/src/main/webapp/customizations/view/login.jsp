<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<openmrs:authentication>
	<c:choose>
		<c:when test="${authenticatedUser == null}">
			<center>
				<img src="${pageContext.request.contextPath}/moduleResources/pihrwanda/images/emr_hands.gif">
				<openmrs:portlet url="login" />
				<script type="text/javascript">
					document.forms[0].elements["refererURL"].value = '';
				</script>
			</center>
			<br/>	
		</c:when>
		<c:otherwise>
			<c:redirect url="/in.htm"/>
		</c:otherwise>
	</c:choose>
</openmrs:authentication>
<%@ include file="/WEB-INF/template/footer.jsp" %> 

