package com.api.CourseApi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;



public class ServetInitializer {
	public class ServletInitializer extends SpringBootServletInitializer {
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(CourseApiApplication.class);
		}
	}
}
