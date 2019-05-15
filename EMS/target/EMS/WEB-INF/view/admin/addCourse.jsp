<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/17
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addCourse</title>

    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/dept_specialty.js"></script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="container body" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center">添加课程</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/addCourse" id="addCourse" method="post">
                        <div class="form-group">
                            <label for="courseID" class="col-sm-2 control-label">课程号</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="courseID" name="courseID" placeholder="请输入课程号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">课程名称</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="courseName" name="courseName" placeholder="请输入课程名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">课时</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="period" name="period" placeholder="请输入课时">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">课程类型</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="courseType" name="courseType">
                                    <option value="" selected></option>
                                    <option value="必修课">必修课</option>
                                    <option value="选修课">选修课</option>
                                    <option value="公共课">公共课</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseName" class="col-sm-2 control-label">所属院系</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="deptID" onchange="set_specialty(this,this.form.specialty)">
                                    <c:forEach items="${deptList}" var="list">
                                        <option value="${list.deptID}">${list.deptName}</option>
                                    </c:forEach>
                                    <option value="" selected></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="specialty" class="col-sm-2 control-label">专业</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="specialty" id="specialty">
                                    <option value="" selected></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="courseID" class="col-sm-2 control-label">学分</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" id="credit" name="credit" placeholder="请输入学分">
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
</script>
</html>
