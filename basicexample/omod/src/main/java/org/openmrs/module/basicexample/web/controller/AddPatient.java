package org.openmrs.module.basicexample.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
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
import java.util.List;

/**
 * Created by susan on 5/17/15.
 */
@Controller
public class AddPatient {
    protected final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "/module/basicexample/addpatient",method= RequestMethod.GET)
    public void addpatient(ModelMap model) {

        BasicExampleService basicExampleService = Context.getService(BasicExampleService.class);
        model.addAttribute("user", Context.getAuthenticatedUser());
        List<Patient> allPatients = Context.getPatientService().getAllPatients();
        model.addAttribute("patients", allPatients);
        BasicExample addPatient=new BasicExample();
        model.addAttribute("addpatient", addPatient);

    }


    @RequestMapping(value = "/module/basicexample/add.form", method = RequestMethod.POST)
    public String addpatient(WebRequest request, HttpSession httpSession, ModelMap model,
                             @RequestParam(required = false, value = "action") String action,
                             @ModelAttribute("addpatient") BasicExample addPatient, BindingResult errors) {

        MessageSourceService mss = Context.getMessageSourceService();
        //model.addAttribute("hello", "");
        BasicExampleService basicExampleService = Context.getService(BasicExampleService.class);
        if (!Context.isAuthenticated()) {
            errors.reject("addpatient.auth.required");

        } else if (mss.getMessage("addpatient.purgeBasicExample").equals(action)) {
            try {

                basicExampleService.purgeBasicExample(addPatient);
                httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Patient Deleted successfully");
                return "redirect:addpatientlist.list";
            }
            catch (Exception ex) {

                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Delete failure");
                log.error("Failed to delete patientModule", ex);
                return "redirect:addpatientform.form?nationalId=" + request.getParameter("nationalId");
            }
        } else {

            basicExampleService.savePatient(addPatient);
            httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Added Successfully");
        }
        return "redirect:addpatient.form";
    }
}

