package DAO;

import entity.Admin;
import interFace.checkPassword;
import interFace.checkUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
管理员的数据库操作
 */
public class adminDAO extends baseDAO implements checkPassword, checkUser {
    public Admin login(String name, String password) {
        String sql = "select*from admin where name='" + name + "'and password='" + password + "'";
        ResultSet resultset = query(sql);
        try {
            if (resultset.next()) {
                Admin admin = new Admin();
                admin.setName(resultset.getString("name"));
                admin.setPassword(resultset.getString("password"));
                admin.setLevel(resultset.getInt("access_level"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Admin> showAllAdmin() {
        String sql = "select * from admin";
        ResultSet result = query(sql);
        List<Admin> list = new ArrayList<Admin>();
        try {
            while (result.next()) {
                Admin admin = new Admin();
                admin.setId(result.getInt("id"));
                admin.setName(result.getString("name"));
                admin.setPassword(result.getString("password"));
                list.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addAdmin(Admin admin) {
        String sql = "insert into admin values (null,'" + admin.getName() +
                "','" + admin.getPassword() + "','" + admin.getLevel() + "')";
        return update(sql);
    }

    public boolean deleteAdmin(String ids) {
        String sql = "delete from admin where id in('" + ids + "')";

        return update(sql);
    }

    public boolean editAdmin(Admin admin) {
        String sql = "update admin set name='" + admin.getName() + "',password='" + admin.getPassword()
                + "' where id='" + admin.getId() + "'";
        return update(sql);
    }

    public Admin getAdmin(int id) {
        String sql = "select * from admin where id='" + id + "'";
        ResultSet resultset = query(sql);
        try {
            if (resultset.next()) {
                Admin admin = new Admin();

                admin.setId(resultset.getInt("id"));
                admin.setName(resultset.getString("name"));
                admin.setPassword(resultset.getString("password"));
                return admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkPassword(String number, String password) {
        String sql = "select * from admin where name = '" + number + "'";
        ResultSet resultSet = query(sql);
        boolean flag = false;
        try {
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setPassword(resultSet.getString("password"));
                if (admin.getPassword().equals(password)) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean checkUser(String account) {
        String sql = "select * from admin where name = '" + account + "'";
        ResultSet resultSet = query(sql);
        boolean flag = false;
        try {
            if (resultSet.next()) {
                flag = true;
            } else {
                flag = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
