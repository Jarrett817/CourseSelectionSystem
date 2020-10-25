package servlet;

import DAO.clazzDAO;
import entity.Clazz;
import entity.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@WebServlet(name = "clazzServlet",urlPatterns="/servlet/clazzServlet")
public class clazzServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if("getStudentInClazz".equals(action)){
            getStudentInClazz(request, response);
        }else if( "deleteStudentInClazz".equals(action)){
            deleteStudentInClazz(request,response);
        }
    }
    public void getStudentInClazz(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //首先获取选中的班级id
        int clazzId=Integer.parseInt(request.getParameter("clazzId"));
        //用班级号去获取学生号
        request.getSession().setAttribute("clazzId",clazzId);
        clazzDAO clazzdao=new clazzDAO();
        List<Integer> studentIds=clazzdao.getStudentIdByClazzId(clazzId);//获取班级内学生id集合
        request.getSession().setAttribute("stuIds",studentIds);
        response.sendRedirect(request.getContextPath()+"/view/showAllStudentInClazz.jsp");
    }
    public void deleteStudentInClazz(HttpServletRequest request, HttpServletResponse response){
        int stuId=Integer.parseInt(request.getParameter("studentToDelete"));//
        //获取到了该班级中要删除的学生id，调用dao删除
        clazzDAO clazzdao=new clazzDAO();

        try {
            if(clazzdao.deleteStudentInClazz(stuId)){
                clazzdao.updateStudentInClazzNum((int)request.getSession().getAttribute("clazzId"),-1);
                request.getSession().setAttribute("message","删除成功！");
                response.sendRedirect(request.getContextPath()+"/view/messageTips/successMessageTips.jsp");
            }else{
                request.getSession().setAttribute("message","删除失败，请检查！");
                response.sendRedirect(request.getContextPath()+"/view/messageTips/messageTips.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
