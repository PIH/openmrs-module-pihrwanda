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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.PatientState;
import org.openmrs.Person;
import org.openmrs.ProgramWorkflow;
import org.openmrs.ProgramWorkflowState;
import org.openmrs.api.context.Context;
import org.openmrs.module.pihrwanda.web.GlobalPropertiesManagement;
import org.openmrs.module.pihrwanda.web.TransfusionPOJO;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;

/**
 * The main controller.
 */
@Controller
public class OncSummaryPortletController extends PortletController {
	
	private GlobalPropertiesManagement gp = new GlobalPropertiesManagement();
	
	private Concept redTransfusion = gp.getConcept(GlobalPropertiesManagement.RED_TRANSFUSION);
	private Concept wholeTransfusion = gp.getConcept(GlobalPropertiesManagement.WHOLE_TRANSFUSION);
	private Concept pltTransfusion = gp.getConcept(GlobalPropertiesManagement.PLTS_TRANSFUSION);
	private Concept otherTransfusion = gp.getConcept(GlobalPropertiesManagement.OTHER_TRANSFUSION_CONSTRUCT);
	private Concept otherTransfusionType = gp.getConcept(GlobalPropertiesManagement.OTHER_TRANSFUSION_TYPE);
	private Concept otherTransfusionUnits = gp.getConcept(GlobalPropertiesManagement.OTHER_TRANSFUSION_UNITS);
	
	private String redTransfusionMP = "Red blood cells";
	private String wholeTransfusionMP = "Whole blood";
	private String pltTransfusionMP = "Platelets";
	
	/**
	 * @see PortletController#populateModel(HttpServletRequest, Map)
	 */
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {

		Patient patient = Context.getPatientService().getPatient((Integer) model.get("patientId"));
    	
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
		    				
		    				ProgramWorkflowState all = gp.getProgramWorkflowState(GlobalPropertiesManagement.ALL_DIAGNOSIS_STATE, GlobalPropertiesManagement.DIAGNOSIS_WORKFLOW, GlobalPropertiesManagement.ONCOLOGY_PROGRAM);
		    				
		    				if(state.getState().equals(all))
		    				{
		    					mapObs(model, patient, "allStaging", GlobalPropertiesManagement.ALL_STAGING);
		    					
		    					mapObs(model, patient, "phStatus", GlobalPropertiesManagement.PH_STATUS);
		    				}
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
		    		mapObs(model, patient, "diagnosisComment", GlobalPropertiesManagement.DIAGNOSIS_COMMENTS);
	    		}
    		}
    	}
    	
    	List<Date> labDates = new ArrayList<Date>();
    	
    	mapObs(model, patient, "hb", GlobalPropertiesManagement.HB_TEST, labDates);
    	mapObs(model, patient, "hct", GlobalPropertiesManagement.HCT_TEST, labDates);
    	mapObs(model, patient, "wbc", GlobalPropertiesManagement.WBC_TEST, labDates);
    	mapObs(model, patient, "neutro", GlobalPropertiesManagement.NEUTRO_TEST, labDates);
    	mapObs(model, patient, "lympho", GlobalPropertiesManagement.LYMPHO_TEST, labDates);
    	mapObs(model, patient, "plts", GlobalPropertiesManagement.PLTS_TEST, labDates);
    	mapObs(model, patient, "esr", GlobalPropertiesManagement.ESR_TEST, labDates);
    	mapObs(model, patient, "anc", GlobalPropertiesManagement.ANC_TEST, labDates);
    	mapObs(model, patient, "na", GlobalPropertiesManagement.NA_TEST, labDates);
    	mapObs(model, patient, "k", GlobalPropertiesManagement.K_TEST, labDates);
    	mapObs(model, patient, "cl", GlobalPropertiesManagement.CL_TEST, labDates);
    	mapObs(model, patient, "co2", GlobalPropertiesManagement.CO2_TEST, labDates);
    	mapObs(model, patient, "urea", GlobalPropertiesManagement.UREA_TEST, labDates);
    	mapObs(model, patient, "cr", GlobalPropertiesManagement.CR_TEST, labDates);
    	mapObs(model, patient, "glu", GlobalPropertiesManagement.GLU_TEST, labDates);
    	mapObs(model, patient, "ca", GlobalPropertiesManagement.CA_TEST, labDates);
    	mapObs(model, patient, "sgot", GlobalPropertiesManagement.SGOT_TEST, labDates);
    	mapObs(model, patient, "sgpt", GlobalPropertiesManagement.SGPT_TEST, labDates);
    	mapObs(model, patient, "tbili", GlobalPropertiesManagement.TBILI_TEST, labDates);
    	mapObs(model, patient, "dbili", GlobalPropertiesManagement.DBILI_TEST, labDates);
    	mapObs(model, patient, "alkphos", GlobalPropertiesManagement.ALKPHOS_TEST, labDates);
    	mapObs(model, patient, "uricAcid", GlobalPropertiesManagement.URIC_ACID_TEST, labDates);
    	
    	if(labDates.size() == 1)
    	{
    		model.put("labResultDates", labDates.get(0));
    	}
    	
    	List<Concept> transfusionConcepts = new ArrayList<Concept>();
    	transfusionConcepts.add(gp.getConcept(GlobalPropertiesManagement.RED_TRANSFUSION));
    	transfusionConcepts.add(gp.getConcept(GlobalPropertiesManagement.WHOLE_TRANSFUSION));
    	transfusionConcepts.add(gp.getConcept(GlobalPropertiesManagement.PLTS_TRANSFUSION));
    	transfusionConcepts.add(gp.getConcept(GlobalPropertiesManagement.OTHER_TRANSFUSION_CONSTRUCT));
    	
    	Calendar threeMonths = Calendar.getInstance();
    	threeMonths.add(Calendar.MONTH, -6);
    	
    	List<Obs> transfusionObs = getAllObs(patient, transfusionConcepts, threeMonths.getTime());
    	model.put("transfusion", processTransfusions(transfusionObs));
    	
    	
    	model.put("patient", patient);
	}
	
	private void mapObs(Map<String, Object> model, Patient patient, String name, String property)
	{
		Obs obs = getObs(patient, property);
			
    	if(obs != null)
    	{
    		model.put(name, obs.getValueAsString(Context.getLocale()));
    	}
	}
	
	private void mapObs(Map<String, Object> model, Patient patient, String name, String property, List<Date> dates)
	{
		Obs obs = getObs(patient, property);
    	
    	if(obs != null)
    	{
    		model.put(name, obs.getValueAsString(Context.getLocale()));
    		model.put(name + "date", obs.getObsDatetime());
    		
    		if(!dates.contains(obs.getObsDatetime()))
    		{
    			dates.add(obs.getObsDatetime());
    		}
    	}
	}
	
	private Obs getObs(Patient patient, String property)
	{
		List<Person> personList = new ArrayList<Person>();
    	personList.add(patient);
		
		List<Concept> concept = new ArrayList<Concept>();
    	concept.add(gp.getConcept(property));
		
		List<Obs> obs = Context.getObsService().getObservations(personList, null, concept,
	        null, null, null, null, 1, null, null, null, false);
    	
    	if(obs.size() == 1)
    	{
    		return obs.get(0);
    	}
    	
    	return null;
	}
	
	private List<Obs> getAllObs(Patient patient, List<Concept> concepts, Date date)
	{
		List<Person> personList = new ArrayList<Person>();
    	personList.add(patient);
    	
		List<Obs> obs = Context.getObsService().getObservations(personList, null, concepts,
	        null, null, null, null, null, null, date, null, false);
		
		return obs;
	}
	
	private List<TransfusionPOJO> processTransfusions(List<Obs> obs)
	{
		List<TransfusionPOJO> pojo = new ArrayList<TransfusionPOJO>();
		for(Obs ob: obs)
		{
			TransfusionPOJO transfusion = new TransfusionPOJO();
			if(ob.getConcept().equals(redTransfusion))
			{
				transfusion.setTransfusionType(redTransfusionMP);
				transfusion.setUnits(ob.getValueNumeric());
				transfusion.setDate(ob.getObsDatetime());
			}
			else if(ob.getConcept().equals(wholeTransfusion))
			{
				transfusion.setTransfusionType(wholeTransfusionMP);
				transfusion.setUnits(ob.getValueNumeric());
				transfusion.setDate(ob.getObsDatetime());
			}
			else if(ob.getConcept().equals(pltTransfusion))
			{
				transfusion.setTransfusionType(pltTransfusionMP);
				transfusion.setUnits(ob.getValueNumeric());
				transfusion.setDate(ob.getObsDatetime());
			}
			else {
				Set<Obs> members = ob.getGroupMembers();
				for(Obs member: members)
				{
					if(member.getConcept().equals(otherTransfusionType))
					{
						transfusion.setTransfusionType(member.getValueText());
					}
					else if(member.getConcept().equals(otherTransfusionUnits))
					{
						transfusion.setUnits(member.getValueNumeric());
					}
				}
				transfusion.setDate(ob.getObsDatetime());
			}
			pojo.add(transfusion);
		}
		
		return pojo;
	}
}
