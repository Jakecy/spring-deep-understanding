package com.service;

public class UseFunctionService {
  
	private FunctionService  functionService;
	
	public void setFunctionService(FunctionService functionService){
		this.functionService=functionService;
	}
	
	public String sayHello(String word){
		return functionService.sayHello(word);
	}
}
