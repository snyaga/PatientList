package org.openmrs.module.basicexample.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by susan on 5/13/15.
 */
public class PatientDetails {
    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/basicexample/manage", method = RequestMethod.GET)
    public void manage(ModelMap model) {
        //model.addAttribute("user", Context.getAuthenticatedUser());
        List<Patient> allPatients = Context.getPatientService().getAllPatients();
        model.addAttribute("patients", allPatients);
    }
}

