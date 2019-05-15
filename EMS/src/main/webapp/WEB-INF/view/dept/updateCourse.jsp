<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/17
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改课程</title>
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
<input type="hidden" id="msg" name="msg" value="${updateMessage}">
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
                        <h1 style="text-align: center">修改课程信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/dept/updateCourse" id="updateCourseForm" method="post">
                        <div class="form-group">
                            <label for="courseID" class="col-sm-2 control-label">课程号</label>
                            <div class="col-sm-10">
                                <input id="courseID" name="courseID" readonly="readonly" type="number" class="form-control" value="${course.courseID}" placeholder="请输入课程号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">课程名称</label>
                            <div class="col-sm-10">
                                <input id="courseName" name="courseName" type="text" class="form-control" value="${course.courseName}" placeholder="请输入课程名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">课时</label>
                            <div class="col-sm-10">
                                <input id="period" name="period" type="number" class="form-control" value="${course.period}" placeholder="请输入课时">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">课程类型</label>
                            <div class="col-sm-10">
                                <select id="courseType" name="courseType" class="form-control">
                                    <option value="必修课">必修课</option>
                                    <option value="选修课">选修课</option>
                                    <option value="公共课">公共课</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="deptID" class="col-sm-2 control-label">所属院系</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="deptID" id="deptID">
                                    <option value="${deptID}">${course.deptName}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="specialtyID" class="col-sm-2 control-label">专业</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="specialtyID" id="specialtyID">
                                    <c:forEach items="${specialtyList}" var="item">
                                        <option value="${item.specialtyID}">${item.specialtyName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">学分</label>
                            <div class="col-sm-10">
                                <input id="credit" name="credit" type="number" class="form-control" value="${course.credit}" placeholder="请输入学分">
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
    $("#nav li:nth-child(1)").addClass("active")

    var specialty = $("#specialtyID option");
    for (var i = 0;i < specialty.length;i++){
        if (specialty[i].value == '${course.specialty}'){
            specialty[i].selected = true;
        }
    }

    var courseType = $("#courseType option");
    for (var i = 0;i < courseType.length;i++){
        if (courseType[i].value == '${course.courseType}'){
            courseType[i].selected = true;
        }
    }

    function updateAlter() {
        var msg = $("#msg").val();
        if (msg != ""){
            alert("${updateMessage}");
            return true;
        }
        return false;
    }

</script>
</html>
