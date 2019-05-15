<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/2/20
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登陆</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <!--引入bootstrap-->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <!--引入Awesome-->
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">

    <!-- 引入JQuery  bootstrap.js-->
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background-image: url('./img/d.png');
        }
        #login-box {
            /*border:1px solid #F00;*/
            padding: 35px;
            border-radius:15px;
            background: #8B91A0;
            filter:alpha(Opacity=70);-moz-opacity:0.7;opacity: 0.7;
        }

    </style>
</head>
<body>
    <div class="container" id="top">
        <div class="row" style="margin-top: 280px;">
            <div class="col-md-4 col-md-offset-4" id="login-box">
                <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/login" id="form-login" method="post">
                    <div class="form-group has-feedback wrapper">
                        <div class="username">

                            <input id="accountNumber" name="accountNumber" type="text" class="form-control" placeholder="用户名">
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <div class="password">

                            <input id="password" name="password" type="password" class="form-control" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="checkbox col-sm-4 col-md-4 col-lg-4">
                            <label>
                                <input type="checkbox" name="rememberme">记住密码
                            </label>
                        </div>
                        <label for="" class="col-sm-4 col-md-4 col-lg-4 control-label forget-pass">
                            <a href="">忘记密码</a>
                        </label>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-login btn-primary" type="submit">登&nbsp;录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
