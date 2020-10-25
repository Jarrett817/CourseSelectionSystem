package servlet;

import DAO.courseDAO;

import entity.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "courseServlet",urlPatterns="/servlet/courseServlet")
public class courseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
      if("addCourse".equals(action)){
            addCourse(request,response);
        }else if("editCourse".equals(action)){
            editCourse(request,response);
        }else if("deleteCourse".equals(action)){
            deleteCourse(request,response);
        }
    }
    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String coplace=request.getParameter("course_place");
        int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
        int maxNum = Integer.parseInt(request.getParameter("max_num"));
        String courseDate = request.getParameter("course_date");
        //用教师id来获取班级名，教师对应班级
        courseDAO courseDao = new courseDAO();


        //获取表单书数据，并赋值给新的course对象
        Course course = new Course();
        course.setName(name);
        course.setTeacherId(teacherId);
        course.setMaxNum(maxNum);
        course.setCoDate(courseDate);
        course.setCoursePlace(coplace);


        if(courseDao.addCourse(course)){
            request.getSession().setAttribute("message","添加成功！");
            response.sendRedirect(request.getContextPath()+"/view/messageTips/successMessageTips.jsp");

        }else{
            request.getSession().setAttribute("message","添加操作错误，可能是所填的教师编号不存在,请重新输入！");
            response.sendRedirect(request.getContextPath()+"/view/messageTips/messageTips.jsp");
        }
    }
    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String courses[]=request.getParameterValues("courses");//获取前台按钮选中的课程id集合
        System.out.println(courses.length);//长度正确，到这一步值还是取到的，但是下一步循环就没有了
        String k="";
        for(String i:courses){//数组里的取不出来
//            int test=Integer.parseInt(i);//提示For input string: ""错误，i为空字符串
//            System.out.println(test);
            k+=i+",";
        }
        k=k.substring(0,k.length()-1);
        System.out.println(k.length());
        //去除最后一位的逗号

        courseDAO courseDao = new courseDAO();
            if(courseDao.deleteCourse(k)){
                request.getSession().setAttribute("message","删除成功！");
                response.sendRedirect(request.getContextPath()+"/view/messageTips/successMessageTips.jsp");
            }else{
                request.getSession().setAttribute("message","该课程已被学生选课，请重新选择！");
                response.sendRedirect(request.getContextPath()+"/view/messageTips/messageTips.jsp");
            }
    }
    private void editCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
        int maxNum = Integer.parseInt(request.getParameter("max_num"));
        String courseDate = request.getParameter("course_date");
        String coursePlace = request.getParameter("course_place");
        int id=Integer.parseInt(request.getParameter("id"));
//      System.out.println(name);
//        System.out.println(teacherId);
//        System.out.println(maxNum);
//        System.out.println(courseDate);
//        System.out.println(coursePlace);


        courseDAO courseDao = new courseDAO();


        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setTeacherId(teacherId);
        course.setCoDate(courseDate);
        course.setMaxNum(maxNum);
        course.setCoursePlace(coursePlace);
            if (courseDao.editCourse(course)) {
                request.getSession().setAttribute("message", "修改成功！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");
            } else {
                request.getSession().setAttribute("message", "修改操作错误，请重新来过！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips//messageTips.jsp");
            }

    }

//



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
