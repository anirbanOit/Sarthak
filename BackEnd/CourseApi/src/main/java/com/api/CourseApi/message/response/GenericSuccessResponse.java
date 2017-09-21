package com.api.CourseApi.message.response;

import com.api.CourseApi.utils.constant.AppConstant;

public class GenericSuccessResponse extends AbstractResponse {

	public GenericSuccessResponse() {
		super.setStatus(AppConstant.API_STATUS_OK);
		super.setStatusCode(AppConstant.GENERIC_SUCCESS_CODE);
		super.setStatusMessage(AppConstant.API_STATUS_OK);
	}

	/**
	 * @param statusMessage
	 */
	public GenericSuccessResponse(final String statusMessage) {
		super.setStatus(AppConstant.API_STATUS_OK);
		super.setStatusCode(AppConstant.GENERIC_SUCCESS_CODE);
		super.setStatusMessage(statusMessage);
	}
}
