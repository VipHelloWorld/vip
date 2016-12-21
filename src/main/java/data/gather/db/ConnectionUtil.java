package data.gather.db;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

/**
 * Created by dell on 2016/12/20.
 */
public class ConnectionUtil {

    public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://115.28.18.174:3306/onwer?useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
