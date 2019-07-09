package com.itheima.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂：创建service和dao对象
 * Bean:在计算机英语中，有可重用组件的含义
 * JavaBean：用java语言编写的可重用组件。javabean>实体类(实体类只是可重用组建的一部分)
 * <p>
 * 1.需要一个配置文件来配置service和dao
 * 配置文件可以是xml也可以是properties
 * 配置的内容：唯一标识=全限定类名（key=value）
 * 2.通过读取配置文件中配置的内容，读全限定类名，反射创建对象
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;

    //定义一个Map，用于存放要创建的对象，称为容器
    private static Map<String,Object> beans;

    //定义静态代码块，为Properties对象赋值
    static {
        try {
            //实例化对象
            props = new Properties();
            //获取properties文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans=new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration keys=props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个key
                String key=keys.nextElement().toString();
                //根据key获取value
                String beanPath=props.getProperty(key);
                //反射创建对象
                Object value=Class.forName(beanPath).newInstance();
                //把key和value存入容器中
                beans.put(key,value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化文件失败！");
        }
    }

    /**
     * 根据bean的名称获取对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

    /**
     * 根据Bean的名称获取bean对象
     *
     * @param beanName
     * @return
     */
    //public static Object getBean(String beanName) {
    //    Object bean = null;
    //    try {
    //        String beanPath = props.getProperty(beanName);
    //        System.out.println(beanPath);
    //        //使用反射的方式创建对象
    //        bean = Class.forName(beanPath).newInstance();//每次都会调用默认构造函数创建对象
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return bean;
    //}
}
