<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>教师密码修改</title>

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
<h1>教师修改密码</h1>
<form action="/courseSelectionSystem/servlet/teacherServlet?action=editTeacherPass" method="post">
    <p><span>原密码：</span><input required="required" name="oldPass"/></p>
    <p><span>新密码：</span><input required="required" name="newPass1"/></p>
    <p><span>再次输入新密码：</span><input required="required" name="newPass2"/></p>

    <p><input id="sub" type="submit" value="修改"></p>
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
