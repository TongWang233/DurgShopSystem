package com.durgshop.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 编写一个切面类，模拟数据库事务的工作过程
 * 
 * 通过实现不同的通知接口，把切面切入到不通过的切入点中;当切入点发出特定通知时，切面的代码就会开始工作
 * 
 * @author 222
 *
 */
public class TranManager implements MethodBeforeAdvice,AfterReturningAdvice,ThrowsAdvice{

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		//前置通知切面实现的功能代码编写到这里
		System.out.println("开始数据库事务！");
	}

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		//后置通知切面实现的功能代码编写到这里
		System.out.println("提交数据库事务！");
	}
	

	public void afterThrowing(Exception ex)  {
		//异常通知切面实现的功能代码编写到这里
		System.out.println("回滚数据库事务！");
	}
}
