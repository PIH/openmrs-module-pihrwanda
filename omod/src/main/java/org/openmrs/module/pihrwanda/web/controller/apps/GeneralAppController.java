package org.openmrs.module.pihrwanda.web.controller.apps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralAppController {
    
    @RequestMapping("/module/pihrwanda/apps/general/index.form") 
    public void viewIndex(ModelMap model) {
    	
    }
}
