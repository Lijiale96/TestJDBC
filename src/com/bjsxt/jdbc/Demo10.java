package com.bjsxt.jdbc;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 测试BLOB 二进制 大对象的使用
 */
public class Demo10 {

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
        InputStream is = null;
        OutputStream os = null;

        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                     "root","520512");


//           ps = conn.prepareStatement("insert into t_user2 (username,headImg) value(?,?)");
//            ps.setString(1,"阿康");
//            ps.setBlob(2,new FileInputStream("d:/icon.jpg"));
//            ps.execute();

//            ps.setClob(2,new FileReader(new File("d:/a.txt")));  //将文本内容直接输入到数据库中
//            // 将程序中的字符串输入到数据库的CLOB字段中
//            ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("handsome".getBytes()))));
//
            ps = conn.prepareStatement("select * from t_user2 where id =?");
            ps.setObject(1,112026);

            rs = ps.executeQuery();
            while (rs.next()){
               Blob b =  rs.getBlob("headImg");
                is = b.getBinaryStream();
                os= new FileOutputStream("d:/a.jpg");
               int temp =0;
               while((temp=is.read())!=-1){
                  os.write(temp);
               }
            }


        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }catch( Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

                try {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
