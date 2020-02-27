package com.bjsxt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 测试个数据库建立连接
 */
public class Demo01 {
    public static void main(String[] args) {
        Connection conn = null;
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            long start = System.currentTimeMillis();
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接。比较耗时！这是Connection对象管理的一个要点！）
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root","520512");
            long end = System.currentTimeMillis();
            System.out.println(conn);
            System.out.println("建立连接，耗时："+(end-start)+"ms毫秒");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch( SQLException e){
            e.printStackTrace();
        }finally {
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
