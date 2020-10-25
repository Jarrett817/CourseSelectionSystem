<%@ page import="DAO.courseDAO" %>
<%@ page import="entity.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>修改课程</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        body {
            background: url("<%=request.getContextPath()%>/view/image/back.jpeg");
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
<h1>修改课程选定</h1>
<form action="view/editCourseDetail.jsp" method="post">
    <table border="1px" cellspacing="0px" align="center">
        <!--表头-->
        <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>修改选定</th>
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
            <td><input type="radio" name="courses" value="<%=course.getId()%>"></td>
        </tr>
        <%
            }
        %>
    </table>
    <input id="2" type="submit" value="修改该课程">


</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>

</body>
</html>
