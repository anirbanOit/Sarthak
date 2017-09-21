package com.api.CourseApi.feature.bean;

import com.api.CourseApi.dao.AddBody;
import com.api.CourseApi.message.response.AbstractResponse;

public interface ContactBean {
	
	/**
	 * 
	 * @param accessorId
	 * @param sessionToken
	 * @return AbstractResponse - Get all List of Users
	 */
	AbstractResponse processGetAllContactProfile(String accessorId, String sessionToken);

	/**
	 * 
	 * @param addBody
	 * @param sessionToken
	 * @return
	 */
	AbstractResponse processAddContact(AddBody addBody, String sessionToken);

	/**
	 * 
	 * @param id
	 * @param sessionToken
	 * @return
	 */
	AbstractResponse processDeleteContact(int id, String sessionToken);
}
