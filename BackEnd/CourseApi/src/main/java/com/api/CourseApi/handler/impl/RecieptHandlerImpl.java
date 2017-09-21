package com.api.CourseApi.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.CourseApi.domain.ReceiptDomain;
import com.api.CourseApi.domain.entity.Receipt;
import com.api.CourseApi.dto.ReceiptDTO;
import com.api.CourseApi.handler.RecieptHandler;

public class RecieptHandlerImpl implements RecieptHandler {

	@Autowired(required=true)
	private ReceiptDomain receiptDomain;
	@Override
	public List<Receipt> performGetAllReciept(String errorCodePrefix) {
		List<Receipt> recieptList = new ArrayList<>();
		recieptList = receiptDomain.getAllReciept();
		List<ReceiptDTO> receiptDtos = new ArrayList<>();
		return null;
	}
	
}
