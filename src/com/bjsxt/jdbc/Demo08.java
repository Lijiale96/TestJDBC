package com.bjsxt.jdbc;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 测试时间处理(java.sql.Date,Time,Timestamp),取出指定时间段的数据
 */
public class Demo08 {

    /**
     * 将字符串代表的日期转为long数字（格式：yyyy-MM-dd  hh:mm:ss)
     * @param dateStr
     * @return
     */
    public static long str2Date(String dateStr){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            return format.parse(dateStr).getTime();

        }catch(ParseException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                     "root","520512");


            ps = conn.prepareStatement("select * from t_user2 where lastLoginTime>? and lastLoginTime <? order by lastLoginTime");

//            java.sql.Date start = new java.sql.Date(str2Date("2020-2-10 15:44:20"));
//            java.sql.Date end = new java.sql.Date(str2Date("2020-2-27 15:44:20"));

           Timestamp start = new Timestamp(str2Date("2020-2-26 00:00:00"));
            Timestamp end = new Timestamp(str2Date("2020-2-26 16:00:00"));
            ps.setObject(1,start);
            ps.setObject(2,end);

            rs= ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id")+"--"+rs.getString("username")+"--"+rs.getTimestamp("lastLoginTime"));

            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }catch( SQLException e){
            e.printStackTrace();
        }finally {

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
