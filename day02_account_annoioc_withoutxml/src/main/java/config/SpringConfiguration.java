package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 * spring中的新注解
 * @Confiuration :作用：表明当前类是一个配置类
 *                细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写
 * @ComponentScan :作用：用于通过注解指定spring在创建容器时扫描的包
 *                 属性：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
 *                      使用此注解，就等同于在xml中配置了：<context:component-scan base-package="com.itheima"></context:component-scan>
 * @Bean :作用：用于把当前方法的返回值作为bean对象存入spring的IOC容器中
 *        属性：用于指定bean的id。默认值是当前方法的名称
 *        细节：当使用注解配置方法时，如果注解有参数，spring框架会去容器中查找有没有可用的bean对象
 *             查找的方式和Autowired注解的作用是一样的
 * @Import :作用：用于导入其他的配置类
 *          属性：value：用于指定其他配置类的字节码
 *          当使用Import的注解之后，有Import注解的类就是父配置类，而导入的都是子配置类
 * @PropertySource :作用:用于指定properties文件的位置
 *                  属性：value:指定文件的名称和路径
 *                       关键字 classpath：表示类路径下
 */
//@Configuration
@ComponentScan(basePackages = {"com.itheima"})
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")//classpath表示后面的路径是类路径
public class SpringConfiguration {
    ///**
    // * 用于创建一个QueryRunner对象
    // * @param dataSource
    // * @return
    // */
    //@Bean(name = "runner")
    //@Scope("prototype")
    //public QueryRunner createQueryRunner(DataSource dataSource){
    //    return  new QueryRunner(dataSource);
    //}
    //
    ///**
    // * 创建数据源对象
    // * @return
    // */
    //@Bean(name = "dataSource")
    //public DataSource dataSource(){
    //    try {
    //        ComboPooledDataSource ds = new ComboPooledDataSource();
    //        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
    //        ds.setJdbcUrl("jdbc:mysql://localhost:3306/szk_ioc?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
    //        ds.setUser("root");
    //        ds.setPassword("");
    //        return ds;
    //    }catch(Exception e){
    //        throw new RuntimeException(e);
    //    }
    //}
}
