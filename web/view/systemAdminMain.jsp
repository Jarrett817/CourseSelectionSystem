<%--
  Created by IntelliJ IDEA.
  User: WJR
  Date: 2019/8/6
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>系统管理员主界面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        body {
            background: #AAFFEE url("<%=request.getContextPath()%>/view/image/back.jpg");
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
    <%String s = session.getAttribute("name1").toString();%>
    <h1>欢迎登录，系统管理员<%=s%>
    </h1>
    <p><a href="manageStudent.jsp">学生管理</a></p>
    <p><a href="manageTeacher.jsp">教师管理</a></p>
    <p><a href="manageCourse.jsp">课程管理</a></p>
    <p><a href="manageAdmin.jsp">管理员管理</a></p>
    <p><a href="/courseSelectionSystem/servlet/loginServlet?action=logout">退出登录</a></p>
</div>
</body>
</html>
