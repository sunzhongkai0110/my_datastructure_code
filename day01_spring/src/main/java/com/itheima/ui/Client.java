package com.itheima.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    /**
     * 获取spring的IOC核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     * ClassPathXmlApplicationContext:可以加载内路径下的配置文件，配置文件必须在内路径下，不在的话加载不了，创建不了容器(实际开发中，更常用)
     * FileSystemXmlApplicationContext:可以加载磁盘任意路径下的配置文件（必须有访问权限）
     *
     * AnnotationConfigApplicationContext:是用于读取注解创建容器的
     *
     * 核心容器的两个接口引发出的问题：
     * ApplicationContext:单例对象使用，在实际开发中，更多采用此接口
     *      在构建核心容器时，创建对象采取的策略是采用立即加载的方式，即只要一读取完配置文件，马上就创建配置文件中配置的对象
     * BeanFactory:多例对象使用
     *      在构建核心容器时，创建对象采取的策略是采用延迟加载的方式，即什么时候根据id获取对象了，什么时候才真正的创建对象
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //ApplicationContext ac = new FileSystemXmlApplicationContext("D:\\IntelliJ IDEA Web Workspace\\day01_spring\\src\\main\\resources\\bean.xml");
        //2.根据id获取Bean对象,根据唯一标志取出对象
        //IAccountService as = (IAccountService) ac.getBean("accountService");//获得Object类型进行强转
        //IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);//传给字节码，使用字节码，强转成想要的对象

        //System.out.println(as);
        //System.out.println(adao);
        //as.saveAccount();

        //------------------BeanFactory-----------------------------
        Resource resource=new ClassPathResource("bean.xml");
        BeanFactory factory=new XmlBeanFactory(resource);
        IAccountService as=(IAccountService)factory.getBean("accountService");
        System.out.println(as);
    }
}
