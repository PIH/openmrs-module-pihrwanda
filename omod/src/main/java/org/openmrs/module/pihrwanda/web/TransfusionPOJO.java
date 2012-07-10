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

import java.util.Date;


/**
 *
 */
public class TransfusionPOJO {
	
	String transfusionType = "";
	
	Double units;
	
	Date date;

	
    public String getTransfusionType() {
    	return transfusionType;
    }

	
    public void setTransfusionType(String tranfusionType) {
    	this.transfusionType = tranfusionType;
    }

	
    public Double getUnits() {
    	return units;
    }

	
    public void setUnits(Double units) {
    	this.units = units;
    }
	
    public Date getDate() {
    	return date;
    }

    public void setDate(Date date) {
    	this.date = date;
    }
}
