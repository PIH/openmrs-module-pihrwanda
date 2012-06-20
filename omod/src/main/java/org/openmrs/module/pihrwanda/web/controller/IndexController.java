package org.openmrs.module.pihrwanda.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.pihrwanda.web.App;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    @RequestMapping("/index.htm") 
    public String viewIndex(ModelMap model, HttpSession session) {
    	
    	session.removeAttribute(App.SELECTED_APP_SESSION_KEY);
    	
    	User currentUser = Context.getAuthenticatedUser();
    	if (currentUser == null) {
    		return "redirect:/login.htm";
    	}
    	
    	List<App> apps = new ArrayList<App>();
    	for (App app : App.values()) {
    		if (app.canAccess(currentUser)) {
    			apps.add(app);
    		}
    	}
    	
    	if (apps.size() == 1) {
    		App app = apps.get(0);
    		session.setAttribute(App.SELECTED_APP_SESSION_KEY, app);
    		return "redirect:"+app.getIndexUrl();
    	}
    	else {
        	model.addAttribute("apps", apps);   		
    	}
    	return null;
    }
}
