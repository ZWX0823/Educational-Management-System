<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/28
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户密码重置</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
</head>
<body onload="check1()">
<!--顶栏-->
<jsp:include page="top.jsp"></jsp:include>
<!--中间主体-->
<div class="container body" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center">重置其他用户密码</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" name="reset" role="form" action="${pageContext.request.contextPath}/dept/userPasswordReset" id="editForm" method="post" onsubmit="return check()">
                        <div class="form-group">
                            <label for="accountNumber" class="col-sm-2 control-label">用户名(非管理员)</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="accountNumber" id="accountNumber" placeholder="请输入账号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="newPassword" id="password" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password2" class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="newPassword2" id="password2" placeholder="请再次输入密码">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <input type="hidden" name="message" id="message" value="${message}">
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(7)").addClass("active")
    
    function check() {
        if (reset.accountNumber.value==""||reset.accountNumber.value==null){
            alert("请输入用户名");return false;
        }
        if (reset.newPassword.value==""||reset.newPassword.value==null){
            alert("请输入新密码");return false;
        }
        if (reset.newPassword2.value==""||reset.newPassword2.value==null){
            alert("请再次输入新密码");return false;
        }
        if (reset.newPassword.value != reset.newPassword2.value){
            alert("两次密码不一致");return false;
        }
    }

    function check1() {

        var f = reset.message.value;
        if (f!=""){
            alert("${message}");
            return true;
        }
        return false;
    }
</script>
</html>
