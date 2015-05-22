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
package org.openmrs.module.patientdetails.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.validator.PatientIdentifierValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * The main controller.
 */
@Controller
public class  PatientDetailsManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/patientdetails/manage.form", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
		List<Patient> allPatients = Context.getPatientService().getAllPatients();
		model.addAttribute("patients", allPatients);
	}

	@RequestMapping(value = "/module/patientdetails/registration.form", method = RequestMethod.GET)
	public void displayForm(ModelMap map){
		map.addAttribute("form", "");

	}

	@RequestMapping(value = "/module/patientdetails/addpatient.form", method = RequestMethod.POST)
	public String addpatient(ModelMap model,
			@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "middleName", required = true) String middleName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "dateOfBirth", required = true) Date dateOfBirth,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "nationalId", required = true) String nationalId){


		PatientService patientService=Context.getPatientService();

		Patient patient = new Patient();
		PersonName personName = new PersonName();
		PersonAddress personAddress = new PersonAddress();

		personName.setGivenName(firstName);
		personName.setMiddleName(middleName);
		personName.setFamilyName(lastName);

		patient.addName(personName);

		patient.setBirthdate(dateOfBirth);
		patient.setGender(gender);
		personAddress.setAddress1(address);

		patient.addAddress(personAddress);
		//patientService.savePatient(patient);

		PatientIdentifier patientId = new PatientIdentifier();

		String TARGET_ID_KEY = "patientdetails.idType";
		String TARGET_ID = Context.getAdministrationService().getGlobalProperty(TARGET_ID_KEY);

		PatientIdentifierType patientIdentifierType = patientService.getPatientIdentifierTypeByName(TARGET_ID);

		patientId.setIdentifier(nationalId);
		patientId.setDateCreated(new Date());
		patientId.setLocation(Context.getLocationService().getDefaultLocation());
		patientId.setIdentifierType(patientIdentifierType);


		PatientIdentifierValidator.validateIdentifier(patientId);
		patient.addIdentifier(patientId);

		if (!patientService.isIdentifierInUseByAnotherPatient(patientId)) {
			patientService.savePatient(patient);
			model.addAttribute("save_success","Patient successfully saved");
			return "redirect:registration.form";
		}else {
			model.addAttribute("save_failed","Patient not saved : duplicate id number");
			return "redirect:registration.form";
		}
	}

}




