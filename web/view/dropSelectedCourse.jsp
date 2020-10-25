<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>

<%@ page import="DAO.courseDAO" %>
<%@ page import="DAO.selectedCourseDAO" %>
<%@ page import="entity.SelectedCourse" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>退课</title>

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
<h1>选课</h1>
<form action="/courseSelectionSystem/servlet/selectedCourseServlet?action=deleteSelectedCourse" method="post">
    <table border="1px" cellspacing="0px" align="center">
        <!--表头-->
        <tr>
            <th>选课编号</th>
            <th>课程编号</th>
            <th>课程名</th>
            <th>退课</th>
        </tr>
        <%
            selectedCourseDAO selectedCourseDao = new selectedCourseDAO();
            courseDAO coursedao = new courseDAO();
            //需要获取学生id
            int id = (Integer) request.getSession().getAttribute("studentId");

            List<SelectedCourse> courses = selectedCourseDao.getAllSelectedCourse(id);
            //根据课程id获取课程


            for (SelectedCourse sCurses : courses) {
        %>
        <tr>
            <!--循环输出数据-->
            <td><%=sCurses.getId() %>
            </td>
            <td><%=sCurses.getCourseId() %>
            </td>
            <td><%=coursedao.getOneCourseById(sCurses.getCourseId()).getName()%>
            </td>
            <td><input type="checkbox" name="courses" value="<%=sCurses.getId()%>"></td>
        </tr>
        <%
            }
        %>
    </table>
    <input id="2" type="submit" value="退课">
</form>


<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
