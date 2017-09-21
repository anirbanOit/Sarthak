package com.api.CourseApi.message.response;

import com.api.CourseApi.exception.helper.ExceptionDetail;
import com.api.CourseApi.utils.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericErrorResponse extends AbstractResponse {
	@JsonIgnore
	@JsonProperty("reason")
	private int reason;

	@JsonProperty("error_details")
	private ExceptionDetail exceptionDetail;
	
	public GenericErrorResponse() {
		super();
	}

	public GenericErrorResponse(final String statusCode, final String statusMessage) {
		super.setStatus(AppConstant.API_STATUS_FAILED);
		super.setStatusCode(statusCode);
		super.setStatusMessage(statusMessage);
	}
	
	public GenericErrorResponse(final ExceptionDetail exceptionDetail, final int httpStatusCode) {
		this.exceptionDetail = exceptionDetail;

		super.setStatus(AppConstant.API_STATUS_FAILED);
		super.setStatusCode(exceptionDetail.getFinalErrorCode());
		super.setStatusMessage(exceptionDetail.getMessage());
		
		this.reason = httpStatusCode;
	}
}
