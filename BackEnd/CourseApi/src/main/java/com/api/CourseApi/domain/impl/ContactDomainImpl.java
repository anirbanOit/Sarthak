package com.api.CourseApi.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.CourseApi.domain.ContactDomain;
import com.api.CourseApi.domain.entity.Contact;
import com.api.CourseApi.domain.repo.ContactRepository;
import com.api.CourseApi.dto.StatusDTO;

@Service("contactDomain")
public class ContactDomainImpl implements ContactDomain {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public StatusDTO addContact(Contact contact) {
		StatusDTO statusDto = new StatusDTO();
		contactRepository.save(contact);
		statusDto.setStatus("Contact Successfully Created");
		return statusDto;
	}

	@Override
	public StatusDTO deleteContact(int id) {
		
		
		StatusDTO statusDto = new StatusDTO();
		contactRepository.delete(id);
		statusDto.setStatus("Contact Successfully Deleted");
		
		return statusDto;
	}

	@Override
	public Contact getContactById(int id) {
		return contactRepository.findOne(id);
	}

}
