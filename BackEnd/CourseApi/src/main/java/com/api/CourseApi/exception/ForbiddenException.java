package com.api.CourseApi.exception;

import com.api.CourseApi.exception.base.AppBusinessException;
import com.api.CourseApi.exception.helper.ExceptionDetail;
import com.api.CourseApi.utils.constant.AppConstant;

public class ForbiddenException extends AppBusinessException {
	private static final long serialVersionUID = 5951927527132322535L;

	/**
	 * 
	 * @param exceptionDetail
	 */
	public ForbiddenException(final ExceptionDetail exceptionDetail) {
		super(exceptionDetail, AppConstant.HTTP_STATUS_FORBIDDEN);
	}
}
