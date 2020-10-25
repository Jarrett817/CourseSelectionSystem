package DAO;

import entity.Course;
import entity.Page;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class courseDAO extends baseDAO {
    //添加课程

    public boolean addCourse(Course course) {
        String sql = "insert into course values(null,'" + course.getName() + "','" +
                course.getTeacherId() + "','" + course.getCoDate() + "','"
                + course.getCoursePlace() + "',0,'" + course.getMaxNum() + "') ";
        return update(sql);
    }

    public Course getOneCourseById(int id) {
        String sql = "select * from course where id=" + id;
        ResultSet resultset = query(sql);
        Course cl = new Course();
        try {
            while (resultset.next()) {
                cl.setId(resultset.getInt("id"));
                cl.setName(resultset.getString("name"));
                cl.setTeacherId(resultset.getInt("teacher_id"));
                cl.setCoDate(resultset.getString("course_date"));
                cl.setSelectedNum(resultset.getInt("selected_num"));
                cl.setMaxNum(resultset.getInt("max_num"));
                cl.setCoursePlace(resultset.getString("course_place"));
                return cl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cl;
    }

    public List<Course> getCourseById(int id) {
        List<Course> ret = new ArrayList<Course>();
        String sql = "select * from course where id=" + id;
        ResultSet resultset = query(sql);
        Course cl = new Course();
        try {
            while (resultset.next()) {
                cl.setId(resultset.getInt("id"));
                cl.setName(resultset.getString("name"));
                cl.setTeacherId(resultset.getInt("teacher_id"));
                cl.setCoDate(resultset.getString("course_date"));
                cl.setSelectedNum(resultset.getInt("selected_num"));
                cl.setMaxNum(resultset.getInt("max_num"));
                cl.setCoursePlace(resultset.getString("course_place"));
                ret.add(cl);
                return ret;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Course> getCourseByName(String name) {
        List<Course> ret = new ArrayList<Course>();
        String sql = "select * from course where name like '%" + name + "%'";
        ResultSet resultset = query(sql);
        Course cl = new Course();
        try {
            while (resultset.next()) {
                cl.setId(resultset.getInt("id"));
                cl.setName(resultset.getString("name"));
                cl.setTeacherId(resultset.getInt("teacher_id"));
                cl.setCoDate(resultset.getString("course_date"));
                cl.setSelectedNum(resultset.getInt("selected_num"));
                cl.setMaxNum(resultset.getInt("max_num"));
                cl.setCoursePlace(resultset.getString("course_place"));
                ret.add(cl);
                return ret;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<Course> getAllCourse() throws SQLException {
//查询所有的课程对象，返回课程对象
        String sql = "select * from course ";
        ResultSet result = query(sql);
        ArrayList<Course> co = new ArrayList<Course>();

        while (result.next()) {
            Course cl = new Course();
            cl.setId(result.getInt("id"));
            cl.setName(result.getString("name"));
            cl.setTeacherId(result.getInt("teacher_id"));
            cl.setCoDate(result.getString("course_date"));
            cl.setSelectedNum(result.getInt("selected_num"));
            cl.setMaxNum(result.getInt("max_num"));
            cl.setCoursePlace(result.getString("course_place"));
            co.add(cl);
        }
        return co;
    }


    public boolean editCourse(Course course) {
        String sql = "update course set name = '" + course.getName() + "',teacher_id = '" + course.getTeacherId() + "',course_date = '" + course.getCoDate() + "',course_place='" + course.getCoursePlace() + "',max_num = '" +
                course.getMaxNum() + "' where id = " + course.getId();
        return update(sql);
    }

    public boolean deleteCourse(String ids) {
        String sql = "delete from course where id in(" + ids + ")";
        return update(sql);
    }

    /**
     * 检查该课程是否已选满
     */
    public boolean isFull(int courseId) {
        boolean ret = false;
        String sql = "select * from course where selected_num >= max_num and id = " + courseId;
        ResultSet query = query(sql);
        try {
            if (query.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 更新课程已选人数
     */
    public void updateCourseSelectedNum(int courseId, int num) {
        //更新选课人数
        String sql = "";
        if (num > 0) {
            sql = "update course set selected_num = selected_num + " + num + " where id = " + courseId;
        } else {
            sql = "update course set selected_num = selected_num - " + Math.abs(num) + " where id = " + courseId;
        }
        update(sql);
    }

}
