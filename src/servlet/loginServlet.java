package servlet;

import DAO.adminDAO;
import DAO.studentDAO;
import DAO.teacherDAO;
import entity.Admin;
import entity.Student;
import entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/servlet/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");//获取method的值
        String path = request.getContextPath();

        if ("login".equals(action)) {

            String name = request.getParameter("account");
            String password = request.getParameter("password");//抓取表单上的账号和密码

            int type = Integer.parseInt(request.getParameter("type"));//获取表单上对应的数字
            switch (type) {
                case 1: {
                    adminDAO adminDao = new adminDAO();
                    Admin admin = adminDao.login(name, password);
//                    System.out.print(admin.getLevel());
                    if (adminDao.checkUser(name) == false) {
                        request.getSession().setAttribute("message", "用户不存在！");
                        response.sendRedirect(path + "/view/messageTips/loginTips.jsp");                    //如果不存在，跳转到错误页面
                    } else if (adminDao.checkPassword(name, password) == false) {
                        request.getSession().setAttribute("message", "密码错误！");
                        response.sendRedirect(path + "/view/messageTips/loginTips.jsp");
                    } else {
                        if (admin.getLevel() == 1) {
                            request.getSession().setAttribute("name4", admin.getName());

                            response.sendRedirect(path + "/view/adminMain.jsp");
                        } else {
                            request.getSession().setAttribute("name1", admin.getName());
                            response.sendRedirect(path + "/view/systemAdminMain.jsp");
                        }
                    }
                    adminDao.closeCon();
                    break;
                }

                case 2: {
                    studentDAO studentdao = new studentDAO();
                    Student student = studentdao.login(name, password);
                    if (studentdao.checkUser(name) == false) {
                        request.getSession().setAttribute("message", "用户不存在！");
                        response.sendRedirect(path + "/view/messageTips/loginTips.jsp");                    //如果不存在，跳转到错误页面
//                        response.getWriter().write("error1");
//                    pw.print(0);
                    } else if (studentdao.checkPassword(name, password) == false) {
                        request.getSession().setAttribute("message", "密码错误！");
                        response.sendRedirect(path + "/view/messageTips/loginTips.jsp");
//                        response.getWriter().write("error2");
//                    pw.print(1);
                    } else {
                        //写一个用于保存学生账号number的session
                        request.getSession().setAttribute("stuNumber", student.getNumber());
//                    int stuId = studentdao.getStuIdByNumber(namame);

                        System.out.println(student.getId());
                        request.getSession().setAttribute("studentId", student.getId());

                        //保存学生名的session,
//                    String stuName = studentdao.getStuNameByNumber(name);
                        request.getSession().setAttribute("name2", student.getName());
                        response.sendRedirect(path + "/view/studentMain.jsp");
//                        pw.print("/view/studentMain.jsp");
//                        response.getWriter().write("student");
                    }
                    studentdao.closeCon();

                    break;
                }

                case 3: {
                    teacherDAO teacherDao = new teacherDAO();
                    Teacher teacher = teacherDao.login(name, password);
                    if (teacherDao.checkUser(name) == false) {
                        request.getSession().setAttribute("message", "用户不存在！");
                        response.sendRedirect(path + "/view/messageTips/loginTips.jsp");
                    } else if (teacherDao.checkPassword(name, password) == false) {
                        request.getSession().setAttribute("message", "密码错误！");
                        response.sendRedirect(path + "/view/messageTips/loginTips.jsp");
                    } else {
                        request.getSession().setAttribute("teacherNumber", teacher.getNumber());
                        request.getSession().setAttribute("name3", teacher.getName());
                        response.sendRedirect(path + "/view/teacherMain.jsp");
                    }
                    teacherDao.closeCon();

                    break;
                }
                default:
                    break;
            }
        } else if ("logout".equals(action)) {
            response.sendRedirect(path + "/view/login.jsp");
            request.getSession().invalidate();//退出登录销毁session
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }
}
