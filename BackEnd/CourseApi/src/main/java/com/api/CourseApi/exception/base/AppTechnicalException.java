package com.api.CourseApi.exception.base;

import com.api.CourseApi.exception.helper.ExceptionDetail;

public abstract class AppTechnicalException extends AppException {
	private static final long serialVersionUID = -6538006142999778827L;

	/**
	 * 
	 * @param exceptionDetail
	 * @param httpStatusCode
	 */
	public AppTechnicalException(final ExceptionDetail exceptionDetail, final int httpStatusCode) {
		super(exceptionDetail, httpStatusCode);
	}
}
