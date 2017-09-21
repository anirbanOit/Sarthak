package com.api.CourseApi.exception;

import com.api.CourseApi.exception.base.AppBusinessException;
import com.api.CourseApi.exception.helper.ExceptionDetail;
import com.api.CourseApi.utils.constant.AppConstant;

public class BadRequestException extends AppBusinessException {
	private static final long serialVersionUID = 5951927527132322535L;

	/**
	 * 
	 * @param exceptionDetail
	 */
	public BadRequestException(final ExceptionDetail exceptionDetail) {
		super(exceptionDetail, AppConstant.HTTP_STATUS_BAD_REQUEST);
	}
}
