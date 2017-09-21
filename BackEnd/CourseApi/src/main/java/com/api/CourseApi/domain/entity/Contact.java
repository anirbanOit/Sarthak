package com.api.CourseApi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="contact")
public class Contact {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	@Column(name="id", nullable=false, updatable=false)
	private int id;
	
	@JsonProperty("name")
	@Column(name="name",  updatable=true)
	private String name;
	
	@JsonProperty("phoneNo")
	@Column(name="phone_no",  updatable=true)
	private String phoneNo;

	public Contact() {
		super();
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param phoneNo
	 */

	public Contact(int id, String name, String phoneNo) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
