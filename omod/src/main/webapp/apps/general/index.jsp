<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patients" otherwise="/login.htm" redirect="/module/pihrwanda/general/index.htm" />
<%@ include file="/WEB-INF/template/header.jsp" %>
<c:set var="baseUrl" value="${pageContext.request.contextPath}"/>

<table class="generalIndexTable" style="padding-top:50px;">
	<tr>
		<td width=60% valign='top'>
			<openmrs:portlet id="findPatient" url="findPatient" parameters="size=full|postURL=${baseUrl}/patientDashboard.form|showIncludeVoided=false|hideAddNewPatient=true|viewType=shortEdit" />
			<openmrs:hasPrivilege privilege="Add Patients">
				<br/> &nbsp; <spring:message code="general.or"/><br/><br/>
				<openmrs:portlet id="addPersonForm" url="addPersonForm" parameters="personType=patient|postURL=${baseUrl}/admin/person/addPerson.htm|viewType=shortEdit" />
			</openmrs:hasPrivilege>
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;
		</td>
		<td valign='top' width="40%">
			<c:if test="${showAnalysis}">
				<b class="boxHeader"><spring:message code="pihrwanda.analysis" text="Analysis"/></b>
				<div class="box">
					<a href="${baseUrl}/cohortBuilder.list">
						<spring:message code="pihrwanda.cohortBuilder" text="Cohort Builder"/>
					</a>
				</div>
			</c:if>
			<br/><br/>
			<c:if test="${!empty reports}">
				<b class="boxHeader"><spring:message code="pihrwanda.reports" text="Reports"/></b>
				<div class="box">
					<c:forEach items="${reports}" var="report">
						<a href="${baseUrl}/module/reporting/run/runReport.form?reportId=${report.uuid}">
							${report.name}<br/>
						</a>
					</c:forEach>				
				</div>
			</c:if>
		</td>
	</tr>
</table>
<br>&nbsp;<br>

<%@ include file="/WEB-INF/template/footer.jsp" %>