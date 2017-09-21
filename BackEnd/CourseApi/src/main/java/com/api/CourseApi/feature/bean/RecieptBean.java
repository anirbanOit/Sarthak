package com.api.CourseApi.feature.bean;

import com.api.CourseApi.message.response.AbstractResponse;

public interface RecieptBean {
	AbstractResponse processGetAllReciept(String accessorId, String sessionToken, int contactId);
}
