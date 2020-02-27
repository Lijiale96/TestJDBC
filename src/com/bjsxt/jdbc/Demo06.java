package com.bjsxt.jdbc;
import java.sql.*;

/**
 * 测试事务的基本概念和用法
 */
public class Demo06 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs  = null;
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                     "root","520512");
            conn.setAutoCommit(false);//JDBC中默认true是自动提交

            ps1 = conn.prepareStatement("insert into t_user (username,pwd,regTime) values(?,?,?)");
            ps1.setObject(1,"阿康"); //
            ps1.setObject(2,"520512"); //
            ps1.setDate(3,new java.sql.Date(System.currentTimeMillis())); //
            ps1.execute();

            System.out.println("插入一个用户,阿康");

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ps2 = conn.prepareStatement("insert into t_user (username,pwd,regTime) values(?,?,?)");
            ps2.setObject(1,"张雨昕"); //把id大于2的记录都取出来
            ps2.setObject(2,"520512"); //把id大于2的记录都取出来
            ps2.setDate(3,new java.sql.Date(System.currentTimeMillis())); //
            ps2.execute();
            System.out.println("插入一个用户,张雨昕");

            conn.commit();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            try {
                conn.rollback();  //回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }catch( SQLException e){
            e.printStackTrace();
        }finally {

            try {
                if (ps1 != null) {
                    ps1.close();
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
