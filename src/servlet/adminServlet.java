package servlet;

import DAO.adminDAO;
import entity.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminServlet", urlPatterns = "/servlet/adminServlet")
public class adminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //dopost方法和一系列调用的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if ("addAdmin".equals(action)) {
            addAdmin(request, response);
        } else if ("editAdmin".equals(action)) {
            editAdmin(request, response);
        } else if ("deleteAdmin".equals(action)) {
            deleteAdmin(request, response);
        }
    }

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) {
        String admin[] = request.getParameterValues("admin");//获取前台按钮选中的课程id集合
        String k = "";
        for (String i : admin) {
            k += i + ",";
        }
        k = k.substring(0, k.length() - 1);
        System.out.println(k.length());
        //去除最后一位的逗号

        adminDAO admindao = new adminDAO();
        try {
            if (admindao.deleteAdmin(k)) {
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

    //  private void editStuPass(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        String oldPass=request.getParameter("oldPass");
//        String newPass1=request.getParameter("newPass1");
//        String newPass2=request.getParameter("newPass2");
//        studentDAO studentDao=new studentDAO();
//       String stuNumber=(String)request.getSession().getAttribute("stuNumber");//学生number获取
//      studentDAO studentdao=new studentDAO();
//      Student student=studentdao.login(stuNumber, oldPass);
//      if(student == null){
//          request.getSession().setAttribute("message","密码错误！请重新输入");
//          response.sendRedirect(request.getContextPath()+"/view/messageTips/editStuPassMessageTips.jsp");                    //如果不存在，跳转到错误页面
//      }else if( studentdao.login(stuNumber, oldPass)!=null&&newPass1.equals(newPass2)){
//          studentDao.editPassword(student,newPass1);
//          request.getSession().setAttribute("message","修改成功");
//          response.sendRedirect(request.getContextPath()+"/view/messageTips/editStuPassMessageTips.jsp");
//      }
//
//  }
    private void editAdmin(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));
        adminDAO adminDao = new adminDAO();

//通过id获取对象
        Admin admin =adminDao.getAdmin(id) ;
        admin.setName(name);
        if (password.equals("重置密码")) {
            admin.setPassword("5555");
        }
        admin.setId(id);
        System.out.print(admin.getPassword());
        try {
            if (adminDao.editAdmin(admin)) {
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

    private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Admin admin = new Admin();
        String level = request.getParameter("level");
        //设置学生姓名，密码，性别，账号
        admin.setName(name);
        if (request.getParameter("password") != null && request.getParameter("password") != "") {
            admin.setPassword(password);
        } else {
            password = "123456";
            admin.setPassword(password);
        }
        if (!level.equals("")) {
            if (Integer.valueOf(level) == 2) {
                admin.setLevel(2);
            }
        }
        //调用数据库层的添加管理员方法
        adminDAO admindao = new adminDAO();
        if (admindao.addAdmin(admin)) {
            request.getSession().setAttribute("message", "添加成功！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/successMessageTips.jsp");

        } else {
            request.getSession().setAttribute("message", "添加操作错误，请重新输入！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips/messageTips.jsp");
        }
    }


}
