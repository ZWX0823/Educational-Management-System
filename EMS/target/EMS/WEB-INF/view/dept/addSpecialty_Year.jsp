<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/15
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加年级专业</title>
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
                        <h1 style="text-align: center">添加年级专业</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/dept/addSpecialty_Year" id="addSpecialty_YearForm" method="post" onsubmit="return check()">
                        <div class="form-group">
                            <label for="dept" class="col-sm-2 control-label">院系</label>
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
                                    <option value="" selected></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="admissionDate" class="col-sm-2 control-label">入学日期</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" name="admissionDate" id="admissionDate">
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
        <div class="col-sm-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(4)").addClass("active")

    function check() {
        if (addSpecialty_YearForm.specialty.value==""||addSpecialty_YearForm.specialty.value==null){
            alert("请选择专业");return false;
        }
        if (addSpecialty_YearForm.admissionDate.value == "" || addSpecialty_YearForm.admissionDate.value == null){
            alert("请选择入学日期");return false;
        }
    }
</script>
</html>
