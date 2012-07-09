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

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Relationship;
import org.openmrs.api.context.Context;
import org.openmrs.web.controller.PortletController;
import org.springframework.stereotype.Controller;

/**
 * The main controller.
 */
@Controller
public class OncPatientHeaderPortletController extends PortletController {
	
	
	/**
	 * @see PortletController#populateModel(HttpServletRequest, Map)
	 */
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {

		Patient patient = Context.getPatientService().getPatient((Integer) model.get("patientId"));
    	
    	List<Person> personList = new ArrayList<Person>();
    	personList.add(patient);
    	
    	List<Concept> telephone = new ArrayList<Concept>();
    	telephone.add(Context.getConceptService().getConcept(Integer.parseInt(Context.getAdministrationService().getGlobalProperty("concept.telephone"))));
    	
    	List<Obs> phone = Context.getObsService().getObservations(personList, null, telephone,
	        null, null, null, null, 1, null, null, null, false);
    	
    	if(phone.size() == 1)
    	{
    		model.put("telephone", phone.get(0).getValueAsString(Context.getLocale()));
    	}
    	   
    	List<Relationship> accomp = Context.getPersonService().getRelationships(patient, null, Context.getPersonService().getRelationshipType(Integer.parseInt(Context.getAdministrationService().getGlobalProperty("relationship.accompagnatuer"))));

    	model.put("healthCenter", patient.getAttribute("Health Center"));
    	model.put("accomp", accomp);
    	model.put("patient", patient);
	}
}
