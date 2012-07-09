<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="View Patients" otherwise="/login.htm"
	redirect="/index.htm" />
	
<openmrs:globalProperty key="visits.enabled" var="visitsEnabled" />

<div class="pihRwanda">

	<div id="patientHeaderPatientNameOnc">${model.patient.personName}</div>
	<div id="patientGender">
		
		<c:if test="${model.patient.gender == 'M'}">
			<img src="${pageContext.request.contextPath}/images/male.gif"
				alt='<spring:message code="Person.gender.male"/>'
				id="maleGenderIcon" />
		</c:if> <c:if test="${model.patient.gender == 'F'}">
			<img src="${pageContext.request.contextPath}/images/female.gif"
				alt='<spring:message code="Person.gender.female"/>'
				id="femaleGenderIcon" />
		</c:if>
		<div class=years>
		<c:if
			test="${model.patient.age > 0}">${model.patient.age} <spring:message
				code="Person.age.years" />
		</c:if> <c:if test="${model.patient.age == 0}">< 1 <spring:message
				code="Person.age.year" />
		</c:if>
		</div>
	</div>
	
	<table id="patientHeaderInfo">
		<tr class="header">
			<td class="padded"><spring:message code="pihrwanda.general"/></td>
		</tr>
		<tr>
			<td class="padded">
			<table>
				<tr>
					<td class="indentedHeader"><strong><spring:message code="pihrwanda.identifier"/>: </strong>
					<c:choose>
						<c:when test="${fn:length(model.patient.activeIdentifiers) > 0}">
							<c:forEach var="identifier" items="${model.patient.activeIdentifiers}" varStatus="status">
								<c:if test="${status.count > 1 }">
									<tr><td></td>
								</c:if>
								<td class="indentedValue"> ${identifier.identifier}</td></tr>
								<tr> <td colspan=2 class="identifierType">(${identifier.identifierType.name})</td></tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<td></td></tr>
						</c:otherwise>
					</c:choose>
				</tr>	
			</table>
			</td>
		</tr>
		
		<tr>
			<td class="padded"><strong><spring:message code="PersonAttributeType.HealthCenter" />: </strong>${model.healthCenter}</td>
		</tr>
		
		<tr>
			<td class="padded"><strong><spring:message code="pihrwanda.phone" />: </strong>${model.telephone }</td>
		</tr>
		
		<tr>
			<td class="padded">
			<table>
				<tr>
					<td class="indentedHeader"><strong><spring:message code="pihrwanda.accompagnatuer"/>: </strong></td>
					<c:choose>
						<c:when test="${fn:length(model.accomp) > 0}">
							<c:forEach var="identifier" items="${model.accomp}" varStatus="status">
								<c:if test="${status.count > 0 }">
									<tr><td></td>
								</c:if>
								<td  class="indentedValue"> ${rel.personB.personName}</td></tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>	
			</table>
			</td>
		</tr>
		
		<tr>
			<td class="paddedLast"><strong><spring:message code="pihrwanda.birthdate"/>: </strong>
				<c:if test="${not empty model.patient.birthdate}">
					<c:if test="${model.patient.birthdateEstimated}">~</c:if>
					<openmrs:formatDate date="${model.patient.birthdate}" type="medium" />
				</c:if>
			</td>
		</tr>
		
		<tr class="header">
			<td class="padded"><spring:message code="pihrwanda.address"/></td>
		</tr>	
		
		<tr>
			<td class="padded"><strong><spring:message code="pihrwanda.district"/>: </strong>${model.patient.personAddress.countyDistrict }</td>
		</tr>
		
		<tr>
			<td class="padded"><strong><spring:message code="pihrwanda.sector"/>: </strong>${model.patient.personAddress.cityVillage }</td>
		</tr>
		
		<tr>
			<td class="padded"><strong><spring:message code="pihrwanda.cell"/>: </strong>${model.patient.personAddress.address3 }</td>
		</tr>
		
		<tr>
			<td class="padded"><strong><spring:message code="pihrwanda.umudugudu"/>: </strong>${model.patient.personAddress.address1 }</td>
		</tr>
	</table>
	
	<div class="actions">
		<input type="button" id="editDemographics"  value="<spring:message code="general.edit"/>"/>
		<input type="button" id="printBarcode"  value="<spring:message code="pihrwanda.printBarcode"/>"/>
	</div>
</div>



