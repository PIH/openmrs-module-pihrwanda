package org.openmrs.module.pihrwanda.web.controller.apps;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openmrs.DrugOrder;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ModuleFactory;
import org.openmrs.module.orderextension.DrugRegimen;
import org.openmrs.module.orderextension.ExtendedDrugOrder;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OncologyAppController {

    @RequestMapping("/module/pihrwanda/apps/oncology/index.form") 
    public void viewIndex(ModelMap model) {
    	
    	ReportDefinition patientList = findReportDefinition("Chemotherapy Expected Patient List");
    	
    	List<ReportDefinition> reports = new ArrayList<ReportDefinition>();
    	
    	reports.add(patientList);
    	
    	model.addAttribute("showAnalysis", ModuleFactory.getStartedModulesMap().containsKey("reportingcompatibility"));
    	model.addAttribute("reports", reports);
    }
    
    @RequestMapping("/module/pihrwanda/apps/oncology/patientDashboard.form") 
    public void viewPatientDashboard(@RequestParam(required = true, value = "patientId") Integer patientId, ModelMap model) {
    
    	Patient patient = Context.getPatientService().getPatient(patientId);

		List<DrugOrder> allDrugOrders = Context.getOrderService().getDrugOrdersByPatient(patient);
		List<DrugRegimen> cycles = new ArrayList<DrugRegimen>();
		
		Calendar compareDate = Calendar.getInstance();
		compareDate.add(Calendar.DAY_OF_YEAR, -7);
		
		for(DrugOrder drugOrder : allDrugOrders)
		{
			if (drugOrder instanceof ExtendedDrugOrder) {
				ExtendedDrugOrder edo = (ExtendedDrugOrder)drugOrder;
				if(edo.getGroup() != null && edo.getGroup() instanceof DrugRegimen) {
					DrugRegimen regimen = (DrugRegimen)edo.getGroup();
					if (regimen.isCyclical() && !cycles.contains(regimen) && regimen.getFirstDrugOrderStartDate().after(compareDate.getTime()))
					{
						cycles.add(regimen);
					}
				}
			}
		}
		
		if(cycles.size() > 0)
		{
			model.addAttribute("cycles", cycles);
		}
    	model.addAttribute("patient", patient);
    }
    
    private ReportDefinition findReportDefinition(String name) {
		ReportDefinitionService s = (ReportDefinitionService) Context.getService(ReportDefinitionService.class);
		List<ReportDefinition> defs = s.getDefinitions(name, true);
		for (ReportDefinition def : defs) {
			return def;
		}
		throw new RuntimeException("Couldn't find Definition " + name);
	}
}
