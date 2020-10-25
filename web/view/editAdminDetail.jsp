<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="DAO.adminDAO" %>
<%@ page import="entity.Admin" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>修改管理员</title>

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
<h1>修改管理员</h1>
<form action="/courseSelectionSystem/servlet/adminServlet?action=editAdmin&id=<%=request.getParameter("admin")%>"
      method="post">
    <%
        if (request.getParameter("admin") != null) {
            int id = Integer.parseInt(request.getParameter("admin"));
            adminDAO admindao = new adminDAO();
            Admin admin = admindao.getAdmin(id);
    %>

    <hr/>
    <p><span>原账户：<%=admin.getName() %>——修改为：</span><input id="et2" name="name" type="text" placeholder="请输入新的账户名"
                                                          required="required"></p>
    <hr/>
    <p><span>原密码：<%=admin.getPassword() %>——是否重置：</span><input id="et3" name="password" type="radio" value="重置密码"></p>
    <hr/>

    <%
        } else {
            request.getSession().setAttribute("message", "请先选择要修改的管理员！");
            response.sendRedirect(request.getContextPath() + "/view/messageTips.jsp");
        }
    %>
    <input id="2" type="submit" value="修改">
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
