package com.nt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.domain.Contact;
import com.nt.service.ContactInfoService;

/**
 * for handling viewContact.jsp page
 * 
 * @author Shekhar
 *
 */

@Controller
public class ViewContactController {
	@Autowired
	ContactInfoService cntctService;

	/**
	 * edit the contacts
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("/editContact")
	public String editContact(HttpServletRequest req, Model model) {
		int cid = Integer.parseInt(req.getParameter("contact_Id"));
		Contact contactById = cntctService.getContactById(cid);
		model.addAttribute("contactInfo", contactById);
		model.addAttribute("editMsg", "sucessfully contact edited");
		return "contact";
	}

	/**
	 * for deleting the contacts
	 * 
	 * @param req
	 * @return
	 */

	@RequestMapping("/deleteContact")
	public String deleteContact(HttpServletRequest req) {

		Integer id = Integer.parseInt(req.getParameter("activeSw"));
		cntctService.deleteContactById(id);
		return "redirect:/getAllContact";
	}

}
