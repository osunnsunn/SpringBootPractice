package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactEditForm;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/admin/contacts")
	public String contactList(Model model) {
		List<Contact> contactList = contactService.getAllContacts();
		model.addAttribute("contactList", contactList);
		return "contactList";
	}
	
	@GetMapping("/admin/contacts/{id}")
	public String contactDetail(@PathVariable("id") Long id, Model model) {
	    Contact contact = contactService.getContactById(id);
	    if (contact == null) {
	        return "redirect:/admin/contacts";
	    }
	    model.addAttribute("contact", contact);
	    return "contactDetail";
	}
	
	@GetMapping("/admin/contacts/{id}/edit")
	public String editContact(@PathVariable("id") Long id, Model model) {
	    Contact contact = contactService.getContactById(id);
	    if (contact == null) {
	        return "redirect:/admin/contacts";
	    }
	    model.addAttribute("contact", contact);
	    return "contactEdit";
	}
	
	@PostMapping("/admin/contacts/{id}/edit")
	public String updateContact(@PathVariable("id") Long id,
	                            @Validated @ModelAttribute ContactEditForm form,
	                            BindingResult result) {
	    if (result.hasErrors()) {
	        return "contactEdit";
	    }
	    contactService.updateContact(id, form);
	    return "redirect:/admin/contacts/" + id;
	}
	
	@GetMapping("/admin/contacts/{id}/delete")
	public String deleteContact(@PathVariable("id") Long id) {
	    contactService.deleteContact(id);
	    return "redirect:/admin/contacts";
	}

}