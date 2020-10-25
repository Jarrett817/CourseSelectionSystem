package DAO;

import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class baseDAO {
    private DBHelper dbhelper = new DBHelper();

    public void closeCon() {
        dbhelper.closeCon();
    }

    /**
     * 返回一个数据库操作后的结果集
     */

    public ResultSet query(String sql) {
        try {
            PreparedStatement prepareStatement = dbhelper.getConnection().prepareStatement(sql);
            return prepareStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     *
     * 定义一个更新数据库信息的方法
     */
    public boolean update(String sql) {
        try {
            return dbhelper.getConnection().prepareStatement(sql).executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
