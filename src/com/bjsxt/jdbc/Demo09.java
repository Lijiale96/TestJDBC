package com.bjsxt.jdbc;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 测试CLOB的文本大对象的使用
 * 包含：将字符串、文件内容插入数据库中的CLOB字段、将ClOB字段值取出来
 */
public class Demo09 {

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


           //ps = conn.prepareStatement("insert into t_user2 (username,myInfo) value(?,?)");
            //ps.setString(1,"阿康");
          //  ps.setClob(2,new FileReader(new File("d:/a.txt")));  //将文本内容直接输入到数据库中
            // 将程序中的字符串输入到数据库的CLOB字段中
        //    ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("handsome".getBytes()))));

            ps = conn.prepareStatement("select * from t_user2 where id =?");
            ps.setObject(1,112023);

            rs = ps.executeQuery();
            while (rs.next()){
               Clob c =  rs.getClob("myInfo");
               Reader r = c.getCharacterStream();
               int temp =0;
               while((temp=r.read())!=-1){
                   System.out.print((char)temp);
               }
            }


        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }catch( Exception e){
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
