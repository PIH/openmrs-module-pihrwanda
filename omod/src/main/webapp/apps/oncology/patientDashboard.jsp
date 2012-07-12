<%@ include file="/WEB-INF/template/include.jsp" %>
<openmrs:require privilege="View Patients" otherwise="/login.htm" redirect="/module/pihrwanda/oncology/index.htm" />
<div id="oncDashboard">
<%@ include file="/WEB-INF/template/header.jsp" %>
<c:set var="baseUrl" value="${pageContext.request.contextPath}"/>

<%-- Files from encounter and graph portlets being included near header to improve page loading speed
     If those tabs/portlets are no longer using them, they should be removed from here --%>
<openmrs:htmlInclude file="/WEB-INF/scripts/easyAjax.js" />

<openmrs:htmlInclude file="/WEB-INF/scripts/jquery/dataTables/css/dataTables.css" />
<openmrs:htmlInclude file="/WEB-INF/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />

<openmrs:htmlInclude file="/WEB-INF/scripts/jquery-ui/js/jquery-ui-1.7.2.custom.min.js" />
<link href="<openmrs:contextPath/>/scripts/jquery-ui/css/<spring:theme code='jqueryui.theme.name' />/jquery-ui.custom.css" type="text/css" rel="stylesheet" />

<openmrs:htmlInclude file="/WEB-INF/scripts/flot/jquery.flot.js" />
<openmrs:htmlInclude file="/WEB-INF/scripts/flot/jquery.flot.multiple.threshold.js"/> 
<%-- /end file imports for portlets --%>

<script type="text/javascript">
	var timeOut = null;

	<openmrs:authentication>var userId = "${authenticatedUser.userId}";</openmrs:authentication>

	//initTabs
	jQuery(document).ready(function() {
		var c = getTabCookie();
		if (c == null) {
			var tabs = document.getElementById("patientTabs").getElementsByTagName("a");
			if (tabs.length && tabs[0].id)
				c = tabs[0].id;
		}
		changeTab(c);
		
		jQuery('#availableCycles').click(function(){ 
			jQuery('#treatmentPlanDialog').dialog('open');
		});

		jQuery('#treatmentPlanDialog').dialog({
			position: 'middle',
			autoOpen: false,
			modal: true,
			title: '<spring:message code="pihrwanda.printTreatmentAdminPlan" javaScriptEscape="true"/>',
			width: '50%',
			zIndex: 100,
			buttons: { '<spring:message code="pihrwanda.print"/>': function() { printTreatmentPlan(); },
					   '<spring:message code="general.cancel"/>': function() { jQuery(this).dialog("close"); }
			}
		});	
		
		jQuery('#appMenu li ul').css(
				{ display: "none", left: "auto" }); 
		
		jQuery('#appMenu li').hover(function() { 
			jQuery(this) .find('ul') .stop(true, true) .slideDown('fast');
			}, function() { 
			jQuery(this) .find('ul') .stop(true,true) .fadeOut('fast'); 
		});
		
		jQuery('#navBar').html(jQuery('#navItems'));
	});
	
	function printTreatmentPlan()
	{	
		jQuery('#treatmentPlan').submit();
		jQuery('#treatmentPlanDialog').dialog("close");
	}
	
	function setTabCookie(tabType) {
		document.cookie = "dashboardTab-" + userId + "="+escape(tabType);
	}
	
	function getTabCookie() {
		var cookies = document.cookie.match('dashboardTab-' + userId + '=(.*?)(;|$)');
		if (cookies) {
			return unescape(cookies[1]);
		}
		return null;
	}
	
	function changeTab(tabObj) {
		if (!document.getElementById || !document.createTextNode) {return;}
		if (typeof tabObj == "string")
			tabObj = document.getElementById(tabObj);
		
		if (tabObj) {
			var tabs = tabObj.parentNode.parentNode.getElementsByTagName('a');
			for (var i=0; i<tabs.length; i++) {
				if (tabs[i].className.indexOf('current') != -1) {
					manipulateClass('remove', tabs[i], 'current');
				}
				var divId = tabs[i].id.substring(0, tabs[i].id.lastIndexOf("Tab"));
				var divObj = document.getElementById(divId);
				if (divObj) {
					if (tabs[i].id == tabObj.id)
						divObj.style.display = "";
					else
						divObj.style.display = "none";
				}
			}
			addClass(tabObj, 'current');
			
			setTabCookie(tabObj.id);
		}
		return false;
    }
</script>

<openmrs:htmlInclude file="/moduleResources/pihrwanda/pihrwanda.css" />

<span id="nav" style="display:none;">
<span id="navItems" >
	<nav role="navigation"> 
		<ul id="appMenu"> 
			<li><a class="homeLink" href="${pageContext.request.contextPath}/module/pihrwanda/apps/oncology/index.form"><img src="${pageContext.request.contextPath}/moduleResources/pihrwanda/images/gohome.png"></a>
			<li><a class="boldWhiteLink" href="#">Find</a> 
				<ul> 
					<li><a href="#">Search for patient</a></li> 
					<li><a href="#">Scan Patient Barcode</a></li>
				</ul>	
			</li>
			<li><a class="boldWhiteLink" href="#">Edit</a> 
				<ul> 
					<li><a href="http://localhost:8080/openmrs/module/htmlformentry/htmlFormEntry.form?returnUrl=/openmrs/module/pihrwanda/apps/oncology/patientDashboard.form?patientId=${patient.patientId}&formId=189&mode=enter">Demographics</a></li> 
				</ul>
			</li>
			<li><a class="boldWhiteLink" href="#">Enter</a>
				<ul> 
					<li><a href="http://localhost:8080/openmrs/module/htmlformentry/htmlFormEntry.form?returnUrl=/openmrs/module/pihrwanda/apps/oncology/patientDashboard.form?patientId=${patient.patientId}&formId=188&mode=enter">Staging & Diagnosis</a></li>
					<li><a href="http://localhost:8080/openmrs/module/htmlformentry/htmlFormEntry.form?returnUrl=/openmrs/module/pihrwanda/apps/oncology/patientDashboard.form?patientId=${patient.patientId}&formId=187&mode=enter">Treatment Administration Summary</a>
				</ul>	
			</li>
			<li><a class="boldWhiteLink" href="#">Print</a>
				<ul> 
					<li><a  href="#">Patient Barcode</a></li>
					<li><a  href="#">Chemotherapy Treatment Administration Plan</a></li>
					<li><a  href="#">Treatment Road map</a>
				</ul>	
			</li>
		</ul>
	</nav>
</span>
</span>
	
<c:if test="${patient.voided}">
	<div id="patientDashboardVoided" class="retiredMessage">
		<div><spring:message code="Patient.voidedMessage"/></div>
	</div>
</c:if>

<c:if test="${patient.dead}">
	<div id="patientDashboardDeceased" class="retiredMessage">
		<div>
			<spring:message code="Patient.patientDeceased"/>
			<c:if test="${not empty patient.deathDate}">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<spring:message code="Person.deathDate"/>: <openmrs:formatDate date="${patient.deathDate}"/>
			</c:if>
			<c:if test="${not empty patient.causeOfDeath}">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<spring:message code="Person.causeOfDeath"/>: <openmrs:format concept="${patient.causeOfDeath}"/>
				<c:if test="${not empty causeOfDeathOther}"> 
					  &nbsp;:&nbsp;<c:out value="${causeOfDeathOther}"></c:out>
				</c:if>
			</c:if>
		</div>
	</div>
</c:if>

<table class="dashboardTable">
	<tr>
		<td class="pihRwandaPatientHeader">
			<div class="patientHeader"><openmrs:portlet url="/apps/oncology/oncPatientHeader.portlet" id="patientDashboardHeader" moduleId="pihrwanda" patientId="${patient.patientId}"/></div>
		</td>

		<td class="pihRwandaPatientTabs">
				<div id="patientTabs${patientVariation}">
					<ul>
						<li><a id="oncSummaryTab" href="#" onclick="return changeTab(this);" hidefocus="hidefocus"><spring:message code="pihrwanda.patientSummary"/></a></li>
						<li><a id="patientRegimenTab" href="#" onclick="return changeTab(this);" hidefocus="hidefocus"><spring:message code="patientDashboard.regimens"/></a></li>
						<openmrs:globalProperty key="visits.enabled" defaultValue="true" var="visitsEnabled"/>
						<c:choose>		
							<c:when test='${visitsEnabled}'>
									<li><a id="patientVisitsTab" href="#" onclick="return changeTab(this);" hidefocus="hidefocus"><spring:message code="patientDashboard.visits"/></a></li>
							</c:when>
							<c:otherwise>
									<li><a id="patientEncountersTab" href="#" onclick="return changeTab(this);" hidefocus="hidefocus"><spring:message code="patientDashboard.encounters"/></a></li>
							</c:otherwise>
						</c:choose>
						
						<li><a id="patientNotesTab" href="#" onclick="return changeTab(this);" hidefocus="hidefocus"><spring:message code="pihrwanda.notes"/></a></li>  
					</ul>
				</div>
			</div>

			<div id="patientSections">
				
				<div id="oncSummary" style="display:none;">
					<openmrs:portlet url="apps/oncology/oncSummary.portlet" id="oncologySummary" moduleId="pihrwanda" patientId="${patient.patientId}"/>
				</div>
				
				<div id="patientRegimen" style="display:none;">
					<div id="availableCycles">
						<c:if test="${!empty cycles}">
							<input type="button" id="printPlan"  value="<spring:message code="pihrwanda.printTreatmentAdminPlan"/>"/>
						</c:if>	
					</div>
					<openmrs:portlet url="patientRegimen" id="patientDashboardRegimen" patientId="${patient.patientId}" parameters="returnUrl=/module/pihrwanda/apps/oncology/patientDashboard.form" />
				</div>
				
				<openmrs:globalProperty key="visits.enabled" defaultValue="true" var="visitsEnabled"/>
				<c:choose>		
					<c:when test='${visitsEnabled}'>
							<div id="patientVisits" style="display:none;">
								<openmrs:portlet url="patientVisits" id="patientDashboardVisits" patientId="${patient.patientId}" />
							</div>
					</c:when>
					<c:otherwise>
							<div id="patientEncounters" style="display:none;">
								<openmrs:globalProperty var="maxEncs" key="dashboard.maximumNumberOfEncountersToShow" defaultValue="" />
								<openmrs:portlet url="patientEncounters" id="patientDashboardEncounters" patientId="${patient.patientId}" parameters="num=${maxEncs}|showPagination=true|formEntryReturnUrl=${pageContext.request.contextPath}/module/pihrwanda/apps/oncology/patientDashboard.form"/>
							</div>
					</c:otherwise>
				</c:choose>
				
				<div id="patientNotes">
					<!-- <openmrs:portlet url="apps/oncology/clinicianNotes.portlet" id="clinicianNotes" moduleId="pihrwanda" patientId="${patient.patientId}"/> -->
				</div>
			</div>
		</td>
	</tr>
</table>

<div id="treatmentPlanDialog">	
	<div class="box">
		<form id="treatmentPlan" name="treatmentPlan" method="post" action="${pageContext.request.contextPath}/module/rwandaReports/printReport.form">
			<input type="hidden" name="patientId" value="${patient.patientId}">
			<input type="hidden" name="report" value="Chemotherapy Treatment Administration Plan">
			<input type="hidden" name="parameters" value="patientId,regimenId">
			<input type="hidden" name="returnPage" value="/module/pihrwanda/apps/oncology/patientDashboard.form?patientId=${patient.patientId}"/>	
			<select name="regimenId" id="regimenId">
				<c:forEach items="${cycles}" var="drugGroup">
					<option value="${drugGroup.id}"><spring:message code="orderextension.regimen.currentCycleNumber" /> <c:out value="${drugGroup.cycleNumber}"/> <spring:message code="general.of" /> <c:out value="${drugGroup.orderSet.name}"/> <spring:message code="general.dateStart"/>: <openmrs:formatDate date="${drugGroup.firstDrugOrderStartDate}" type="medium" /></option>
				</c:forEach>								
			</select>
		</form>
	</div>
</div>


<%@ include file="/WEB-INF/template/footer.jsp" %>
</div>