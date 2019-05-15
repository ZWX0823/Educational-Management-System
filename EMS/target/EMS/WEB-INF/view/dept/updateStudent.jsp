<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/24
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
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
<input type="hidden" id="msg" value="${updateMessage}">
<input type="hidden" id="msg1" value="${addMessage}">
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
                        <h1 style="text-align: center">修改学生信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/updateStudent" id="updateStudentForm" method="post">
                        <div class="form-group">
                            <label for="studentID" class="col-sm-2 control-label">学号</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="studentID" name="studentID" readonly="readonly" value="${student.studentID}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="studentName" class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="studentName" name="studentName" value="${student.studentName}" placeholder="请输入学生姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="studentName" class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="男" <c:if test="${student.sex=='男'}">checked</c:if>>男
                                </label>
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="女" <c:if test="${student.sex=='女'}">checked</c:if>>女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="birthday" class="col-sm-2 control-label">出生年份</label>
                            <div class="col-sm-10">
                                <input type="date" value="<fmt:formatDate value="${student.birthday}" dateStyle="medium" pattern="yyyy-MM-dd"></fmt:formatDate>" name="birthday" id="birthday">
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
                                <input type="number" class="form-control" name="phone" id="phone" value="${student.phone}" placeholder="请输入手机号码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="accountNumber" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" readonly="readonly" class="form-control" name="accountNumber" id="accountNumber" value="${student.accountNumber}" placeholder="请输入账号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="password" id="password" value="${login.password}" placeholder="请输入密码">
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
</body>
<script type="text/javascript">
    $("#nav li:nth-child(3)").addClass("active")

    var specialtySelect = $("#specialty option")
    var classSelect = $("#class option")

    for (var i = 0;i < specialtySelect.length;i++){
        if (specialtySelect[i].value == ${student.specialty}){
            specialtySelect[i].selected = true;
        }
    }
    for (var i = 0;i < classSelect.length;i++){
        if (classSelect[i].value == ${student.classID}){
            classSelect[i].selected = true;
        }
    }

    function updateAlter(){

        var f = $("#msg").val();
        var m = $("#msg1").val();
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
