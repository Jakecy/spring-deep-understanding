package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.JavaConfig;
import com.service.UseFunctionService;

//@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(JavaConfig.class);
		//
		UseFunctionService  uf=context.getBean(UseFunctionService.class);
		
		uf.sayHello("....java...config...");
		context.close();
		//
		/*SpringApplication.run(SpringDemoApplication.class, args);
		System.out.println("............spring启动完成.........");*/
	}

}
