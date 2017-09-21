package com.api.CourseApi.feature.bean.impl;



import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.CourseApi.dao.AddBody;
import com.api.CourseApi.dto.ContactDTO;
import com.api.CourseApi.dto.StatusDTO;
import com.api.CourseApi.feature.bean.ContactBean;
import com.api.CourseApi.feature.message.response.AllContactsDetails;
import com.api.CourseApi.feature.message.response.AllStatusDTO;
import com.api.CourseApi.handler.ContactHandler;
import com.api.CourseApi.handler.RequestValidator;
import com.api.CourseApi.message.response.AbstractResponse;
import com.api.CourseApi.utils.log.AppLog;

@Service("userBean")
public class ContactBeanImpl implements ContactBean {
	
	@Autowired(required = true)
	private ContactHandler contactHandler;

	@Autowired(required = true)
	private RequestValidator requestValidator;

	private final AppLog appLog;

	@Inject
	public ContactBeanImpl() {
		appLog = AppLog.getInstance(getClass());
	}

	@Override
	public AbstractResponse processGetAllContactProfile(final String accessorId, final String sessionToken) {
		final String errorCodePrefix = com.api.CourseApi.utils.APIGroupCode.EMPLOYEE_GET_ALL_USERS.getGroupCode()
				+ com.api.CourseApi.utils.APIGroupCode.EMPLOYEE_GET_ALL_USERS.getApiCode() + "000";

		String beanServiceName = "processGetAllContactProfile";

		appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorId);

		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		requestValidator.validateEMRId(accessorId, errorCodePrefix);

		List<ContactDTO> contactDTOs = contactHandler.performGetContactDetail(errorCodePrefix);

		AllContactsDetails getAllContactDetails = new AllContactsDetails();
		appLog.printLog(Level.DEBUG, beanServiceName, com.api.CourseApi.utils.AppUtil.convertObjectToJson(getAllContactDetails));
		getAllContactDetails.setContactDTO(contactDTOs);

		return getAllContactDetails;
	}

	@Override
	public AbstractResponse processAddContact(AddBody addBody, String sessionToken) {
		final String errorCodePrefix = com.api.CourseApi.utils.APIGroupCode.EMPLOYEE_ADD_USER.getGroupCode()
				+ com.api.CourseApi.utils.APIGroupCode.EMPLOYEE_ADD_USER.getApiCode() + "000";

		String beanServiceName = "processAddContact";

		appLog.printLog(Level.DEBUG, beanServiceName, " Body" + addBody);

		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);

		StatusDTO statusDto = contactHandler.performAddContact(addBody, errorCodePrefix);

		AllStatusDTO allstatusDto = new AllStatusDTO();
		appLog.printLog(Level.DEBUG, beanServiceName, com.api.CourseApi.utils.AppUtil.convertObjectToJson(allstatusDto));
		allstatusDto.setStatusDto(statusDto);

		return allstatusDto;
	}

	@Override
	public AbstractResponse processDeleteContact(int id, String sessionToken) {
		final String errorCodePrefix = com.api.CourseApi.utils.APIGroupCode.EMPLOYEE_GET_SINGLE_USER.getGroupCode()
				+ com.api.CourseApi.utils.APIGroupCode.EMPLOYEE_GET_SINGLE_USER.getApiCode() + "000";
		
		String beanServiceName = "procesDeleteContact";
		appLog.printLog(Level.DEBUG, beanServiceName, " Id" + id);
		
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		
		StatusDTO statusDto = contactHandler.performDeleteContact(id, errorCodePrefix);
		
		AllStatusDTO allstatusDto = new AllStatusDTO();
		appLog.printLog(Level.DEBUG, beanServiceName, com.api.CourseApi.utils.AppUtil.convertObjectToJson(allstatusDto));
		allstatusDto.setStatusDto(statusDto);
		
		return allstatusDto;
	}

}
