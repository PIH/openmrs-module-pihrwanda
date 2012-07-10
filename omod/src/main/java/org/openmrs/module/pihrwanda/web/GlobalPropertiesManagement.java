package org.openmrs.module.pihrwanda.web;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Drug;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.OrderType;
import org.openmrs.PatientIdentifierType;
import org.openmrs.Program;
import org.openmrs.ProgramWorkflow;
import org.openmrs.ProgramWorkflowState;
import org.openmrs.RelationshipType;
import org.openmrs.api.context.Context;

public class GlobalPropertiesManagement {
	
	
	public Program getProgram(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		Program program = Context.getProgramWorkflowService().getProgramByUuid(globalProperty);
		
		if(program == null)
		{
			program = Context.getProgramWorkflowService().getProgramByName(globalProperty);
		}
		
		if(program == null)
		{
			try{
			program = Context.getProgramWorkflowService().getProgram(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(program == null)
		{
			throw new RuntimeException("Unable to retrieve a program from the global property: " + globalPropertyName);
		}
		
		return program;
	}
	
	public PatientIdentifierType getPatientIdentifier(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		PatientIdentifierType pit = Context.getPatientService().getPatientIdentifierTypeByUuid(globalProperty);
		
		if(pit == null)
		{
			pit = Context.getPatientService().getPatientIdentifierTypeByName(globalProperty);
		}
		
		if(pit == null)
		{
			try{
			pit = Context.getPatientService().getPatientIdentifierType(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(pit == null)
		{
			throw new RuntimeException("Unable to retrieve a patient identifier from the global property: " + globalPropertyName);
		}
		
		return pit;
	}
	
	public Concept getConcept(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		Concept c = Context.getConceptService().getConceptByUuid(globalProperty);
		
		if(c == null)
		{
			c = Context.getConceptService().getConceptByName(globalProperty);
		}
		
		if(c == null)
		{
			try{
				c = Context.getConceptService().getConcept(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(c == null)
		{
			throw new RuntimeException("Unable to retrieve a concept from the global property: " + globalPropertyName);
		}
		
		return c;
	}
	
	public Form getForm(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		Form form = Context.getFormService().getFormByUuid(globalProperty);
		
		if(form == null)
		{
			form = Context.getFormService().getForm(globalProperty);
		}
		
		if(form == null)
		{
			try{
				form = Context.getFormService().getForm(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(form == null)
		{
			throw new RuntimeException("Unable to retrieve a form from the global property: " + globalPropertyName);
		}
		
		return form;
	}
	
	public Form getFormFromGlobalPropertyValue(String globalProperty, String globalPropertyName)
	{	
		Form form = Context.getFormService().getFormByUuid(globalProperty);
		
		if(form == null)
		{
			form = Context.getFormService().getForm(globalProperty);
		}
		
		if(form == null)
		{
			try{
				form = Context.getFormService().getForm(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(form == null)
		{
			throw new RuntimeException("Unable to retrieve a form from the global property: " + globalPropertyName);
		}
		
		return form;
	}
	
	public EncounterType getEncounterType(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		return getEncounterTypeFromGlobalProperty(globalProperty, globalPropertyName);
		
	}
	
	private EncounterType getEncounterTypeFromGlobalProperty(String globalProperty, String globalPropertyName)
	{
		EncounterType et = Context.getEncounterService().getEncounterTypeByUuid(globalProperty);
		
		if(et == null)
		{
			et = Context.getEncounterService().getEncounterType(globalProperty);
		}
		
		if(et == null)
		{
			try{
				et = Context.getEncounterService().getEncounterType(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(et == null)
		{
			throw new RuntimeException("Unable to retrieve a encounterType from the global property: " + globalPropertyName);
		}
		
		return et;
	}
	
	public List<EncounterType> getEncounterTypeList(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		String[] encounters = globalProperty.split(":");
		if(encounters.length == 1)
		{
			encounters = globalProperty.split(",");
		}
		
		List<EncounterType> encounterTypes=new ArrayList<EncounterType>();
		for(String id:encounters){
			encounterTypes.add(getEncounterTypeFromGlobalProperty(id, id));
		}
		
		return encounterTypes;
	}
	
	public List<Form> getFormList(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		String[] forms = globalProperty.split(",");
		
		List<Form> formTypes = new ArrayList<Form>();
		for(String id:forms){
			formTypes.add(getFormFromGlobalPropertyValue(id, globalPropertyName));
		}
		
		return formTypes;
	}
	
	public RelationshipType getRelationshipType(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		RelationshipType rt = Context.getPersonService().getRelationshipTypeByUuid(globalProperty);
		
		if(rt == null)
		{
			rt =  Context.getPersonService().getRelationshipTypeByName(globalProperty);
		}
		
		if(rt == null)
		{
			try{
				rt =  Context.getPersonService().getRelationshipType(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(rt == null)
		{
			throw new RuntimeException("Unable to retrieve a relationshipType from the global property: " + globalPropertyName);
		}
		
		return rt;
	}
	
	public OrderType getOrderType(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		OrderType ot = Context.getOrderService().getOrderTypeByUuid(globalProperty);
		
		if(ot == null)
		{
			try{
				ot =  Context.getOrderService().getOrderType(Integer.parseInt(globalProperty));
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(ot == null)
		{
			throw new RuntimeException("Unable to retrieve a orderType from the global property: " + globalPropertyName);
		}
		
		return ot;
	}
	
	
	public ProgramWorkflow getProgramWorkflow(String globalPropertyName, String programName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		Program program = this.getProgram(programName);
		
		ProgramWorkflow pw = null;
		
		if(program != null)
		{
			pw = program.getWorkflowByName(globalProperty);
			
			if(pw == null)
			{
				pw = Context.getProgramWorkflowService().getWorkflowByUuid(globalProperty);
			}
			if(pw == null)
			{
				pw = program.getWorkflow(Integer.parseInt(globalProperty));
			}
			
			if(pw == null)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
			
			return pw;
		}
		else
		{
			throw new RuntimeException("Unable to retrieve " + globalPropertyName +" because the global property for the program " + programName + " doesn't resolve to a program");
		}
	}
	
	public ProgramWorkflowState getProgramWorkflowState(String globalPropertyName, String workflowName, String programName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		ProgramWorkflow pw = this.getProgramWorkflow(workflowName, programName);
		
		ProgramWorkflowState pws = null;
		
		if(pw != null)
		{
			pws = pw.getState(globalProperty);
			
			if(pws == null)
			{
				pws = Context.getProgramWorkflowService().getStateByUuid(globalProperty);
			}
			
			if(pws == null)
			{
				try{
					pws = pw.getState(Integer.parseInt(globalProperty));
					
					if(pws == null)
					{
						pws = pw.getState(Context.getConceptService().getConcept(Integer.parseInt(globalProperty)));
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
				}
			}
			
			if(pws == null)
			{
				throw new RuntimeException("Unable to retrieve a programWorkflowState from the global property: " + globalPropertyName);
			}
		}
		
		return pws;
	}
	
	public List<Drug> getDrugs(Concept concept) {                 
	     List<Drug> drugs = Context.getConceptService().getDrugsByConcept(concept);                 
	     return drugs;
	}
	
	
	public List<Concept> getConceptsByConceptSet(String globalPropertyName)
	{
		String globalProperty = Context.getAdministrationService().getGlobalProperty(globalPropertyName);
		
		Concept c = Context.getConceptService().getConceptByUuid(globalProperty);
		List<Concept> concepts=null;
		if(c!= null)
			concepts=Context.getConceptService().getConceptsByConceptSet(c);		
		
		if(c == null && (concepts==null ||concepts.size()==0))
		{
			c = Context.getConceptService().getConceptByName(globalProperty);
			if(c!= null)
			concepts=Context.getConceptService().getConceptsByConceptSet(c);
		}
		
		if(c == null && (concepts==null ||concepts.size()==0))
		{
			try{
				c = Context.getConceptService().getConcept(Integer.parseInt(globalProperty));
				if(c!= null)
				concepts=Context.getConceptService().getConceptsByConceptSet(c);
			}
			catch(Exception e)
			{
				throw new RuntimeException("Unable to convert global property " + globalPropertyName +" to an integer.");
			}
		}
		
		if(c == null)
		{
			throw new RuntimeException("Unable to retrieve a concept from the global property: " + globalPropertyName);
		}
		if(c != null && (concepts==null ||concepts.size()==0))
		{
			throw new RuntimeException("Unable to retrieve a concepts from the global property: " + globalPropertyName+". Check if the concept is a Set of other concepts.");
		}
		
		return concepts;
	}
	

	//Programs
	public final static String ONCOLOGY_PROGRAM = "reports.oncologyprogram";
	
	//ProgramWorkflow
	public final static String TREATMENT_INTENT_WORKFLOW = "reports.treatmentIntentWorkflow";
	
	public final static String DIAGNOSIS_WORKFLOW = "reports.diagnosisWorkflow";
	
	public final static String CHEMOTHERAPY_STATUS_WORKFLOW = "pihrwanda.chemotherapyWorkflow";
	
	public final static String RADIATION_STATUS_WORKFLOW = "pihrwanda.radiationWorkflow";
	
	public final static String SURGERY_STATUS_WORKFLOW = "pihrwanda.surgeryWorkflow";

	public final static String DIAGNOSIS_STATUS_WORKFLOW = "pihrwanda.diagnosisStatusWorkflow";
	
	//ProgramWorkflowState
	public final static String ALL_DIAGNOSIS_STATE = "pihrwanda.ALLDiagnosisState";
	
	//Identifiers
	
	//RelationshipTypes
	public final static String ACCOMPAGNATUER_RELATIONSHIP = "reports.accompagnatuerRelationship";
	
	public final static String MANAGING_DCA = "pihrwanda.managingDCA";
	
	public final static String MANAGING_GP = "pihrwanda.managingGP";
	
	//Concepts
	
	public final static String TELEPHONE_NUMBER_CONCEPT = "reports.telephoneNumberConcept"; 
	
	public final static String WEIGHT_CONCEPT = "reports.weightConcept"; 
	
	public final static String HEIGHT_CONCEPT = "reports.heightConcept";
	
	public final static String DIAGNOSIS_COMMENTS = "pihrwanda.diagnosisComments";
	
	public final static String ALL_STAGING = "pihrwanda.allStaging";
	
	public final static String PH_STATUS = "pihrwanda.phStatus";
	
	public final static String RED_TRANSFUSION = "pihrwanda.redTransfusionUnits";
	
	public final static String WHOLE_TRANSFUSION = "pihrwanda.wholeTransfusionUnits";
	
	public final static String PLTS_TRANSFUSION = "pihrwanda.pltsTransfusionUnits";
	
	public final static String OTHER_TRANSFUSION_TYPE = "pihrwanda.otherTransfusionType";
	
	public final static String OTHER_TRANSFUSION_UNITS = "pihrwanda.otherTransfusionUnits";
	
	public final static String OTHER_TRANSFUSION_CONSTRUCT = "pihrwanda.otherTransfusionConstruct";
	
	//Lab result
	public final static String HB_TEST = "pihrwanda.hb";
	
	public final static String HCT_TEST = "pihrwanda.hct"; 
	
	public final static String WBC_TEST = "pihrwanda.wbc"; 
	
	public final static String NEUTRO_TEST = "pihrwanda.neutro";
	
	public final static String LYMPHO_TEST = "pihrwanda.lympho";
	
	public final static String PLTS_TEST = "pihrwanda.plts"; 
	
	public final static String ESR_TEST = "pihrwanda.esr"; 
	
	public final static String ANC_TEST = "pihrwanda.anc"; 
	
	public final static String NA_TEST = "pihrwanda.na";
	
	public final static String K_TEST = "pihrwanda.k";
	
	public final static String CL_TEST = "pihrwanda.cl";
	
	public final static String CO2_TEST = "pihrwanda.co2";
	
	public final static String UREA_TEST = "pihrwanda.urea";
	
	public final static String CR_TEST = "pihrwanda.cr";
	
	public final static String GLU_TEST = "pihrwanda.glu";
	
	public final static String CA_TEST = "pihrwanda.ca";
	
	public final static String SGOT_TEST = "pihrwanda.sgot"; 
	
	public final static String SGPT_TEST = "pihrwanda.sgpt";
	
	public final static String TBILI_TEST = "pihrwanda.tbili"; 
	
	public final static String DBILI_TEST = "pihrwanda.dbili"; 
	
	public final static String ALKPHOS_TEST = "pihrwanda.alkphos";
	
	public final static String URIC_ACID_TEST = "pihrwanda.uricAcid"; 

}
