package DAO;

import entity.Clazz;
import entity.Page;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clazzDAO extends baseDAO {
    public ArrayList<Clazz> getAllClazz() throws SQLException {
//查询所有的课程对象，返回课程对象
        String sql = "select * from clazz ";
        ResultSet result = query(sql);
        ArrayList<Clazz> co = new ArrayList<Clazz>();

        while (result.next()) {
            Clazz cl = new Clazz();
            cl.setId(result.getInt("id"));
            cl.setName(result.getString("name"));
            cl.setSelectedNum(result.getInt("selected_num"));
            co.add(cl);
        }
        return co;
    }

    public Clazz getClazzById(int clazzId) {
        String sql = "select name from clazz where id=" + clazzId;
        ResultSet result = query(sql);
        String ss = "";
        try {
            if (result.next()) {
                Clazz clazz = new Clazz();
                clazz.setName(result.getString("name"));
                return clazz;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Integer> getStudentIdByClazzId(int clazzId) {
        String sql = "select student_id from selected_clazz where clazz_id=" + clazzId;
        List<Integer> list = new ArrayList<Integer>();
        ResultSet result = query(sql);
        try {
            while (result.next()) {
                list.add(result.getInt("student_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteStudentInClazz(int stuId) {
        String sql = "delete from selected_clazz where student_id = " + stuId;
        return update(sql);

    }

    public boolean addStudentToClazz(int stuId, int clazzId) {
        String sql = "insert into selected_clazz values(" + clazzId + "," + stuId + ")";
        return update(sql);
    }

//    public int getClazzIdByTeacherId(int teacherId) {
//        String sql = "select clazz_id from teacher where id=" + teacherId;
//        ResultSet resultSet = query(sql);//存放查询结果的结果集
//        int clazzId = 0;
//        try {
//            while (resultSet.next()) {
//                clazzId = resultSet.getInt("clazz_id");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return clazzId;
//    }

//    public boolean addStudentToClazz(int stuId, int teacherId) {
//        int clazzId = getClazzIdByTeacherId(teacherId);
//        String sql = "insert into selected_clazz values('" + clazzId + "','" + stuId + "','" + teacherId + "')";
//        return update(sql);
//    }

    public void updateStudentInClazzNum(int clazzId, int i) {
        //更新班级人数

        String sql = "update clazz set selected_num = selected_num + " + i + " where id = " + clazzId;
        update(sql);
    }

    public int getStuNum(int clazzId) {
        String sql = "select selected_num from clazz where id=" + clazzId;
        ResultSet result = query(sql);
        int i = 0;
        try {
            while (result.next()) {
                i = result.getInt("selected_num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
