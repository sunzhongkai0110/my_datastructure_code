package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 账户的业务层实现类
 *
 * 曾经xml的配置：
 * <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 *       scope="" init-method="" destroy="">
 *      <property name="" value="" / ref=""></property>
 * </bean>
 *
 * 如果注解中只有一个value属性，这个value属性是可以不写的
 *
 * 用于创建对象的:作用和在xml配置文件中编写一个<bean></bean>标签实现的功能是一样的
 *              @Component :作用:将当前类反射创建对象，把当前类对象存入spring容器中
 *                          属性：value：用于指定bean的id，当不写时，它的默认值是当前类名，且首字母小写
 *              @Controer :一般用于表现层
 *              @Service :一般用于业务层
 *              @Repository :一般用于持久层
 *              以上三个注解的作用和属性与Conponent是一模一样
 *              它们三个是spring框架为我们提供明确的三层使用的注解，使我们的三层对象更加清晰
 * 用于注入数据的:作用和在xml配置文件中的bean标签中写一个<property></property>的作用是一样的
 *              @Autowired :作用：只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                          如果IOC容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 *                          如果IOC容器中有多个类型匹配时:先按照类注入，再按照名称注入
 *                          出现位置：可以是变量上，，也可以是方法上
 *                          细节：在使用注解注入时，set方法就不是必须的了
 *              @Qualifier :作用：在按照类注入的基础上再按照名称注入。在给类成员注入时不能单独使用，要和Autowired组合使用，但是在给方法参数注入时可以单独使用
 *                          属性：用于指定注入bean的id
 *              @Resource :作用：直接按照bean的id注入，它可以独立使用
 *                         属性：name：用于指定bean的id
 *              以上三种注入方式都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现
 *              另外，集合类型的注入只能通过xml来实现
 *
 *              @Value :作用：用于注入基本类型和String类型的数据
 *                      属性：value 用于指定数据类型的值，它可以使用spring中的SpEL(也就是spring的el表达式)
 *                           SpEL的写法：${表达式}
 *                              写在jsp文件中，一定是jsp的EL表达式，一定会去四大域中获取数据；
 *                              如果写在spring的配置文件或者注解中，就是spring的EL表达式，会去spring指定位置去找；
 *                              如果写在mybatis的配置文件中，就是mybatis的EL表达式，就去mybatis规定的位置获取数据
 * 用于改变作用范围的:作用就和在bean标签中使用scope属性实现的功能是一样的
 *                 @Scope :作用：用于指定bean的属性范围
 *                         属性：value  指定范围的取值，常用取值：singleton  prototype
 * 和生命周期相关的[了解]:作用就和在bean标签使用init-method和destroy-method的作用是一样的
 *                    @PreDestroy :作用：用于指定销毁方法
 *                    @PostConstruct :作用：用于指定初始化方法
 */
@Service(value = "accountService")
//@Scope("prototype")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao = new AccountDaoImpl();

    //public AccountServiceImpl(){
    //    System.out.println("对象创建了");
    //}

    @PostConstruct
    public void init() {
        System.out.println("初始化方法执行了");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法执行了");
    }
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
