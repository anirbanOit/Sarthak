package com.api.CourseApi.feature.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.CourseApi.dao.AddBody;
import com.api.CourseApi.feature.bean.ContactBean;
import com.api.CourseApi.message.response.AbstractResponse;
import com.api.CourseApi.utils.AppUtil;
import com.api.CourseApi.utils.log.AppLog;
import com.api.CourseApi.utils.constant.AppConstant;


@RestController
@RequestMapping("/contact")
public class ContactController {
	private final ContactBean contactBean;
	private final AppLog appLog;

	@Inject
	public ContactController(final ContactBean contactBean) {
		this.appLog = AppLog.getInstance(ContactController.class);
		this.contactBean = contactBean;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getUser() {
		return ResponseEntity.status(HttpStatus.OK).body("User Information");
	}

	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> getAllContact(@Valid @RequestParam("id") final String accessorIdStr,
			@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken) {
		String methodName = "getAllContact";

		appLog.printLog(Level.DEBUG, methodName, "Role: " + accessorIdStr);

		AbstractResponse getAllContactResponse = contactBean.processGetAllContactProfile(accessorIdStr, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getAllContactResponse));

		return ResponseEntity.status(HttpStatus.OK).body(getAllContactResponse);
	}
	
	
	//POST Operation
	
	@CrossOrigin
	@RequestMapping(value = "/addContact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> addContacts(@Valid @RequestBody final AddBody addBody, @RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken ){
		String methodName = "addContact";
		
		AbstractResponse getAllContactResponse = contactBean.processAddContact(addBody, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getAllContactResponse));

		return ResponseEntity.status(HttpStatus.OK).body(getAllContactResponse);
	}
	
	//Delete operation
	
	@CrossOrigin
	@RequestMapping(value = "/delContact/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> deleteContacts(@Valid @PathVariable final int id  , @RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken ){
		String methodName = "addContact";
		
		AbstractResponse getAllContactResponse = contactBean.processDeleteContact(id, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getAllContactResponse));

		return ResponseEntity.status(HttpStatus.OK).body(getAllContactResponse);
	}
}
