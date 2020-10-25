package DAO;

import entity.Page;
import entity.Teacher;
import interFace.checkPassword;
import interFace.checkUser;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.DBHelper.getConnection;

public class teacherDAO extends baseDAO implements checkPassword, checkUser {
    public int getTeacherIdByCourseId(int courseId) {
        String sql = "select teacher_id from course where id='" + courseId + "'";
        ResultSet result = query(sql);
        int s = 0;
        try {
            while (result.next()) {
                s = result.getInt("teacher_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

//    public String getTeacherNameByNumber(String number) {
//        String sql = "select name from teacher where number='" + number + "'";
//        ResultSet result = query(sql);
//        String s = "";
//        try {
//            while (result.next()) {
//                s = result.getString("name");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return s;
//    }

    public ArrayList<Teacher> getAllTeacher() throws SQLException {
//查询所有的课程对象，返回课程对象
        String sql = "select * from teacher ";
        ResultSet result = query(sql);
        ArrayList<Teacher> sts = new ArrayList<Teacher>();

        while (result.next()) {
            Teacher st = new Teacher();
            st.setId(result.getInt("id"));
            st.setNumber(result.getString("number"));
            st.setSex(result.getString("sex"));
            st.setName(result.getString("name"));
            st.setPassword(result.getString("password"));
            sts.add(st);
        }
        return sts;
    }

    //添加老师信息
    public boolean addTeacher(Teacher teacher) {
        String sql = "insert into teacher values(null,'" + teacher.getNumber() + "','" + teacher.getName() + "','"
                + teacher.getPassword() + "','" + teacher.getSex() + "')";
        return update(sql);
    }

    //编辑老师信息
    public boolean editTeacher(Teacher teacher) {
        String sql = "update teacher set name = '" + teacher.getName() + "'";
        sql += ",sex = '" + teacher.getSex() + "'";
        sql += ",number = " + teacher.getNumber();
        sql += " where id = " + teacher.getId();
        return update(sql);
    }

    //通过id来删除对应的老师
    public boolean deleteTeacher(String ids) {
        String sql = "delete from teacher where id in(" + ids + ")";
        return update(sql);
    }

    //获取id对应的老师信息
    public Teacher getTeacher(int id) {
        String sql = "select * from teacher where id = " + id;
        Teacher teacher = null;
        ResultSet resultSet = query(sql);
        try {
            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setSex(resultSet.getString("sex"));
                teacher.setNumber(resultSet.getString("number"));
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

//    public int getClazzIdByTeacherId(int teacherid) {
//        String sql = "select clazz_id from teacher where id=" + teacherid;
//        ResultSet result = query(sql);
//        int ss = 0;
//        try {
//            if (result.next()) {
//                ss = result.getInt("clazz_id");
//            }
//            return ss;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ss;
//    }

    public Teacher login(String name, String password) {
        String sql = "select * from teacher where number = '" + name + "' and password = '" + password + "'";
        ResultSet resultSet = query(sql);
        try {
            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setPassword(resultSet.getString("password"));

                teacher.setSex(resultSet.getString("sex"));
                teacher.setNumber(resultSet.getString("number"));
                return teacher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean editPassword(Teacher teacher, String newPassword) {
        String sql = "update teacher set password = '" + newPassword + "' where id = " + teacher.getId();
        return update(sql);
    }


    @Override
    public boolean checkPassword(String number, String password) {
        String sql = "select * from teacher where number = '" + number + "'";
        ResultSet resultSet = query(sql);
        boolean flag = false;
        try {
            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setPassword(resultSet.getString("password"));
                if (teacher.getPassword().equals(password)) {
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
        String sql = "select * from teacher where number = '" + account + "'";
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
