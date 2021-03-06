package com.api.CourseApi.handler.impl;

import org.springframework.stereotype.Service;

import com.api.CourseApi.exception.BadRequestException;
import com.api.CourseApi.exception.SessionException;
import com.api.CourseApi.exception.helper.ErrorCause;
import com.api.CourseApi.exception.helper.ExceptionDetail;
import com.api.CourseApi.exception.helper.ResponseCode;
import com.api.CourseApi.handler.RequestValidator;
import com.api.CourseApi.utils.constant.AppConstant;

@Service("requestValidator")
public class RequestValidatorImpl implements RequestValidator {

	private void throwException(final String exceptionType, final String errorCodePrefix,
			final ResponseCode responseCode, final ErrorCause errorCause) {
		ExceptionDetail exceptionDetail = new ExceptionDetail(AppConstant.USER_ID_NONE, AppConstant.USER_NAME_NONE,
				AppConstant.IMPACTED_USER_ID_NONE, errorCodePrefix, responseCode, errorCause);

		if (exceptionType == AppConstant.SESSION_EXCEPTION) {
			throw new SessionException(exceptionDetail);
		}
		if (exceptionType == AppConstant.BAD_REQUEST_EXCEPTION) {
			throw new BadRequestException(exceptionDetail);
		}
	}

	@Override
	public void validateAuthToken(final String token, final String parentMethod, final String errorCodePrefix) {
		if (!token.equals(AppConstant.TOKEN)) {
			throwException(AppConstant.SESSION_EXCEPTION, errorCodePrefix, ResponseCode.FORBIDDEN,
					ErrorCause.SESSION_TOKEN);
		}
	}

	@Override
	public void validateEMRId(final String emrIdStr, final String errorCodePrefix) {

		if (emrIdStr == null || emrIdStr.isEmpty()) {
			throwException(AppConstant.BAD_REQUEST_EXCEPTION, errorCodePrefix, ResponseCode.MANDATORY_PARAMETER,
					ErrorCause.ADMIN_ID);
		}

		long emrId = Long.parseLong(emrIdStr);

		if (emrId <= 0) {
			throwException(AppConstant.BAD_REQUEST_EXCEPTION, errorCodePrefix, ResponseCode.INVALID_VALUE,
					ErrorCause.ADMIN_ID);
		}
	}
}
