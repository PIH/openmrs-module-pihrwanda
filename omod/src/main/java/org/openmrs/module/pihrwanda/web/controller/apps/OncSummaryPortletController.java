/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.pihrwanda.web.controller.apps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.PatientState;
import org.openmrs.Person;
import org.openmrs.ProgramWorkflow;
import org.openmrs.api.context.Context;
import org.openmrs.module.pihrwanda.web.GlobalPropertiesManagement;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;

/**
 * The main controller.
 */
@Controller
public class OncSummaryPortletController extends PortletController {
	
	private GlobalPropertiesManagement gp = new GlobalPropertiesManagement();
	
	/**
	 * @see PortletController#populateModel(HttpServletRequest, Map)
	 */
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {

		Patient patient = Context.getPatientService().getPatient((Integer) model.get("patientId"));
    	
    	List<Person> personList = new ArrayList<Person>();
    	personList.add(patient);
    	
    	List<PatientProgram> oncologyProgram = Context.getProgramWorkflowService().getPatientPrograms(patient, gp.getProgram(GlobalPropertiesManagement.ONCOLOGY_PROGRAM), null,
	        null, null, null, false);
    	
    	if(oncologyProgram != null && oncologyProgram.size() > 0)
    	{
    		for(PatientProgram oncProg: oncologyProgram)
    		{
	    		if(oncProg.getActive())
	    		{	
		    		ProgramWorkflow diagnosis = gp.getProgramWorkflow(GlobalPropertiesManagement.DIAGNOSIS_WORKFLOW, GlobalPropertiesManagement.ONCOLOGY_PROGRAM);
		    		ProgramWorkflow diagnosisStatus = gp.getProgramWorkflow(GlobalPropertiesManagement.DIAGNOSIS_STATUS_WORKFLOW, GlobalPropertiesManagement.ONCOLOGY_PROGRAM);
		    		ProgramWorkflow surgeryStatus = gp.getProgramWorkflow(GlobalPropertiesManagement.SURGERY_STATUS_WORKFLOW, GlobalPropertiesManagement.ONCOLOGY_PROGRAM);
		    		ProgramWorkflow radiationStatus = gp.getProgramWorkflow(GlobalPropertiesManagement.RADIATION_STATUS_WORKFLOW, GlobalPropertiesManagement.ONCOLOGY_PROGRAM);
		    		ProgramWorkflow chemotherapyStatus = gp.getProgramWorkflow(GlobalPropertiesManagement.CHEMOTHERAPY_STATUS_WORKFLOW, GlobalPropertiesManagement.ONCOLOGY_PROGRAM);
		    		ProgramWorkflow treatmentIntent = gp.getProgramWorkflow(GlobalPropertiesManagement.TREATMENT_INTENT_WORKFLOW, GlobalPropertiesManagement.ONCOLOGY_PROGRAM);
	    			
	    			Set<PatientState> states = oncProg.getCurrentStates();
		    		for(PatientState state: states)
		    		{
		    			if(state.getState().getProgramWorkflow().equals(diagnosis))
		    			{
		    				model.put("diagnosis", state.getState().getConcept().getDisplayString());
		    			}
		    			if(state.getState().getProgramWorkflow().equals(diagnosisStatus))
		    			{
		    				model.put("diagnosisStatus", state.getState().getConcept().getDisplayString());
		    			}
		    			if(state.getState().getProgramWorkflow().equals(radiationStatus))
		    			{
		    				model.put("radiationStatus", state.getState().getConcept().getDisplayString());
		    			}
		    			if(state.getState().getProgramWorkflow().equals(chemotherapyStatus))
		    			{
		    				model.put("chemotherapyStatus", state.getState().getConcept().getDisplayString());
		    			}
		    			if(state.getState().getProgramWorkflow().equals(surgeryStatus))
		    			{
		    				model.put("surgeryStatus", state.getState().getConcept().getDisplayString());
		    			}
		    			if(state.getState().getProgramWorkflow().equals(treatmentIntent))
		    			{
		    				model.put("treatmentIntent", state.getState().getConcept().getDisplayString());
		    			}
		    		}
	    		}
    		}
    	}
    	
    	model.put("patient", patient);
	}
}
