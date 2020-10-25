<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="bookmark" href="favicon.ico"/>
    <link href="<%=request.getContextPath()%>/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/h-ui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet"
          type="text/css"/>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/icon.css">

    <script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/h-ui/js/H-ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/h-ui/lib/icheck/jquery.icheck.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
    <link rel="shortcut icon" href="#"/>
    <%-- 解决找不到favicon.ico--%>

    <script type="text/javascript">
        //设置复选框
        // $(function(){
        $(".skin-minimal input").iCheck({
            radioClass: 'iradio-blue',
            increaseArea: '25%'
        });

        // var xmlHttp;

        function loginCheck() {
            var account = document.getElementById("account");
            var pasword = document.getElementById("password");
            // var type = document.getElementsByName("type");
            if (account.value == "") {
                alert("账号不能为空！");
            } else if (pasword.value == "") {
                alert("密码不能为空！");
            }
            // else {
            //     var data = $("#form").serialize();
            //     $.ajax({
            //         type: "post",
            //         url: "/courseSelectionSystem/servlet/loginServlet?method=login",
            //         data: data,
            //         dataType: "text", //返回数据类型
            //         success: function (msg) {
            //             if ("error1" == msg) {
            //                 $.messager.alert("用户不存在!");
            //             } else if ("error22" == msg) {
            //                 $.messager.alert("密码错误！")
            //             } else if("student"==msg){
            //                 window.location.href = "studentMain.jsp";
            //             }
            //         }
            //
            //     });
            // }
        }

        //     } else {
        //         xmlHttp = createXMLhttp();
        //         var url = "/courseSelectionSystem/servlet/loginServlet?";
        //         xmlHttp.open("POST", url, true);
        //         xmlHttp.onreadystatechange = callback;
        //         var str="login";
        //         xmlHttp.send("action="+str+"&account=" + escape(account.value) + "&password=" + escape(pasword.value) + "&type=" + escape(type.value));
        //     }
        // }
        //
        // function createXMLhttp() {
        //     var xmlHttp;
        //     if (window.XMLHttpRequest) {
        //         xmlHttp = new XMLHttpRequest();
        //     }
        //     if (window.ActiveXObject) {
        //         xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        //         if (!xmlHttp) {
        //             xmlHttp = new ActiveXObject("Msxxml2.XMLHTTP");
        //         }
        //     }
        //     return xmlHttp;
        // }
        //
        // function callback() {
        //     if (xmlHttp.readyState == 4) {
        //         if (xmlHttp.status == 200) {
        //             var result = xmlHttp.responseText;
        //             var result1 = eval("(" + result + ")");
        //             if (result1 == 0) {
        //                 document.getElementById("tip").innerHTML = "用户不存在！";
        //             }else if(result1==1){
        //                 document.getElementById("tip").innerHTML = "密码错误！";
        //             }else{
        //                 window.location.href=result1;
        //             }
        //
        //         }
        //     }
        //
        // }


    </script>
    <title>登录|学生选课系统</title>
    <meta name="keywords" content="学生选课系统">
</head>
<body>

<div class="header" style="padding: 0;">
    <h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">学生选课系统</h2>
</div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form id="form" class="form form-horizontal" action="/courseSelectionSystem/servlet/loginServlet?action=login"
              method="post">

            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-8">
                    <input id="account" name="account" type="text" placeholder="账户" class="input-text size-L"
                           required="required">

                </div>
            </div>


            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-8">
                    <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L"
                           required="required">
                </div>
            </div>


            <div class="mt-20 skin-minimal" style="text-align: center;">
                <div class="radio-box">
                    <input type="radio" id="radio-2" name="type" value="2" checked="true"/><!--//是学生的话提交2-->
                    <label for="radio-1">学生</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="radio-3" name="type" value="3"/>
                    <label for="radio-2">老师</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="radio-1" name="type" value="1"/>
                    <label for="radio-3">管理员</label>
                </div>
            </div>

            <div class="row">
                <div class="formControls col-8 col-offset-3">
                    <input id="submitBtn" type="submit" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;" onclick="loginCheck()">

                </div>
            </div>
            <%--            <div id="tip" style="color:red;padding:10px;font-size:12px"></div>--%>


        </form>

    </div>

</div>


</body>
</html>