package com.api.CourseApi.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiptDTO {
	
	@NotNull
	@JsonProperty("id")
	private int id;
	
	@NotEmpty
	@NotNull
	@JsonProperty("contactId")
	private int contactId;
	
	@NotEmpty
	@NotNull
	@JsonProperty("receiptType")
	private String receiptType;
	
	@NotEmpty
	@NotNull
	@JsonProperty("receiptAmount")
	private String receiptAmount;
	
	@NotEmpty
	@NotNull
	@JsonProperty("receiptDate")
	private String receiptDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public String getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	
	
}
