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
    <title>查看所有课程</title>

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
<h1>查看课程</h1>
<form>
    <table border="1px" cellspacing="0px" align="center">
        <!--表头-->
        <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>教师id</th>
            <th>上课时间</th>
            <th>上课地点</th>
            <th>已选人数</th>
            <th>总容量</th>
        </tr>
        <%
            courseDAO courseDao = new courseDAO();
            ArrayList<Course> list = courseDao.getAllCourse();
            for (int i = 0; i < list.size(); i++) {
                Course course = list.get(i);
        %>
        <tr>
            <!--循环输出数据-->
            <td><%=course.getId() %>
            </td>
            <td><%=course.getName() %>
            </td>
            <td><%=course.getTeacherId() %>
            </td>
            <td><%=course.getCoDate() %>
            </td>
            <td><%=course.getCoursePlace() %>
            </td>
            <td><%=course.getSelectedNum() %>
            </td>
            <td><%=course.getMaxNum() %>
            </td>

        </tr>
        <%
            }
        %>
    </table>
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
