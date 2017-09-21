package com.api.CourseApi.handler;

import java.util.List;

import com.api.CourseApi.domain.entity.Receipt;

public interface RecieptHandler {
	/**
	 * 
	 * @param errorCodePrefix
	 * @return List<Receipt>
	 */
	List<Receipt> performGetAllReciept(String errorCodePrefix);
}
