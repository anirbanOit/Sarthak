package com.api.CourseApi;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.api.CourseApi.utils.constant.AppConstant;


@Component
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		AppConstant.setHttpStatusCode();
		
	}
	
}
