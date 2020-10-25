<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="DAO.teacherDAO" %>
<%@ page import="entity.Teacher" %>
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
<h1>修改学生</h1>
<form action="/courseSelectionSystem/servlet/teacherServlet?action=editTeacher&id=<%=request.getParameter("teacher")%>"
      method="post">
    <%
        if (request.getParameter("teacher") != null) {
            int id = Integer.parseInt(request.getParameter("teacher"));//获取学生的id值
            teacherDAO teacherdao = new teacherDAO();
            Teacher teacher = teacherdao.getTeacher(id);
//                通过序号获取了课程对象
    %>

    <hr/>
    <p><span>原工号：<%=teacher.getNumber() %>——修改为：</span><input id="et1" name="number" type="text" placeholder="请输入新的工号"
                                                              required="required"></p>
    <hr/>
    <p><span>原姓名：<%=teacher.getName() %>——修改为：</span><input id="et2" name="name" type="text" placeholder="请输入新的姓名"
                                                            required="required"></p>
    <hr/>
    <p><span>原密码：<%=teacher.getPassword() %>——是否重置：</span><input id="et3" name="password" type="radio" value="重置密码"></p>
    <hr/>
    <p><span>原性别：<%=teacher.getSex()%>——修改为：</span><input id="et5" name="sex" type="text" placeholder="请输入新的性别"
                                                          required="required"></p>
    <hr/>
    <%
        } else {
            request.getSession().setAttribute("message", "请先选择要修改的教师！");
            response.sendRedirect(request.getContextPath() + "/view/systemAdminUpdateTeacher.jsp");
        }
    %>
    <input id="2" type="submit" value="修改">
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
