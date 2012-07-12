<%@ include file="/WEB-INF/template/include.jsp"%>

<script type="text/javascript">
jQuery(document).ready(function() {
	
	var url = "http://localhost:8080${pageContext.request.contextPath}/module/htmlformentry/htmlFormEntry.form?personId=${patient.patientId}&patientId=${patient.patientId}&returnUrl=/module/pihrwanda/apps/oncology/patientDashboard.form&formId=185&inPopup=true";
	 
	jQuery("#patientNotes").load(url);
	 
	jQuery(".submitButton").hide();
});
</script>
		 
<div id="clinicianNotes">
</div>