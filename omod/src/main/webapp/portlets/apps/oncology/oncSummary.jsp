<%@ include file="/WEB-INF/template/include.jsp" %>

<table id="oncSummary">
	<tr>
		<td class="oncSummaryCol1">
			<strong><spring:message code="pihrwanda.diagnosis"/>: </strong> ${model.diagnosis }
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