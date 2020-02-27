package com.bjsxt.jdbc;
import java.sql.*;

/**
 * 测试PreparedStatement的基本用法
 */
public class Demo03 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "520512");

            String sql = "insert into t_user (username,pwd,regTime) values (?,?,?)";//？占位符
            ps = conn.prepareStatement(sql);

            //可以使用setObject方法处理参数
            ps.setString(1, "李家乐");//参数索引是从1开始计算的，而不是0
            ps.setString(2, "520512");
            ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
//            ps.setObject(1,"张雨昕");
//            ps.setObject(2,"1996827");
            System.out.println("插入一行记录");
            //ps.execute();
            int count = ps.executeUpdate();
            System.out.println(count);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                } catch(SQLException e){
                    e.printStackTrace();
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



