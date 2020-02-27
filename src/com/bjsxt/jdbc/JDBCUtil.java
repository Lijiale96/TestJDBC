package com.bjsxt.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    static Properties pros = null; //可以帮助读取和处理资源文件中的信息

    static{ //加载JDBCUtil类的时候调用
        pros = new Properties();

        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getMysqlConn(){
        try {
            Class.forName(pros.getProperty("mysqlDriver"));
            return  DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    pros.getProperty("mysqlUser"),pros.getProperty("mysqlPwd"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

public static void close(ResultSet rs,Statement ps,Connection conn){
    try {
        if (rs != null) {
            rs.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    try {
        if (ps != null) {
            ps.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    try {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void close( Statement ps,Connection conn){
            try {
                if (ps != null) {
                    ps.close();
                }} catch (SQLException e){
                    e.printStackTrace();
                }

                try {
                    if (conn != null) {
                        conn.close();}
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                public static void close(Connection conn){
                try {
                    if (conn != null) {
                        conn.close();}
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
