package com.api.CourseApi.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.CourseApi.dao.AddBody;
import com.api.CourseApi.domain.ContactDomain;
import com.api.CourseApi.domain.entity.Contact;
import com.api.CourseApi.dto.ContactDTO;
import com.api.CourseApi.dto.StatusDTO;
import com.api.CourseApi.exception.NotFoundException;
import com.api.CourseApi.exception.helper.ErrorCause;
import com.api.CourseApi.exception.helper.ResponseCode;
import com.api.CourseApi.handler.ContactHandler;

@Service("contactHandler")
public class ContactHandlerImpl implements ContactHandler {

	@Autowired(required = true)
	private ContactDomain contactDomain;

	@Override
	public List<ContactDTO> performGetContactDetail(String errorCodePrefix) {
		
		List<Contact> contactList = contactDomain.getAllContacts();
		List<ContactDTO> contactDtos = new ArrayList<>();
		
		if (contactList == null || contactList.isEmpty()) {
			com.api.CourseApi.exception.helper.ExceptionDetail exceptionDetail = new com.api.CourseApi.exception.helper.ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.NOT_FOUND,
					ErrorCause.NO_DATA_FOUND);

			throw new NotFoundException(exceptionDetail);
		}
		
		for (Contact contact : contactList) {
			ContactDTO contactDTO = new ContactDTO();
			contactDTO.setId(contact.getId());
			contactDTO.setName(contact.getName());
			contactDTO.setPhoneNo(contact.getPhoneNo());
			contactDtos.add(contactDTO);
		}
		return contactDtos;
	}

	@Override
	public StatusDTO performAddContact(AddBody addBody, String errorCodePrefix) {
		Contact contact = new Contact();
		contact.setName(addBody.getName());
		contact.setPhoneNo(addBody.getPhoneNo());
		
		StatusDTO statusDto = new StatusDTO();
		statusDto = contactDomain.addContact(contact);
		return statusDto;
	}

	@Override
	public StatusDTO performDeleteContact(int id, String errorCodePrefix) {
		Contact contact = new Contact();
		contact = contactDomain.getContactById(id);
		if (contact==null) {
			com.api.CourseApi.exception.helper.ExceptionDetail exceptionDetail = new com.api.CourseApi.exception.helper.ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.NOT_FOUND,
					ErrorCause.NO_DATA_FOUND);

			throw new NotFoundException(exceptionDetail);
		}
		StatusDTO statusDto = new StatusDTO();
		statusDto = contactDomain.deleteContact(id);
		return statusDto;
	}

	

	
	
	
}
