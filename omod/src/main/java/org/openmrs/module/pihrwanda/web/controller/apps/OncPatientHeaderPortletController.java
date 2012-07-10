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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.api.context.Context;
import org.openmrs.module.pihrwanda.web.GlobalPropertiesManagement;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;

/**
 * The main controller.
 */
@Controller
public class OncPatientHeaderPortletController extends PortletController {
	
	private GlobalPropertiesManagement gp = new GlobalPropertiesManagement();
	/**
	 * @see PortletController#populateModel(HttpServletRequest, Map)
	 */
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {

		Patient patient = Context.getPatientService().getPatient((Integer) model.get("patientId"));
    	
    	List<Person> personList = new ArrayList<Person>();
    	personList.add(patient);
    	
    	List<Concept> telephone = new ArrayList<Concept>();
    	telephone.add(gp.getConcept(GlobalPropertiesManagement.TELEPHONE_NUMBER_CONCEPT));
    	
    	List<Obs> phone = Context.getObsService().getObservations(personList, null, telephone,
	        null, null, null, null, 1, null, null, null, false);
    	
    	if(phone.size() == 1)
    	{
    		model.put("telephone", phone.get(0).getValueAsString(Context.getLocale()));
    	}
    	 
    	List<Date> weightDates = new ArrayList<Date>();
    	mapObs(model, patient, "weight", GlobalPropertiesManagement.WEIGHT_CONCEPT, weightDates);
    	mapObs(model, patient, "height", GlobalPropertiesManagement.HEIGHT_CONCEPT, weightDates);
    	
    	mapBSA(model, patient, GlobalPropertiesManagement.WEIGHT_CONCEPT, GlobalPropertiesManagement.WEIGHT_CONCEPT);
    	
    	
    	List<Relationship> relationships = Context.getPersonService().getRelationshipsByPerson(patient, new Date());
    	List<Relationship> accomp = new ArrayList<Relationship>();
    	List<Relationship> managingDCA = new ArrayList<Relationship>();
    	List<Relationship> managingGP = new ArrayList<Relationship>();
    	
    	RelationshipType accompType =  gp.getRelationshipType(GlobalPropertiesManagement.ACCOMPAGNATUER_RELATIONSHIP);
    	RelationshipType dca = gp.getRelationshipType(GlobalPropertiesManagement.MANAGING_DCA);
    	RelationshipType gpType = gp.getRelationshipType(GlobalPropertiesManagement.MANAGING_GP);
    	
    	for(Relationship rel: relationships)
    	{
    		if(rel.getRelationshipType().equals(accompType) && rel.getPersonB().equals(patient))
    		{
    			accomp.add(rel);
    		}
    		if(rel.getRelationshipType().equals(dca) && rel.getPersonB().equals(patient))
    		{
    			managingDCA.add(rel);
    		}
    		if(rel.getRelationshipType().equals(gpType) && rel.getPersonB().equals(patient))
    		{
    			managingGP.add(rel);
    		}
    	}
    	
    	model.put("healthCenter", patient.getAttribute("Health Center"));
    	model.put("accomp", accomp);
    	model.put("managingDCA", managingDCA);
    	model.put("managingGP", managingGP);
    	model.put("patient", patient);
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
	
	private void mapBSA(Map<String, Object> model, Patient patient, String propertyWeight, String propertyHeight)
	{
		Obs height = getObs(patient, GlobalPropertiesManagement.HEIGHT_CONCEPT);
		Obs weight = getObs(patient, GlobalPropertiesManagement.WEIGHT_CONCEPT);
		
		if(height != null && height.getValueNumeric() != null && weight != null && weight.getValueNumeric() != null)
		{
			//code courtesy of Dr Chadi Cortas
			double h = height.getValueNumeric();
			double w = weight.getValueNumeric();
		
			double bsa = Math.sqrt(h*w/3600);
			
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			model.put("bsa", Double.valueOf(twoDForm.format(bsa)));
		}
	}
}
