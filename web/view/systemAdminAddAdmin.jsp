<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="util.numberUtil" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>添加管理员</title>

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
<h1>添加管理员</h1>
<form action="/courseSelectionSystem/servlet/adminServlet?action=addAdmin" method="post">
    <p><span>密码：</span><input name="password" placeholder="不输入即为默认123456"/></p>
    <p><span>姓名(即账号)：</span><input required="required" name="name"/></p>
    <p><span>是否为系统管理员：</span><input type="checkbox" name="level" value="2"/></p>


    <p><input id="sub" type="submit" value="添加"></p>
</form>
<p><a href="javascript:history.back(-1)">返回上一页</a></p>
</body>
</html>
