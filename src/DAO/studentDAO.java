package DAO;

import entity.Page;
import entity.Student;
import interFace.checkPassword;
import interFace.checkUser;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class studentDAO extends baseDAO implements checkPassword, checkUser {
    public ArrayList<Student> getAllStudent() throws SQLException {
//查询所有的课程对象，返回课程对象
        String sql = "select * from student ";
        ResultSet result = query(sql);
        ArrayList<Student> sts = new ArrayList<Student>();

        while (result.next()) {
            Student st = new Student();
            st.setId(result.getInt("id"));
            st.setNumber(result.getString("number"));
            st.setSex(result.getString("sex"));
            st.setName(result.getString("name"));
            st.setClazz_id(result.getInt("clazz_id"));

            sts.add(st);
        }
        return sts;
    }

    //学生添加方法
    public boolean addStudent(Student student) {
        String sql = "insert into student values (null,'" + student.getNumber() + "','" + student.getName() +
                "','" + student.getPassWord() + "','" + student.getSex() + "','" + student.getClazz_id() + "')";
        return update(sql);
    }

    //学生信息修改
    public boolean editStudent(Student student) {
        String sql = "update student set name='" + student.getName() + "',sex='" + student.getSex() + "',password='" + student.getPassWord()
                + "',number='" + student.getNumber() + "' where id='" + student.getId() + "'";
        return update(sql);
    }

    //学生删除方法
    public boolean deleteStudent(String ids) {
        String sql = "delete from student where id in('" + ids + "')";

        return update(sql);
    }

    public Student getStudent(int id) {
        String sql = "select * from student where id='" + id + "'";
        Student student = null;
        ResultSet resultset = query(sql);
        try {
            if (resultset.next()) {
                student = new Student();
                student.setId(resultset.getInt("id"));
                student.setName(resultset.getString("name"));
                student.setNumber(resultset.getString("number"));
                student.setPassWord(resultset.getString("password"));
                student.setSex(resultset.getString("sex"));
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }


    public int getStuIdByNumber(String number) {
        String sql = "select id from student where number='" + number + "'";
        ResultSet result = query(sql);
        int i = 0;
        try {
            while (result.next()) {
                i = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public Student login(String number, String password) {
        String sql = "select * from student where number = '" + number + "' and password = '" + password + "'";
        ResultSet resultSet = query(sql);
        try {
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setPassWord(resultSet.getString("password"));
                student.setClazz_id(resultSet.getInt("clazz_id"));
                student.setSex(resultSet.getString("sex"));
                student.setNumber(resultSet.getString("number"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //修改密码
    public boolean editPassword(Student student, String newPassword) {
        String sql = "update student set password = '" + newPassword + "' where id = " + student.getId();
        return update(sql);
    }

    @Override
    public boolean checkPassword(String number, String password) {
        String sql = "select * from student where number = '" + number + "'";
        ResultSet resultSet = query(sql);
        boolean flag = false;
        try {
            if (resultSet.next()) {
                Student student = new Student();
                student.setPassWord(resultSet.getString("password"));
                if (student.getPassWord().equals(password)) {
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
        String sql = "select * from student where number = '" + account + "'";
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

