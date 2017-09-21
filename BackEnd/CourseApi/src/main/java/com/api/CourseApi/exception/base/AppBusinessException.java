package com.api.CourseApi.exception.base;

import com.api.CourseApi.exception.helper.ExceptionDetail;

public abstract class AppBusinessException extends AppException {
	private static final long serialVersionUID = 5951927527132322535L;

	/**
	 * 
	 * @param exceptionDetail
	 * @param httpStatusCode
	 */
	public AppBusinessException(final ExceptionDetail exceptionDetail, final int httpStatusCode) {
		super(exceptionDetail, httpStatusCode);
	}
}
