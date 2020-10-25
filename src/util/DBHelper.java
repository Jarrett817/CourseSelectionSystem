package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
数据库连接
 */
public class DBHelper {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/zucc_course_selection_system?useUnicode=true&characterEncoding=UTF-8";//数据库连接信息
    private static final String username = "root";
    private static final String password = "13867223154";
    private static Connection conn = null;
    //静态代码块负责加载驱动

    //单例模式返回数据库对象
    public static Connection getConnection() throws Exception {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public void closeCon() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
