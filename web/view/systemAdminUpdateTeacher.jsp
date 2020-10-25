<%@ page import="java.util.ArrayList" %>
<%@ page import="DAO.teacherDAO" %>
<%@ page import="entity.Teacher" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>修改教师选定</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        body {
            background: url("<%=request.getContextPath()%>/view/image/back.jpg");
        }

        form {
            text-align: center;
            margin: 30px auto;
            width: 400px;
            border: 1px solid #000;
        }

        p {
            text-align: center;

        }

    </style>

</head>

<body>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=UTF-8");

%>
<h1>修改教师选定</h1>
<form action="view/editTeacherDetail.jsp" method="post">
    <table border="1px" cellspacing="0px" align="center">
        <!--表头-->
        <tr>
            <th>教师id</th>
            <th>工号</th>
            <th>姓名</th>
            <th>修改选定</th>
        </tr>
        <%
            teacherDAO teacherDao = new teacherDAO();
            ArrayList<Teacher> list = teacherDao.getAllTeacher();
            for (Teacher teacher : list) {
        %>
        <tr>
            <!--循环输出数据-->
            <td><%=teacher.getId() %>
            </td>
            <td><%=teacher.getNumber() %>
            </td>
            <td><%=teacher.getName() %>
            </td>
            <td><input type="radio" name="teacher" value="<%=teacher.getId()%>"></td>
        </tr>
        <%
            }
        %>
    </table>
    <input id="2" type="submit" value="修改该教师">


</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>

</body>
</html>
