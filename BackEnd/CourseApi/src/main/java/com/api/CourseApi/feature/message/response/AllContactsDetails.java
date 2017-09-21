package com.api.CourseApi.feature.message.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.api.CourseApi.dto.ContactDTO;
import com.api.CourseApi.message.response.GenericSuccessResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AllContactsDetails extends GenericSuccessResponse{
	
	@NotNull
	@JsonProperty("result")
	private List<ContactDTO> contactDTO;
	
	/**
	 * 
	 * @return List<ContactDTO>
	 */

	public List<ContactDTO> getContactDTO() {
		return contactDTO;
	}
	
	/**
	 * 
	 * @param contactDTO
	 */
	public void setContactDTO(List<ContactDTO> contactDTO) {
		this.contactDTO = contactDTO;
	}
	
	
	
}
