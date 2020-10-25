<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误信息提示</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="styles.css">
      <style type="text/css">


          body{
              /*background: url("../image/messagetip.jpg") no-repeat;*/
              text-align:center;
          }
      </style>
  </head>
  
  <body>
  <% String s= (String)session.getAttribute("message");%>
    <h1><%=s%></h1>
  <a href="javascript:history.back(-1)">返回上一页</a>
  </body>
</html>
