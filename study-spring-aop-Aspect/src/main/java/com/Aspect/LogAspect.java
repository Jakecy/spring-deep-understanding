package com.Aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.annotation.Action;

@Aspect
@Component
public class LogAspect {

	//定义一个切点
	//切点就是拦截规则
	//spring从哪里切，怎么切都是由切点定义的
	@Pointcut("@annotation(com.annotation.Action)") //通过@Pointcut声明切点
	public void annotationPointCut(){};

	@After(value ="annotationPointCut()")
	public void after(JoinPoint joinPoint){
		System.out.println("-------------切面开始进行拦截操作----------------");
		MethodSignature signature=(MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		Action action=method.getAnnotation(Action.class);
		System.out.println("注解式拦截: "+action.name());

	}
}
