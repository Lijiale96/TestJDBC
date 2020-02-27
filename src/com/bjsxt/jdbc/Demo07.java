package com.bjsxt.jdbc;
import java.sql.*;
import java.util.Random;

/**
 * 测试时间处理(java.sql.Date,Time,Timestamp)
 */
public class Demo07 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps1 = null;

        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                     "root","520512");


            for (int i=0;i<1000;i++) {
                ps1 = conn.prepareStatement("insert into t_user2 (username,pwd,regTime,lastLoginTime) values(?,?,?,?)");
                ps1.setObject(1, "康康"+i); //
                ps1.setObject(2, "520512"); //

               int rand = 1000000000 + new Random().nextInt(1000000000);

                java.sql.Date date = new java.sql.Date(System.currentTimeMillis()-rand);
                Timestamp stamp = new Timestamp(System.currentTimeMillis()-5*rand);//如果需要插入指定日期，可以使用Calendar、DateFormat
                ps1.setDate(3, date); //
                ps1.setTimestamp(4, stamp);
                ps1.execute();
            }
            System.out.println("插入一个用户,阿康");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
