package com.bjsxt.jdbc;
import java.sql.*;

/**
 * 测试PreparedStatement的基本用法
 */
public class Demo04 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs  = null;
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root","520512");

            String sql ="select id,username,pwd from t_user where id>?";//？占位符
             ps = conn.prepareStatement(sql);
            ps.setObject(1,2); //把id大于2的记录都取出来

             rs  =ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getInt(1)+"---"+rs.getString(2)+"---"+rs.getString(3));
            }



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
