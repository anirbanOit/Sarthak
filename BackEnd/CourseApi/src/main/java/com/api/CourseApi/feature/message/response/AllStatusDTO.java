package com.api.CourseApi.feature.message.response;

import javax.validation.constraints.NotNull;

import com.api.CourseApi.dto.StatusDTO;
import com.api.CourseApi.message.response.GenericSuccessResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AllStatusDTO extends GenericSuccessResponse {
	
	@NotNull
	@JsonProperty("result")
	private StatusDTO statusDto;

	public StatusDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(StatusDTO statusDto) {
		this.statusDto = statusDto;
	}
}
