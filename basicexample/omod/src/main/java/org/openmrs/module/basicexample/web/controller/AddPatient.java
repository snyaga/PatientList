package org.openmrs.module.basicexample.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.basicexample.BasicExample;
import org.openmrs.module.basicexample.api.BasicExampleService;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by susan on 5/17/15.
 */
@Controller
public class AddPatient {
    protected final Log log = LogFactory.getLog(getClass());

  /*  @RequestMapping(value = "/module/basicexample/addpatient",method= RequestMethod.GET)
    public void addpatient(ModelMap model) {

        BasicExampleService basicExampleService = Context.getService(BasicExampleService.class);
        model.addAttribute("user", Context.getAuthenticatedUser());
        List<Patient> allPatients = Context.getPatientService().getAllPatients();
        model.addAttribute("patients", allPatients);
        BasicExample addPatient=new BasicExample();
        model.addAttribute("addpatient", addPatient);

    }*/


    @RequestMapping(value = "/module/basicexample/addpatient", method = RequestMethod.POST)
    public void addpatient(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "middleName", required = false) String middleName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "dateOfBirth", required = false) Date dateOfBirth,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "address", required = false) String address1)
    {


        Patient patient = new Patient();
        PersonName name = new PersonName;
        PersonAddress address = new PersonAddress;
        name.setGivenName(firstName);
        name.setMiddleName(middleName);
        name.setFamilyName(lastName);
        patient.setBirthdate(dateOfBirth);
        patient.setGender(gender);
        address.setAddress1(address1);

    }

}

