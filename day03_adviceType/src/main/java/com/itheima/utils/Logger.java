package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，提供了公共的代码
 */
public class Logger {
    /**
     * 前置通知
     */
    public void beforeReturnPrintLog() {
        System.out.println("前置通知Logger类中的beforeReturnPrintLog方法开始记录日志。。。");
    }

    /**
     * 后置通知
     */
    public void afterReturnPrintLog() {
        System.out.println("后置通知Logger类中的afterReturnPrintLog方法开始记录日志。。。");
    }

    /**
     * 异常通知
     */
    public void afterThrowingPrintLog() {
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志。。。");
    }

    /**
     * 最终通知
     */
    public void afterPrintLog() {
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志。。。");
    }

    /**
     * 环绕通知
     * 问题：当配置了环绕通知后，切入点的方法没有执行，而通知的方法执行了
     * 分析：通过对比动态代理的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而此代码没有
     * 解决：Spring框架提供了一个接口:ProceedingJoinPoint。该接口有一个方法proceed(),此方法就相当于明确调用切入点方法，该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会提供该接口的实现类可以使用
     * spring中的环绕通知：它是spring框架提供的一种可以在代码中手动控制增强方法何时执行的方式
     */
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();//得到方法运行所需的参数

            System.out.println("Logger类中的aroundPrintLog方法开始记录日志。。。前置通知");

            rtValue = pjp.proceed();//明确调用业务层方法(切入点方法)

            System.out.println("Logger类中的aroundPrintLog方法开始记录日志。。。后置通知");

            return rtValue;
        } catch (Throwable t) {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志。。。异常通知");
            throw new RuntimeException(t);
        } finally {
            System.out.println("Logger类中的aroundPrintLog方法开始记录日志。。。最终通知");
        }


    }
}
