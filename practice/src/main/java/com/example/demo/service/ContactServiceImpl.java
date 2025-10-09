package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();

		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());

		contactRepository.save(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}
	
	@Override
	public Contact getContactById(Long id) {
		Optional<Contact> contactOptional = contactRepository.findById(id);
		if (contactOptional.isPresent()) {
			return contactOptional.get();
		}
		return null;
	}
	
	@Override
	public void updateContact(Long id, Contact updatedContact) {
	    Contact contact = contactRepository.findById(id).orElse(null);
	    if (contact != null) {
	        contact.setLastName(updatedContact.getLastName());
	        contact.setFirstName(updatedContact.getFirstName());
	        contact.setEmail(updatedContact.getEmail());
	        contact.setPhone(updatedContact.getPhone());
	        contact.setZipCode(updatedContact.getZipCode());
	        contact.setAddress(updatedContact.getAddress());
	        contact.setBuildingName(updatedContact.getBuildingName());
	        contact.setContactType(updatedContact.getContactType());
	        contact.setBody(updatedContact.getBody());

	        contactRepository.save(contact);
	    }
	}

	@Override
	public void deleteContact(Long id) {
	    contactRepository.deleteById(id);
	}
	
}
