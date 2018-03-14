package com;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.JavaConfig;
import com.service.UseFunctionService;

//@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(JavaConfig.class);
		UseFunctionService  uf=context.getBean(UseFunctionService.class);
		System.out.println(uf.sayHello("....java...config..."));
		System.out.println(".......................................");
		context.close();
	}

}
