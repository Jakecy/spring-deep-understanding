package com;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.config.AopConfig;
import com.service.DemoAnnotationService;

//@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AopConfig.class);
		//
		ConfigurableEnvironment environment = context.getEnvironment();
		
		DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);
		annotationService.add();
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		context.close();
	}

}
