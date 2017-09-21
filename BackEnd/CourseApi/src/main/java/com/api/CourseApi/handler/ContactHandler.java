package com.api.CourseApi.handler;

import java.util.List;

import com.api.CourseApi.dao.AddBody;
import com.api.CourseApi.dto.ContactDTO;
import com.api.CourseApi.dto.StatusDTO;



public interface ContactHandler {
	
	/**
	 * 
	 * @param errorCodePrefix
	 * @return List<ContactDTO>
	 */
	List<ContactDTO> performGetContactDetail(String errorCodePrefix);

	StatusDTO performAddContact(AddBody addBody, String errorCodePrefix);

	StatusDTO performDeleteContact(int id, String errorCodePrefix);
}
