<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patients" otherwise="/login.htm" redirect="/module/pihrwanda/oncology/index.htm" />
<%@ include file="/WEB-INF/template/header.jsp" %>
<c:set var="baseUrl" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
jQuery(document).ready(function() {
	
	jQuery('#searchBarcode').click(function(){ 
		jQuery('#barcodeDialog').dialog('open');
		jQuery('#identifier').focus();
	});
	
	jQuery('#barcodeDialog').dialog({
		position: 'middle',
		autoOpen: false,
		modal: true,
		title: '<spring:message code="pihrwanda.barcode" javaScriptEscape="true"/>',
		zIndex: 100,
		buttons: {'<spring:message code="general.cancel"/>': function() { jQuery(this).dialog("close"); }
		}
	});
	
	jQuery('#chemoList').click(function(){ 
		jQuery('#chemoListDialog').dialog('open');
	});
	
	jQuery('#chemoListDialog').dialog({
		position: 'middle',
		autoOpen: false,
		modal: true,
		title: 'Chemotherapy Expected Patient List',
		width: '50%',
		zIndex: 100,
		buttons: { 'Run': function() { printChemoList(); },
				   '<spring:message code="general.cancel"/>': function() { jQuery(this).dialog("close"); }
		}
	});	
	
	function printChemoList()
	{	
		jQuery('#chemoList').submit();
		jQuery('#chemoListDialog').dialog("close");
	}
});
</script>

<table class="generalIndexTable">
	<tr>
		<td id="reports">
			<c:if test="${!empty reports}">
				<b class="boxHeader">Patient Lists</b>
				<div class="box">
					<a href="#">Active Adult patients</a><br/>
					<a href="#">Active Pediatric patients</a><br/>
					<c:forEach items="${reports}" var="report">
						<a id="chemoList" href="#">${report.name}</a><br/>	
					</c:forEach>
					<a href="#">Out-patient Clinic consultation list</a><br/>
					<a href="#">Pending diagnosis list</a><br/>				
				</div>
			</c:if>
			<b class="boxHeader">Clinical Reports</b>
			<div class="box">
				<a href="#">Late Visit In-patient Report</a><br/>
				<a href="#">Late Visit Out-patient Report</a><br/>			
			</div>
			<b class="boxHeader">Program Management</b>
			<div class="box">
				<a href="#">Drug forecast report</a><br/>
				<a href="#">Monthly M&E indicators</a><br/>	
				<a href="#">Quarterly M&E indicators</a><br/>				
			</div>
		</td>
		<td id="patientSearch">
			<openmrs:portlet id="findPatient" url="findPatient" parameters="size=full|postURL=${baseUrl}/module/pihrwanda/apps/oncology/patientDashboard.form|showIncludeVoided=false|hideAddNewPatient=false|viewType=shortEdit|autoJump=false|size=full" />
			<span id="indexButtons">
				<input type="button" id="searchBarcode" value="<spring:message code="pihrwanda.barcode" />">
				</br>
				</br>
				<a href="http://localhost:8080/openmrs/module/htmlformentry/htmlFormEntry.form?returnUrl=/openmrs/module/pihrwanda/apps/oncology/index.form&formId=186&mode=enter"><input type="button" id="createPatient" value="Create new patient"></a>
			</span>
		</td>
	</tr>
</table>
<br>&nbsp;<br>

<div id="barcodeDialog">
	<div class="box">
		<form id="scanBarcode" name="scanBarcode" method="post" action="${baseUrl}/module/pihrwanda/apps/oncology/scanBarcode.form">
		<input type="text" id="identifier" name="identifier" class="identifier" />
	</div>
</div>

<div id="chemoListDialog">	
	<div class="box">
		<form id="chemoList" name="chemoList" method="post" action="${pageContext.request.contextPath}/module/rwandaReports/printReport.form">
			<input type="hidden" name="report" value="Chemotherapy Expected Patient List">
			<input type="hidden" name="parameters" value="endDate">
			<input type="hidden" name="returnPage" value="/module/pihrwanda/apps/oncology/patientDashboard.form?patientId=${patient.patientId}"/>	
			<openmrs_tag:dateField formFieldName="endDate" startValue=""/>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>