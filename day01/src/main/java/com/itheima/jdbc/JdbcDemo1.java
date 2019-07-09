package com.itheima.jdbc;

import java.sql.*;

/**
 * 程序的耦合
 *          耦合：程序键的依赖关系，包括：类之间的依赖，方法间的依赖
 *          解耦：降低程序间依赖关系
 * 实际开发中，应该做到：编译期不依赖，运行时才依赖
 * 解耦的思路：1.使用反射来创建对象，而不是使用new关键字
 *           2.通过读取配置文件来获取要创建的对象的全限定类名
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        /**
         * JDBC连接数据库
         * 1.注册驱动
         * 2.获取连接
         * 3.获取操作数据库的预处理对象
         * 4.执行SQL，得到结果集
         * 5.遍历结果集
         * 6.释放资源
         */
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());//没有mysql的依赖，将无法编译通过
        Class.forName("com.mysql.jdbc.Driver");//不再依赖某一个驱动类，但是使用其他数据库还是要改

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy", "root", "1234");

        PreparedStatement pstm = conn.prepareStatement("select * from account");

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }

        rs.close();
        pstm.close();
        conn.close();
    }
}
