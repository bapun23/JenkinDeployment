package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.constant.ContactDetailsConstant;
import com.nt.domain.Contact;
import com.nt.service.ContactInfoService;

/**
 * 
 * @author Shekhar for handling Contact.jsp page
 *
 */
@Controller
public class ContactInfoController {
	@Autowired
	private ContactInfoService cntctService;

	/**
	 * method for launching form page.....
	 *
	 */

	@RequestMapping("/")
	public String displayContactForm(Model model) {
		Contact c = new Contact();
		model.addAttribute("contactInfo", c);
		return "contact";
	}

	/**
	 * mathod for adding contact details
	 * 
	 * @param inputBean
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/submitInfo", method = RequestMethod.POST)
	public String handleSubmit(@ModelAttribute Contact inputBean, Model model, RedirectAttributes redAtt) {
		boolean flag = cntctService.saveContacts(inputBean);
		if (flag)
			redAtt.addFlashAttribute("succMsg", "successfully contact added");
		else
			redAtt.addFlashAttribute("failMsg", "Not successfully contact added");
		return "redirect:/invalidSubmit";

	}

	/**
	 * to prevent double posting
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/invalidSubmit", method = RequestMethod.GET)
	public String handleInvalidSubmit(Model model) {
		Contact c = new Contact();
		model.addAttribute("contactInfo", c);
		return ContactDetailsConstant.CONTACTSTR;
	}

	/**
	 * Fetching All contacts
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping("/getAllContact")
	public String viewContacts(Model model) {
		List<Contact> listContact = cntctService.displayAllContacts();
		model.addAttribute("allCntct", listContact);
		return ContactDetailsConstant.CONTACTVIEWSTR;
	}
}
