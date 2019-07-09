package com.itheima.ui;

import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        //多态：如果看成父类对象的话，只能访问父类中拥有的方法和属性
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //看成自己
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取Bean对象,根据唯一标志取出对象
        //IAccountService as1 = (IAccountService) ac.getBean("accountService");//获得Object类型进行强转
        //IAccountService as2 = (IAccountService) ac.getBean("accountService");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();
        //System.out.println(as1 == as2);

        //手动关闭容器
        ac.close();
    }
}
