package com.api.CourseApi.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBody {

	@JsonProperty("name")
	private String name;

	@JsonProperty("phoneNo")
	private String phoneNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
