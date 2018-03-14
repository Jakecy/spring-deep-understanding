package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.service.FunctionService;
import com.service.UseFunctionService;


@Configuration
public class JavaConfig {

	//1.把FunctionServie放入spring容器中
	@Bean
	public FunctionService  functionService(){
		return new FunctionService();
	}
	
	//2.把UseFunctionService放入spring容器中
	//使用@Bean会自动把生成的对象放入spring容器中管理
	@Bean
	public UseFunctionService  useFunctionService(){
		UseFunctionService  us=new UseFunctionService();
		us.setFunctionService(functionService());
		return us;
	}
	
	
	//3.把UseFunctionService放入spring容器中
	@Bean
	public UseFunctionService  useFunctionService(FunctionService  functionService){
		UseFunctionService  us=new UseFunctionService();
		us.setFunctionService(functionService);
		return us;
	}
}
