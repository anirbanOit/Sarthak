package com.api.CourseApi.domain;

import java.util.List;

import com.api.CourseApi.domain.entity.Contact;
import com.api.CourseApi.dto.StatusDTO;

public interface ContactDomain {
	/**
	 * 
	 * @return List<Contact>
	 */
	List<Contact> getAllContacts();
	Contact getContactById(int id);
	StatusDTO addContact(Contact contact);
	StatusDTO deleteContact(int id);
}
