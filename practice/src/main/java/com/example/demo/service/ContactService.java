package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {

	void saveContact(ContactForm contactForm);

	List<Contact> getAllContacts();
	
	Contact getContactById(Long id);
	
	void updateContact(Long id, Contact updatedContact);
	
	void deleteContact(Long id);

}