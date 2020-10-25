package DAO;

import entity.Course;
import entity.Page;
import entity.SelectedCourse;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class selectedCourseDAO extends baseDAO {
    //按id或者课程名查询课程
    public List<Course> getSelectedCourseList(String n) {//为了显示更多信息用Course泛型
        //获得id或者课程名后获取课程的list
        StringUtil stringUtil = new StringUtil();
        courseDAO courseDao = new courseDAO();

        if (stringUtil.isNumberic(n)) {
            //如果是数字用id查询
            return courseDao.getCourseById(Integer.parseInt(n));
        } else {
            return courseDao.getCourseByName(n);
        }

    }

    public List<SelectedCourse> getAllSelectedCourse(int studentId) throws SQLException {
        String sql = "select * from selected_course where student_id=" + studentId;
        ResultSet result = query(sql);
        List<SelectedCourse> sc = new ArrayList<SelectedCourse>();
        while (result.next()) {
            SelectedCourse course = new SelectedCourse();
            course.setStudentId(result.getInt("student_id"));
            course.setCourseId(result.getInt("course_id"));
            course.setId(result.getInt("id"));
            sc.add(course);
        }
        return sc;
    }
//    public int getSelectedCourseListTotal(SelectedCourse selectedCourse){
//        int total = 0;
//        String sql = "select count(*)as total from s_selected_course ";
//        if(selectedCourse.getStudentId() != 0){
//            sql += " and student_id = " + selectedCourse.getStudentId();
//        }
//        if(selectedCourse.getCourseId() != 0){
//            sql += " and course_id = " + selectedCourse.getCourseId();
//        }
//        ResultSet resultSet = query(sql.replaceFirst("and", "where"));
//        try {
//            while(resultSet.next()){
//                total = resultSet.getInt("total");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return total;
//    }

    /**
     * 检查学生是否已经选择该门课程
     */
    public boolean isSelected(int studentId, int courseId) {
        boolean ret = false;
        String sql = "select * from selected_course where student_id = " + studentId + " and course_id = " + courseId;
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
     * 添加选课信息
     */
    public boolean addSelectedCourse(SelectedCourse selectedCourse) {
        String sql = "insert into selected_course values(null," + selectedCourse.getStudentId() + "," + selectedCourse.getCourseId() + ")";
        return update(sql);
    }

    /**
     * 删除所选课程
     */
    public boolean deleteSelectedCourse(int SCid) {
        String sql = "delete from selected_course where id = " + SCid;
        selectedCourseDAO selectedCourseDao = new selectedCourseDAO();
        SelectedCourse selectedCourse = selectedCourseDao.getSelectedCourse(SCid);//获取选择退课的选课对象
        courseDAO courseDao = new courseDAO();

        courseDao.updateCourseSelectedNum(selectedCourse.getCourseId(), -1);
        selectedCourseDao.closeCon();
        courseDao.closeCon();
        return update(sql);
    }

    /**
     * 获取一条选课数据
     */
    public SelectedCourse getSelectedCourse(int id) {
        SelectedCourse ret = null;
        String sql = "select * from selected_course where id = " + id;
        ResultSet query = query(sql);
        try {
            if (query.next()) {
                ret = new SelectedCourse();
                ret.setId(query.getInt("id"));
                ret.setCourseId(query.getInt("course_id"));
                ret.setStudentId(query.getInt("student_id"));
                return ret;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
