<%@ taglib prefix="pihrwanda" uri="/WEB-INF/view/module/pihrwanda/resources/pihrwanda.tld" %>
	<c:set var="app" value='<%= session.getAttribute("pihrwanda_selected_app") %>'/>
	<c:choose>
		<c:when test="${!empty app}">
		<c:choose>
			<c:when test="${app.name == 'administration'}">
			<div id="banner">
				<a href="<spring:theme code="url.organization" />">
				  <div id="logosmall"><img src="<%= request.getContextPath() %><spring:theme code="image.logo.text.small" />" alt="OpenMRS Logo" border="0"/></div>
				</a>  
				<table id="bannerbar">
				  <tr>
				    <td id="logocell"> <img src="<%= request.getContextPath() %><spring:theme code="image.logo.small" />" alt="" class="logo-reduced61" />
				    </td>
					<td id="barcell">
				        <div class="barsmall">
				        <img align="left" src="<%= request.getContextPath() %><spring:theme code="image.logo.bar" />" alt="" class="bar-round-reduced50"/>
				        <openmrs:hasPrivilege privilege="View Navigation Menu">
								<%@ include file="/WEB-INF/template/gutter.jsp" %>
						</openmrs:hasPrivilege>
				        </div>
				    </td>
				  </tr>
				</table>
				</div>
				
		</c:when>
		<c:otherwise>
		<a href="${pageContext.request.contextPath}">
			<img style="width:100px; height:25px; vertical-align:middle;" src="${pageContext.request.contextPath}/moduleResources/pihrwanda/images/openmrs_logo_white_large.png" alt="OpenMRS" border="0"/>
		</a>
		<span id="appName"><a class="boldBlackLink" href="${pageContext.request.contextPath}${app.indexUrl}"><spring:message code="pihrwanda.app.${app.name}"/></span>
		</a>
		<div id="navBar" class="fullBar topBar">
			
		</div>
		</c:otherwise>
		</c:choose>
		</c:when>
		<c:otherwise>
		<a href="${pageContext.request.contextPath}">
			<img style="width:100px; height:25px; vertical-align:middle;" src="${pageContext.request.contextPath}/moduleResources/pihrwanda/images/openmrs_logo_white_large.png" alt="OpenMRS" border="0"/>
		</a>
		</c:otherwise>
		</c:choose>


