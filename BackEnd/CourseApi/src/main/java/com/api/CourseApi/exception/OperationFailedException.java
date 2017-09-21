package com.api.CourseApi.exception;

import com.api.CourseApi.exception.base.AppTechnicalException;
import com.api.CourseApi.exception.helper.ExceptionDetail;
import com.api.CourseApi.utils.constant.AppConstant;

public class OperationFailedException extends AppTechnicalException {
	private static final long serialVersionUID = -6538006142999778827L;

	/**
	 * 
	 * @param exceptionDetail
	 */
	public OperationFailedException(final ExceptionDetail exceptionDetail) {
		super(exceptionDetail, AppConstant.HTTP_STATUS_INTERNAL_SERVER_ERROR);
	}
}
