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
    <title>删除普通管理员</title>

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
<h1>删除普通管理员</h1>
<form action="/courseSelectionSystem/servlet/adminServlet?action=deleteAdmin" method="post">
    <table border="1px" cellspacing="0px" align="center">
        <!--表头-->
        <tr>
            <th>管理员id</th>
            <th>账号</th>
            <th>密码</th>
            <th>删除选定</th>

        </tr>
        <%
            adminDAO adminDao = new adminDAO();
            List<Admin> list = adminDao.showAllAdmin();
            for (Admin admin : list) {
        %>
        <tr>
            <!--循环输出数据-->
            <td><%=admin.getId() %>
            </td>
            <td><%=admin.getName() %>
            </td>
            <td><%=admin.getPassword() %>
            </td>

            <td><input type="checkbox" name="admin" value="<%=admin.getId()%>"></td>
        </tr>
        <%
            }
        %>
    </table>
    <input id="2" type="submit" value="删除该管理员">

</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
