<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/2/26
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师信息修改</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
</head>
<body onload="updateAlter()">
<%--接收消息--%>
<input type="hidden" id="msg" value="${addMessage}">
<input type="hidden" id="msg1" value="${updateMessage}">
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
                        <h1 style="text-align: center;">修改教师信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/updateTeacher" id="updateTeacherForm" method="post">
                        <div class="form-group">
                            <label for="teacherID" class="col-sm-2 control-label">教师编号</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="number" class="form-control" id="teacherID" name="teacherID" value="${teacher.teacherID}" placeholder="请输入教师编号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="teacherName" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="teacherName" name="teacherName" value="${teacher.teacherName}" placeholder="请输入教师姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="teacherName" class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="男" <c:if test="${teacher.sex=='男'}">checked</c:if>>男
                                </label>
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="女" <c:if test="${teacher.sex=='女'}">checked</c:if>>女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="birthday" class="col-sm-2 control-label">出生年份</label>
                            <div class="col-sm-10">
                                <input type="date" value="<fmt:formatDate value="${teacher.birthday}" dateStyle="medium" pattern="yyyy-MM-dd"></fmt:formatDate>"  name="birthday" id="birthday">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="deptID" class="col-sm-2 control-label">院系</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="deptID" id="deptID">
                                    <c:forEach items="${deptList}" var="item">
                                        <option value="${item.deptID}">${item.deptName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="degree" class="col-sm-2 control-label">学历</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="degree" id="degree">
                                    <option value=""></option>
                                    <option value="本科">本科</option>
                                    <option value="硕士">硕士</option>
                                    <option value="博士">博士</option>
                                    <option value="博士后">博士后</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">职称</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="title" id="title">
                                    <option value=""></option>
                                    <option value="助教">助教</option>
                                    <option value="讲师">讲师</option>
                                    <option value="副教授">副教授</option>
                                    <option value="教授">教授</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">联系方式</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="phone" name="phone" value="${teacher.phone}" placeholder="请输入手机号码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="accountNumber" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" readonly="readonly" class="form-control" id="accountNumber" name="accountNumber" value="${teacher.accountNumber}" placeholder="请输入账号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="password" name="password" value="${login.password}" placeholder="请输入密码">
                                <input type="hidden" id="role" name="role" value="1">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
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
    $("#nav li:nth-child(3)").addClass("active")

    var deptSelect = $("#deptID option");
    for (var i = 0;i < deptSelect.length;i++){
        if (deptSelect[i].value == ${teacher.deptID}) {
            deptSelect[i].selected = true;
        }
    }

    var degreeSelect = $("#degree option");
    var titleSelect = $("#title option");

    for (var i = 0;i < degreeSelect.length;i++){
        if (degreeSelect[i].value == '${teacher.degree}'){
            degreeSelect[i].selected = true;
        }
    }
    for (var i = 0;i < titleSelect.length;i++){
        if (titleSelect[i].value == '${teacher.title}'){
            titleSelect[i].selected = true;
        }
    }

    function updateAlter(){

        var f = $("#msg1").val();
        var m = $("#msg").val();
        if (f!=""){
            alert("${updateMessage}");
            return true;
        }
        if (m!=""){
            alert("${addMessage}");
            return true;
        }
        return false;
    }
</script>
</html>
