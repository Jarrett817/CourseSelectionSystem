<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="DAO.clazzDAO" %>
<%@ page import="entity.Clazz" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查看所有班级</title>

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
<h1>查看班级</h1>
<form action="/courseSelectionSystem/servlet/clazzServlet?action=getStudentInClazz" method="post">
    <table border="1px" cellspacing="0px" align="center">
        <!--表头-->
        <tr>
            <th>班级id</th>
            <th>班级名</th>
            <th>已选人数</th>
            <th>选择</th>
        </tr>
        <%
            clazzDAO clazzDao = new clazzDAO();
            ArrayList<Clazz> list = clazzDao.getAllClazz();
            for (Clazz clazz : list) {
        %>
        <tr>
            <!--循环输出数据-->
            <td><%=clazz.getId() %>
            </td>
            <td><%=clazz.getName() %>
            </td>
            <td><%=clazz.getSelectedNum() %>
            </td>
            <td><input type="radio" name="clazzId" value=<%=clazz.getId()%>></td>
        </tr>
        <%}%>
    </table>
    <input type="submit" name="showStudent" value="查看该班级学生">
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
