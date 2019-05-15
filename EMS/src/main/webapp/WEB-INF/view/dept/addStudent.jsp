<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/25
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
</head>
<body>
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
                        <h1 style="text-align: center">添加学生信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/dept/addCourse" id="addStudentForm" method="post" onsubmit="return check()">
                        <div class="form-group">
                            <label for="studentID" class="col-sm-2 control-label">学号</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="studentID" name="studentID" placeholder="请输入学号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="studentName" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="studentName" name="studentName" placeholder="请输入学生姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="studentName" class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="男" checked>男
                                </label>
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="女">女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="studentName" class="col-sm-2 control-label">出生年份</label>
                            <div class="col-sm-10">
                                <input type="date" value="1996-09-02" name="birthday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dept" class="col-sm-2 control-label">所属院系</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="dept" id="dept">
                                    <option value="${dept.deptID}">${dept.deptName}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="specialty" class="col-sm-2 control-label">专业</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="specialty" id="specialty">
                                    <c:forEach items="${specialtyList}" var="item">
                                        <option value="${item.specialtyID}">${item.specialtyName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="classID" class="col-sm-2 control-label">班级</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="classID" id="classID">
                                    <c:forEach items="${classList}" var="item">
                                        <option value="${item.classID}">${item.className}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="entranceYear" class="col-sm-2 control-label">入学年份</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="entranceYer" id="entranceYear" placeholder="请输入入学年份">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">联系方式</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="phone" id="phone" placeholder="请输入手机号码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="accountNumber" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="accountNumber" id="accountNumber" placeholder="请输入账号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="password" id="password" placeholder="请输入密码">
                                <input type="hidden" id="role" name="role" value="2">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
                            <input type="hidden" id="pageContext" value="${pageContext.request.contextPath}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-sm-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(3)").addClass("active")

    function check() {
        if (addStudentForm.accountNumber.value==""||addStudentForm.accountNumber.value==null){
            alert("请输入用户名");return false;
        }
        if (addStudentForm.password.value==""||addStudentForm.password.value==null){
            alert("请输入密码");return false;
        }
        if (addStudentForm.accountNumber.value != addStudentForm.studentID.value){
            alert("用户名与学生编号不一致，请重新输入");return false;
        }
    }

</script>
</html>
