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
package org.openmrs.module.pihrwanda.web;

import org.openmrs.User;

/**
 * Represents all of the supported clinics/services that
 * appear as separate applications within the system
 */
public enum App {
	
	OUTPATIENT (
		"outpatient", 
		"/module/rwandaprimarycare/homepage.form", 
		"/module/rwandaprimarycare/patient.form",
		"Application Access - Outpatient"
	),
	CHW (
		"chw", 
		"/pages/providerHome.page", 
		"/patientDashboard.form",
		"Application Access - CHW"
	),
	LAB (
		"lab", 
		"/module/simplelabentry/index.htm", 
		"/patientDashboard.form",
		"Application Access - Lab"
	),
	GENERAL (
		"general", 
		"/module/pihrwanda/apps/general/index.form", 
		"/patientDashboard.form",
		"Application Access - General"
	),
	ADMINISTRATION (
		"administration", 
		"/admin/index.htm", 
		"/patientDashboard.form",
		"Application Access - Administration"
	);
	
	App(String name, String indexUrl, String patientDashboardUrl, String requiredPrivilege) {
		this.name = name;
		this.indexUrl = indexUrl;
		this.patientDashboardUrl = patientDashboardUrl;
		this.requiredPrivilege = requiredPrivilege;
	}
	
	private String name;
	private String indexUrl;
	private String patientDashboardUrl;
	private String requiredPrivilege;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the indexUrl
	 */
	public String getIndexUrl() {
		return indexUrl;
	}
	
	/**
	 * @return the patientDashboardUrl
	 */
	public String getPatientDashboardUrl() {
		return patientDashboardUrl;
	}
	
	/**
	 * @return the requiredPrivilege
	 */
	public String getRequiredPrivilege() {
		return requiredPrivilege;
	}

	/**
	 * @return whether the passed user can access the application
	 */
	public boolean canAccess(User u) {
		return u.hasPrivilege(requiredPrivilege);
	}
	
	public static final String SELECTED_APP_SESSION_KEY = "pihrwanda_selected_app";
	
	/**
	 * @return the App that has the passed name
	 */
	public static final App lookupByName(String name) {
		for (App a : App.values()) {
			if (a.getName().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}
}
