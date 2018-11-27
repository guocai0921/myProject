package com.guocai.common.myUtils;

import java.sql.*;

/**
 * java类简单作用描述
 *
 * @ClassName: DbUtil
 * @Package: com.guocai.common.myUtils
 * @Description: 数据库连接工具类
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-08-9:18
 */
public class DbUtil {
    public static Connection getConnection(String driver,String host,String port,String sid,String username,String password) {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//找到oracle驱动器所在的类  ---
            String url = "jdbc:oracle:"+driver+":@"+host+":"+port+":"+sid; //URL地址
            String name = username;
            String pwd = password;
            conn = DriverManager.getConnection(url, name, pwd);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(PreparedStatement pstmt){
        if(pstmt !=null){
            try {
                pstmt.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs){
        if(rs !=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection conn){
        if(conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
