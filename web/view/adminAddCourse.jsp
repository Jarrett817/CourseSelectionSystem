<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>添加课程</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">


    <style type="text/css">
        body {
            background: url("image/back.jpg");

        }

        p {
            text-align: center;
            color: #000000;
        }

        span {
            color: #000000;
        }

        h1 {
            margin-top: 100px;
            text-align: center;
        }

        #sub {
            margin: 20px auto;
            width: 100px;
            height: 25px;
        }

        a {
            color: #000000;
            font-size: 1.2em;
        }
    </style>

</head>
<%
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<body>
<h1>添加课程</h1>
<form action="/courseSelectionSystem/servlet/courseServlet?action=addCourse" method="post">
    <p><span>课程名：</span><input required="required" name="name"/></p>
    <p><span>教师编号：</span><input required="required" name="teacher_id"/></p>
    <p><span>上课时间：</span><input required="required" name="course_date"/></p>
    <p><span>上课地点：</span><input required="required" name="course_place"/></p>
    <p><span>最大容量：</span><input required="required" name="max_num"/></p>

    <p><input id="sub" type="submit" value="添加"></p>
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
