<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/13
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>院办管理员主页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
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
                        <h1 class="col-md-3">课程名单管理</h1>
                        <form class="col-md-7" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/dept/searchCourse" id="searchCourseForm" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="select" name="specialty">
                                            <c:forEach items="${specialtyList}" var="item">
                                                <option value="${item.specialtyID}">${item.specialtyName}</option>
                                            </c:forEach>
                                            <option value="" selected>--专业--</option>
                                        </select>
                                    </div>
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="year" name="year">
                                            <option value="2015">2015</option>
                                            <option value="2016">2016</option>
                                            <option value="2017">2017</option>
                                            <option value="2018">2018</option>
                                            <option value="2019">2019</option>
                                            <option value="" selected>--年级--</option>
                                        </select>
                                    </div>
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="courseType" name="courseType">
                                            <option value="公共课">公共课</option>
                                            <option value="必修课">必修课</option>
                                            <option value="选修课">选修课</option>
                                            <option value="" selected>--课程类型--</option>
                                        </select>
                                    </div>
                                    <input type="text" class="form-control" placeholder="请输入课程关键字" name="courseName">
                                    <span class="input-group-addon btn" onclick="document.getElementById('searchCourseForm').onsubmit" id="search">搜索</span>
                                    <input type="hidden" id="deptID" name="deptID" value="${courseList.get(0).deptID}">
                                </div>
                            </div>
                        </form>
                        <button class="btn btn-default col-md-1 col-md-offset-1" style="margin-top: 20px" onclick="location.href='${pageContext.request.contextPath}/dept/addCourse?deptID=${dept.deptID}&deptName=${dept.deptName}'">
                            添加课程&nbsp;
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>专业</th>
                        <th>周数</th>
                        <th>课程类型</th>
                        <th>学分</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courseList}" var="item">
                        <tr>
                            <td>${item.courseID}</td>
                            <td>${item.courseName}</td>
                            <td>${item.specialtyName}</td>
                            <td>${item.weeks}</td>
                            <td>${item.courseType}</td>
                            <td>${item.credit}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/dept/updateCourse?courseID=${item.courseID}&deptID=${item.deptID}'">修改</button>
                                <button class="btn btn-default btn-xs btn-danger" onclick="deleteCourse('${item.courseID}')">删除</button>
                                <button class="btn btn-default btn-xs btn-success" onclick="location.href='${pageContext.request.contextPath}/dept/addTeacherCourse?courseID=${item.courseID}&deptID=${item.deptID}'">安排教师</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a> </li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.totalPageCount}">最后一页&raquo;</a> </li>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-lg-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active")

    <c:if test="${pagingVO != null}">
    if (${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    function deleteCourse(courseID){
        var msg = "你确认要删除该课程吗？";
        if (confirm(msg)==true){
            location.href="${pageContext.request.contextPath}/dept/deleteCourse?courseID="+courseID;
            return true;
        }
        else {
            return false;
        }
    }

    $("#search").click(function () {
        $("#searchCourseForm").submit();
    });
</script>
</html>
