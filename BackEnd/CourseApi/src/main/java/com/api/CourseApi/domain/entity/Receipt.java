package com.api.CourseApi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="receipt")
public class Receipt {
	
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable=false, updatable=false)
	@JsonProperty("id")
	private int receiptId;
	
	@JsonProperty("contactId")
	@Column(name= "contactId", updatable= false)
	private int contactId;
	
	@JsonProperty("receiptType")
	@Column(name= "receiptType", updatable= true)
    private String receiptType;
	
	@JsonProperty("receiptAmount")
	@Column(name= "receiptAmount", updatable= true)
    private String receiptAmount;
	
	@JsonProperty("receiptDate")
	@Column(name= "receiptDate", updatable= true )
    private String receiptDate;

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
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
