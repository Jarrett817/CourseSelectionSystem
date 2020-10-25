package servlet;

import DAO.*;
import entity.Course;
import entity.SelectedCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;


@WebServlet(name = "selectedCourseServlet", urlPatterns = "/servlet/selectedCourseServlet")
public class selectedCourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if ("addSelectedCourse".equals(action)) {
            addSelectedCourse(request, response);
        } else if ("getSelectedCourse".equals(action)) {
            getSelectedCourse(request, response);
        } else if ("deleteSelectedCourse".equals(action)) {
            deleteSelectedCourse(request, response);
        }
    }

    private void deleteSelectedCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] SCIds = request.getParameterValues("courses");//获取所选退课的id值
        selectedCourseDAO selectedCourseDao = new selectedCourseDAO();
//        String Ids="";
        for (String Id : SCIds) {
//            SelectedCourse selectedCourse = selectedCourseDao.getSelectedCourse(Integer.parseInt(Id));//获取选择退课的选课对象
//            courseDAO courseDao = new courseDAO();
//
//            courseDao.updateCourseSelectedNum(selectedCourse.getCourseId(), -1);
//            Ids = Id + ",";
//            Ids = Ids.substring(0, Ids.length() - 1);
            selectedCourseDao.deleteSelectedCourse(Integer.valueOf(Id));
        }
        request.getSession().setAttribute("message", "成功退选!");
        response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");

        selectedCourseDao.closeCon();
    }


    private void addSelectedCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        studentDAO studentDao = new studentDAO();
        selectedCourseDAO selectedcoursedao = new selectedCourseDAO();
        courseDAO coursedao = new courseDAO();
        //获取教师id
        String courseId = request.getParameter("courses");//获取所选选课的id值，还需要学生的id，如何获取？
        String stuNumber = (String) request.getSession().getAttribute("stuNumber");//学生number获取
        int stuId = studentDao.getStuIdByNumber(stuNumber);//学生id到手
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(Integer.parseInt(courseId));
        selectedCourse.setStudentId(stuId);
        //判断是否已经选课
        selectedCourseDAO sc = new selectedCourseDAO();
        if (sc.isSelected(stuId, Integer.parseInt(courseId)) == true) {
            request.getSession().setAttribute("message", "所选课程无法再添加，请重新选择！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
        } else if (coursedao.isFull(Integer.parseInt(courseId))) {
            request.getSession().setAttribute("message", "所选课程已满，请重新选择！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
        } else {
            if (selectedcoursedao.addSelectedCourse(selectedCourse)) {
                //选课成功将学生添加到对应的班级，需要教师号

                request.getSession().setAttribute("message", "添加成功！");
                //还要设置课程的已选人数
                courseDAO courseDao = new courseDAO();
                courseDao.updateCourseSelectedNum(selectedCourse.getCourseId(), 1);
                response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");

            } else {
                request.getSession().setAttribute("message", "添加操作错误，请重新选择！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
            }
        }
    }

    //为了实现搜索显示功能
    private void getSelectedCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String n = request.getParameter("getValues");//获取id或者课程名
        //获取查询方式，
        System.out.println(n);
        System.out.println(name);

        selectedCourseDAO selectedCourseDao = new selectedCourseDAO();
        if ("selectCourseByName".equals(name)) {
            List<Course> courseList1 = selectedCourseDao.getSelectedCourseList(n);//通过名字
            request.getSession().setAttribute("courseList", courseList1);
            response.sendRedirect(request.getContextPath() + "/view/searchCourse.jsp");

        } else if ("selectCourseById".equals(name)) {
            List<Course> courseList2 = selectedCourseDao.getSelectedCourseList(n);
            request.getSession().setAttribute("courseList", courseList2);
            response.sendRedirect(request.getContextPath() + "/view/searchCourse.jsp");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}
