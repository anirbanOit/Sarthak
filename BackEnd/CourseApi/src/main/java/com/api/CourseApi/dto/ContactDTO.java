package com.api.CourseApi.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDTO {

	@JsonProperty("id")
	private int id;

	@NotNull(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~NAME")
	@NotEmpty(message = "GENERIC_BUSINESS~MANDATORY_PARAMETER~NAME")
	@JsonProperty("name")
	private String name;

	@JsonProperty("phoneNo")
	private String phoneNo;

	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * 
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
