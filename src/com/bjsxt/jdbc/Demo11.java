package com.bjsxt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 测试使用JDBCUtil工具类来简化JDBC开发
 */
public class Demo11 {

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
        //Reader r =null;

        //加载驱动类
        try {
            conn = JDBCUtil.getMysqlConn();
            ps =conn.prepareStatement("insert into t_user (username) values (?)");
            ps.setString(1,"李家乐是无敌");
            ps.execute();
        } catch( Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.close(rs,ps,conn);
        }
    }
}
