package servlet;

import DAO.teacherDAO;

import entity.Teacher;
import util.numberUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "teacherServlet", urlPatterns = "/servlet/teacherServlet")
public class teacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if ("addTeacher".equals(action)) {
            addTeacher(request, response);
        } else if ("editTeacher".equals(action)) {
            editTeacher(request, response);
        } else if ("deleteTeacher".equals(action)) {
            deleteTeacher(request, response);
        } else if ("editTeacherPass".equals(action)) {
            editTeacherPass(request, response);
        }
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) {
        String teacher[] = request.getParameterValues("teacher");//获取前台按钮选中的课程id集合
        String k = "";
        for (String i : teacher) {
            k += i + ",";
        }
        k = k.substring(0, k.length() - 1);
        System.out.println(k.length());
        //去除最后一位的逗号

        teacherDAO teacherDao = new teacherDAO();
        try {
            if (teacherDao.deleteTeacher(k)) {
                request.getSession().setAttribute("message", "删除成功！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");
            } else {
                request.getSession().setAttribute("message", "删除失败！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editTeacherPass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oldPass = request.getParameter("oldPass");
        String newPass1 = request.getParameter("newPass1");
        String newPass2 = request.getParameter("newPass2");
        teacherDAO teacherDao = new teacherDAO();
        String teacherNumber = (String) request.getSession().getAttribute("teacherNumber");//学生number获取
        Teacher teacher = teacherDao.login(teacherNumber, oldPass);
        if (teacher == null) {
            request.getSession().setAttribute("message", "密码错误！请重新输入");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");                    //如果不存在，跳转到错误页面
        } else if (teacherDao.login(teacherNumber, oldPass) != null && newPass1.equals(newPass2)) {
            teacherDao.editPassword(teacher, newPass1);
            request.getSession().setAttribute("message", "修改成功");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");
        }

    }

    private void editTeacher(HttpServletRequest request,
                             HttpServletResponse response) {
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        int id = Integer.parseInt(request.getParameter("id"));


        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setNumber(number);
        teacher.setSex(sex);
        if (password.equals("重置密码")) {
            teacher.setPassword("6666");
        }
        teacher.setId(id);
        teacherDAO teacherDao = new teacherDAO();


        try {
            if (teacherDao.editTeacher(teacher)) {
                request.getSession().setAttribute("message", "修改成功！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");
            } else {
                request.getSession().setAttribute("message", "修改操作错误，请重新来过！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addTeacher(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String number = request.getParameter("number");

        Teacher teacher = new Teacher();
        //设置学生姓名，密码，性别，账号
        teacher.setName(name);
        if (request.getParameter("password") != null && request.getParameter("password") != "") {
            teacher.setPassword(password);
        } else {
            password = "6666";
            teacher.setPassword(password);
        }
        teacher.setSex(sex);
        teacher.setNumber(number);
        teacher.setNumber(numberUtil.teacherNumber());
        //调用数据库层的添加学生方法
        teacherDAO teacherDao = new teacherDAO();
        if (teacherDao.addTeacher(teacher)) {
            request.getSession().setAttribute("message", "添加成功！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");

        } else {
            request.getSession().setAttribute("message", "添加操作错误，请重新输入！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
