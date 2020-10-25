<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="entity.Student" %>
<%@ page import="entity.Course" %>
<%@ page import="DAO.courseDAO" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="DAO.studentDAO" %>
<%@ page import="DAO.clazzDAO" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查看改版机内所有学生</title>

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
    <javascript>

    </javascript>

</head>
<%
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");

%>
<body>
<h1>查看该班级内所有学生</h1>
<form action="/courseSelectionSystem/servlet/clazzServlet?action=deleteStudentInClazz" method="post">
    <table border="1px" cellspacing="0px" align="center">
        <!--表头-->
        <tr>
            <th>学生id</th>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>

        </tr>
        <%

            List<Integer> list = (List<Integer>) request.getSession().getAttribute("stuIds");//获取班内学生id集合
            clazzDAO clazzdao = new clazzDAO();
            int k = (Integer) request.getSession().getAttribute("clazzId");
            int m = clazzdao.getStuNum(k);
            for (int i : list) {
                studentDAO studentdao = new studentDAO();
                Student student = studentdao.getStudent(i);

        %>
        <tr>
            <!--循环输出数据-->
            <td><%=student.getId() %>
            </td>
            <td><%=student.getNumber() %>
            </td>
            <td><%=student.getName() %>
            </td>
            <td><%=student.getSex()%>
            </td>

            <td><input type="checkbox" name="studentToDelete" value=<%=student.getId()%>></td>
        </tr>
        <%
            }
        %>

    </table>
    <div>
        <td>班级人数</td>
        <td><%=m%>
        </td>
    </div>
    <p><input id="sub" type="submit" value="踢出"></p>

</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
