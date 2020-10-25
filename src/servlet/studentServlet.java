package servlet;

import DAO.clazzDAO;
import DAO.studentDAO;

import entity.Student;
import util.numberUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "studentServlet", urlPatterns = "/servlet/studentServlet")
public class studentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //dopost方法和一系列调用的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if ("addStudent".equals(action)) {
            addStudent(request, response);
        } else if ("editStudent".equals(action)) {
            editStudent(request, response);
        } else if ("deleteStudent".equals(action)) {
            deleteStudent(request, response);
        } else if ("editStuPass".equals(action)) {
            editStuPass(request, response);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
        String student[] = request.getParameterValues("student");//获取前台按钮选中的课程id集合
        String k = "";
        for (String i : student) {
            k += i + ",";
        }
        k = k.substring(0, k.length() - 1);
        System.out.println(k.length());
        //去除最后一位的逗号

        studentDAO studentDao = new studentDAO();
//      clazzDAO clazzdao=new clazzDAO();
        try {
            if (studentDao.deleteStudent(k)) {
//              int stuIdAfter=studentDao.getStuIdByNumber(student.getNumber());
//              clazzdao.addStudentToClazz(stuIdAfter,Integer.valueOf(clazz_id));
//              clazzdao.updateStudentInClazzNum(Integer.valueOf(clazz_id));
                request.getSession().setAttribute("message", "删除成功！");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");
            } else {
                request.getSession().setAttribute("message", "删除失败！可能该学生已选课，请先退课再删除");
                response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editStuPass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oldPass = request.getParameter("oldPass");
        String newPass1 = request.getParameter("newPass1");
        String newPass2 = request.getParameter("newPass2");
        studentDAO studentDao = new studentDAO();
        String stuNumber = (String) request.getSession().getAttribute("stuNumber");//学生number获取
        studentDAO studentdao = new studentDAO();
        Student student = studentdao.login(stuNumber, oldPass);
        if (student == null) {
            request.getSession().setAttribute("message", "密码错误！请重新输入");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");                    //如果不存在，跳转到错误页面
        } else if (studentdao.login(stuNumber, oldPass) != null && newPass1.equals(newPass2)) {
            studentDao.editPassword(student, newPass1);
            request.getSession().setAttribute("message", "修改成功");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");
        }

    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));
        String sex = request.getParameter("sex");


        Student student = new Student();
        student.setName(name);
        student.setNumber(number);
        if (password.equals("重置密码")) {
            student.setPassWord("8888");
        }
        student.setId(id);
        student.setSex(sex);
        studentDAO studentDao = new studentDAO();


        try {
            if (studentDao.editStudent(student)) {
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
    //获取查询结果

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String clazz_id = request.getParameter("clazz_id");
        Student student = new Student();
        //设置学生姓名，密码，性别，账号
        student.setName(name);
        if (request.getParameter("password") != null && request.getParameter("password") != "") {
            student.setPassWord(password);
        } else {
            password = "123456";
            student.setPassWord(password);
        }
        student.setSex(sex);
        student.setNumber(numberUtil.stuNumber());
        student.setClazz_id(Integer.valueOf(clazz_id));
        //调用数据库层的添加学生方法
        studentDAO studentDao = new studentDAO();
        clazzDAO clazzdao = new clazzDAO();
        if (studentDao.addStudent(student)) {
            //添加学生id和班级id到选班表，并在班级表后增加人数
            //获取添加后的学生id
            int stuIdAfter = studentDao.getStuIdByNumber(student.getNumber());
            clazzdao.addStudentToClazz(stuIdAfter, Integer.valueOf(clazz_id));
            clazzdao.updateStudentInClazzNum(Integer.valueOf(clazz_id), 1);
            request.getSession().setAttribute("message", "添加成功！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");

        } else {
            request.getSession().setAttribute("message", "添加操作错误，请重新输入！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
        }
    }


}
