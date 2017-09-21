package com.api.CourseApi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusDTO {
	
	@JsonProperty("status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
