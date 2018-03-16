# spring-deep-understanding
深入学习spring的demo以及相关笔记



对spring的AOP_AspectJ的学习：



AspectJ

声明切点pointcut

 




A pointcut declaration has two parts: a signature comprising a name and any parameters, and a pointcut expression that determines exactly which method executions we are interested in. In the @AspectJ annotation-style of AOP, a pointcut signature is provided by a regular method definition, and the pointcut expression is indicated using the @Pointcut annotation (the method serving as the pointcut signature must have a void return type).



切点的构成
切点由俩部分构成：pointcut signature + pointcut expression。


Pointcut expression的种类：

Some examples of common pointcut expressions are given below.
：
•	the execution of any public method:
execution(public * *(..))
•	the execution of any method with a name beginning with "set":
execution(* set*(..))
•	the execution of any method defined by the AccountService interface:
execution(* com.xyz.service.AccountService.*(..))
•	the execution of any method defined in the service package:
execution(* com.xyz.service.*.*(..))
•	any join point (method execution only in Spring AOP) where the proxy implements the AccountService interface:
this(com.xyz.service.AccountService)
•	any join point (method execution only in Spring AOP) where the target object has an @Transactional annotation:
@target(org.springframework.transaction.annotation.Transactional)

•	any join point (method execution only in Spring AOP) where the executing method has an @Transactional annotation:
@annotation(org.springframework.transaction.annotation.Transactional)





使用@annotation做切点表达式pointcut expression
•	any join point (method execution only in Spring AOP) where the executing method has an @Transactional annotation:
@annotation(org.springframework.transaction.annotation.Transactional)

e.g:
我自定义了一个注解，此时这个注解的作用就是一个Mark标记：
 


 



