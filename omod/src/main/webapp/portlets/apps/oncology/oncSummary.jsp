<%@ include file="/WEB-INF/template/include.jsp" %>

<div id="oncClinicalSummary">
	<div class="box${model.patientVariation}">
		<table id="oncSummary">
			<tr>
				<td class="oncSummaryCol1">
					<strong><spring:message code="pihrwanda.diagnosis"/>: </strong>${model.diagnosis }
				</td>
				<td class="oncSummaryCol2">
					<strong><spring:message code="pihrwanda.treatmentIntent"/>: </strong> ${model.treatmentIntent }
				</td>
			</tr>
			<tr>
				<td>
					<strong><spring:message code="pihrwanda.diagnosisStatus"/>: </strong> ${model.diagnosisStatus }
				</td>
				<td>
					<strong><spring:message code="pihrwanda.surgeryStatus"/>: </strong> ${model.surgeryStatus }
				</td>
			</tr>
			<tr>
				<td>
					<strong><spring:message code="pihrwanda.diagnosisComments"/>: </strong> ${model.diagnosisComment }
				</td>
				<td>
					<strong><spring:message code="pihrwanda.radiationStatus"/>: </strong> ${model.radiationStatus }
				</td>
			</tr>
			<tr>
				<td>
					
				</td>
				<td>
					<strong><spring:message code="pihrwanda.chemotherapyStatus"/>: </strong> ${model.chemotherapyStatus }
				</td>
			</tr>
		</table>
		<div class="boxHeader${model.patientVariation}"><spring:message code="pihrwanda.staging"/></div>
		<div class="box${model.patientVariation}">	
			<table class="staging">
				<tr>
					<td>
						<c:if test="${!empty model.allStaging }"><strong><spring:message code="pihrwanda.allStaging"/>: </strong> ${model.allStaging }</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<c:if test="${!empty model.phStatus }"><strong><spring:message code="pihrwanda.phStatus"/>: </strong> ${model.phStatus }</c:if>	
					</td>
				</tr>	
			</table>
		</div>
	</div>
	</br>
	<div class="boxHeader${model.patientVariation}"><spring:message code="pihrwanda.labResults"/><c:if test="${!empty model.labResultDates}">: <openmrs:formatDate date="${model.labResultDates}" type="medium" /> </c:if></div>
	<div class="box${model.patientVariation}">	
		<table class="labresult">
			<tr>
				<th><spring:message code="pihrwanda.hb"/></th>
				<th><spring:message code="pihrwanda.hct"/></th>
				<th><spring:message code="pihrwanda.wbc"/></th>
				<th><spring:message code="pihrwanda.neutro"/></th>
				<th><spring:message code="pihrwanda.lympho"/></th>
				<th><spring:message code="pihrwanda.plts"/></th>
				<th><spring:message code="pihrwanda.esr"/></th>
				<th><spring:message code="pihrwanda.anc"/></th>
				<th><spring:message code="pihrwanda.na"/></th>
				<th><spring:message code="pihrwanda.k"/></th>
				<th><spring:message code="pihrwanda.cl"/></th>
				<th><spring:message code="pihrwanda.co2"/></th>
				<th><spring:message code="pihrwanda.urea"/></th>
				<th><spring:message code="pihrwanda.cr"/></th>
				<th><spring:message code="pihrwanda.glu"/></th>
				<th><spring:message code="pihrwanda.ca"/></th>
				<th><spring:message code="pihrwanda.sgot"/></th>
				<th><spring:message code="pihrwanda.sgpt"/></th>
				<th><spring:message code="pihrwanda.tbili"/></th>
				<th><spring:message code="pihrwanda.dbili"/></th>
				<th><spring:message code="pihrwanda.alkphos"/></th>
				<th><spring:message code="pihrwanda.uricAcid"/></th>
			</tr>
			<tr>
				<td>${model.hb}</td>
				<td>${model.hct}</td>
				<td>${model.wbc}</td>
				<td>${model.neutro}</td>
				<td>${model.lympho}</td>
				<td>${model.plts}</td>
				<td>${model.esr}</td>
				<td>${model.anc}</td>
				<td>${model.na}</td>
				<td>${model.k}</td>
				<td>${model.cl}</td>
				<td>${model.co2}</td>
				<td>${model.urea}</td>
				<td>${model.cr}</td>
				<td>${model.glu}</td>
				<td>${model.ca}</td>
				<td>${model.sgot}</td>
				<td>${model.sgpt}</td>
				<td>${model.tbili}</td>
				<td>${model.dbili}</td>
				<td>${model.alkphos}</td>
				<td>${model.uricAcid}</td>
			</tr>	
			<c:if test="${empty model.labResultDates}">
				<tr class="resultDates">
					<td><openmrs:formatDate date="${model.hbdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.hctdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.wbcdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.neutrodate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.lymphodate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.pltsdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.esrdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.ancdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.nadate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.kdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.cldate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.co2date}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.ureadate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.crdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.gludate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.cadate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.sgotdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.sgptdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.tbilidate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.dbilidate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.alkphosdate}" type="medium" /></td>
					<td><openmrs:formatDate date="${model.uricAciddate}" type="medium" /></td>
				</tr>
			</c:if>
		</table>
	</div>
	</br>
	<div class="boxHeader${model.patientVariation}"><spring:message code="pihrwanda.otherConditions"/></div>
	<div class="box${model.patientVariation}">	
		<table class="otherConditions">
			<tr>
				<td></td>
			</tr>
		</table>	
	</div>		
	</br>
	<div class="boxHeader${model.patientVariation}"><spring:message code="pihrwanda.transfusions"/></div>
	<div class="box${model.patientVariation}">	
		<table class="transfusions">
			<tr>
				<th>
					<spring:message code="pihrwanda.date"/>
				</th>
				<th>
					<spring:message code="pihrwanda.type"/>
				</th>
				<th>
					<spring:message code="pihrwanda.units"/>
				</th>
			</tr>
			<c:forEach var="trans" items="${model.transfusion}">
				<tr>
					<td>
						<openmrs:formatDate date="${trans.date}" type="medium" />
					</td>
					<td>
						${trans.transfusionType}
					</td>
					<td>
						${trans.units}
					</td>
				</tr>
			</c:forEach>
		</table>	
	</div>	
	</br>
	<div class="boxHeader${model.patientVariation}"><spring:message code="pihrwanda.unplannedHospitalisations"/></div>
	<div class="box${model.patientVariation}">	
		<table class="transfusions">
			<tr>
				<th>
					<spring:message code="pihrwanda.admissiondate"/>
				</th>
				<th>
					<spring:message code="pihrwanda.dischargedate"/>
				</th>
				<th>
					<spring:message code="pihrwanda.visitreason"/>
				</th>
			</tr>
			<tr>
				<td>
					
				</td>
				<td>
					
				</td>
				<td>
					
				</td>
			</tr>
		</table>	
	</div>				
</div>