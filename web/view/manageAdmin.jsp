<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>管理员管理</title>

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
<body>
<div id="wel">
    <h1>管理课程信息</h1>
    <p><a href="showAllAdmin.jsp">查看所有普通管理员信息</a></p>
    <p><a href="systemAdminAddAdmin.jsp">添加管理员</a></p>
    <p><a href="systemAdminDeleteAdmin.jsp">删除管理员</a></p>
    <p><a href="systemAdminUpdateAdmin.jsp">修改管理员</a></p>
    <p><a href="/courseSelectionSystem/servlet/loginServlet?action=logout">退出登录</a></p>
    <p><a href="javascript:history.back(-1)">返回上一页</a></p>
</div>
</body>
</html>
