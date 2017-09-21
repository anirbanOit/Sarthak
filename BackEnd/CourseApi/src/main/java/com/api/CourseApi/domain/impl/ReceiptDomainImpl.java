package com.api.CourseApi.domain.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.CourseApi.domain.ReceiptDomain;
import com.api.CourseApi.domain.entity.Receipt;
import com.api.CourseApi.domain.repo.RecieptRepository;

@Service("recieptDomain")
public class ReceiptDomainImpl implements ReceiptDomain {
	RecieptRepository recieptRepo;
	@Override
	public List<Receipt> getAllReciept() {
		return recieptRepo.findAll();
	}
	
}
