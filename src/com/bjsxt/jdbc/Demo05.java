package com.bjsxt.jdbc;
import java.sql.*;

/**
 * 测试批处理的用法
 */
public class Demo05 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs  = null;
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root","520512");
             conn.setAutoCommit(false);//设为手动提交
            long start = System.currentTimeMillis();
            stmt  = conn.createStatement();

            for (int i =0;i<20000;i++){
                stmt.addBatch("insert into t_user (username,pwd,regTime ) values('阿康"+i+"',520512,now())");
            }

            stmt.executeBatch();
            conn.commit();//提交事务
            long end = System.currentTimeMillis();
            System.out.println("插入20000条数据，耗时（毫秒）:"+(end-start));


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch( SQLException e){
            e.printStackTrace();
        }finally {

            //遵循:resultset-->statement-->connection这样的关闭顺序
            try {
                if (rs != null) {
                    rs.close();
                }
            }catch(SQLException e){
                    e.printStackTrace();
                }
            try {
                if (stmt != null) {
                    stmt.close();
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
