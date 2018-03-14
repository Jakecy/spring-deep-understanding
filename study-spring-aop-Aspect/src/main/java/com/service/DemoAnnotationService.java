package com.service;

import org.springframework.stereotype.Service;

import com.annotation.Action;

@Service
public class DemoAnnotationService {

	@Action(name="注解式拦截的add操作")
	public void add(){}
}
