<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="entity.Course" %>
<%@ page import="DAO.courseDAO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>管理员登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <style type="text/css">
        body {
            background: url("image/back.jpg");
            text-align: center;

        }

        #wel {
            margin: 100px 400px 0 400px;
            border: 1px solid #000;
        }

        a {
            font-size: 1.5em;
            background: #BBFFEE;
        }

        a:hover {
            background: #00BBFF;
        }
    </style>

</head>
<%
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<body>
<h1>修改课程</h1>
<form action="/courseSelectionSystem/servlet/courseServlet?action=editCourse&id=<%=request.getParameter("courses")%>"
      method="post">
    <%
        if (request.getParameter("courses") != null) {
            int id = Integer.parseInt(request.getParameter("courses"));//获取课程的id值
            courseDAO coursedao = new courseDAO();
            Course course = coursedao.getOneCourseById(id);
//                通过序号获取了课程对象

    %>

    <p><span>原课程名：<%=course.getName()%>——修改为：</span><input id="c2" name="name" type="text" placeholder="请输入新的课程名"
                                                           required="required"></p>
    <hr/>
    <p><span>原教师id：<%=course.getTeacherId() %>——修改为：</span><input id="c3" name="teacher_id" type="text"
                                                                  placeholder="请输入新的教师id" required="required"></p>
    <hr/>
    <p><span>原上课时间：<%=course.getCoDate() %>——修改为：</span><input id="c4" name="course_date" type="text"
                                                               placeholder="请输入新的上课时间" required="required"></p>
    <hr/>
    <p><span>原上课地点：<%=course.getCoursePlace() %>——修改为：</span><input id="c5" name="course_place" type="text"
                                                                    placeholder="请输入新的上课地点" required="required"></p>
    <hr/>
    <p><span>原最大容量：<%=course.getMaxNum()%>——修改为：</span><input id="c6" name="max_num" type="text" placeholder="请输入新的最大容量"
                                                              required="required"></p>
    <hr/>
    <%
        } else {
            request.getSession().setAttribute("message", "请先选择要修改的课程！");
            response.sendRedirect(request.getContextPath() + "/view//messageTips.jsp");
        }
    %>
    <input id="2" type="submit" value="修改">
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
