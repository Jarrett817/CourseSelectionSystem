<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>管理员主界面</title>

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
    <%String s = (String) session.getAttribute("name4");%>

    <h1>欢迎登录，管理员<%=s%>
    </h1>
    <p><a href="manageCourse.jsp">课程管理</a></p>

    <p><a href="/courseSelectionSystem/servlet/loginServlet?action=logout">退出登录</a></p>
</div>
</body>
</html>
