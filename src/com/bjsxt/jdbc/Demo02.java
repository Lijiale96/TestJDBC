package com.bjsxt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试Statement接口的用法，执行SQL语句，以及SQL注入问题
 */
public class Demo02 {
    public static void main(String[] args) {
        Connection conn =null;
        Statement stmt = null;
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接。比较耗时！这是Connection对象管理的一个要点！）
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象！
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root","520512");

            stmt = conn.createStatement();
//            String name="赵奇";
//            String sql ="insert into t_user (username,pwd,regTime) value('"+name+"',5555,now())";
//            stmt.execute(sql);

            /**
             * 测试SQL注入
             */
            String id ="5";
            String sql ="delete from t_user where id ="+id;
            stmt.execute(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch( SQLException e){
            e.printStackTrace();
        }finally {
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
}
