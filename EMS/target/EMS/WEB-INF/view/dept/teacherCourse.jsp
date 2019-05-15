<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/15
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员主页</title>
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
                        <h1 class="col-md-4">课程名单管理</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>课程号</th>
                        <th>课程名称</th>
                        <th>教师编号</th>
                        <th>教师姓名</th>
                        <th>上课时间</th>
                        <th>上课地点</th>
                        <th>专业</th>
                        <th>周数</th>
                        <th>课程类型</th>
                        <th>学分</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${teacherCourseList}" var="item">
                        <tr>
                            <td>${item.courseID}</td>
                            <td>${item.courseName}</td>
                            <td>${item.teacherID}</td>
                            <td>${item.teacherName}</td>
                            <td>${item.courseTime}</td>
                            <td>${item.classroom}</td>
                            <td>${item.specialtyName}</td>
                            <td>${item.weeks}</td>
                            <td>${item.courseType}</td>
                            <td>${item.credit}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/dept/seeComments?teacher_CourseID=${item.teacher_CourseID}'">查看学生评价</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/dept/searchTeacherCourse?page=${pagingVO.prePageNo}&teacherID=${teacherCourseList.get(0).teacherID}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/searchTeacherCourse?page=${pagingVO.currentPageNo+1}&teacherID=${teacherCourseList.get(0).teacherID}">${pagingVO.currentPageNo+1}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/searchTeacherCourse?page=${pagingVO.currentPageNo+2}&teacherID=${teacherCourseList.get(0).teacherID}">${pagingVO.currentPageNo+2}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/searchTeacherCourse?page=${pagingVO.currentPageNo+3}&teacherID=${teacherCourseList.get(0).teacherID}">${pagingVO.currentPageNo+3}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/searchTeacherCourse?page=${pagingVO.currentPageNo+4}&teacherID=${teacherCourseList.get(0).teacherID}">${pagingVO.currentPageNo+4}</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/searchTeacherCourse?page=${pagingVO.totalPageCount}&teacherID=${teacherCourseList.get(0).teacherID}">最后一页&raquo;</a></li>
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
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    <%--设置菜单中--%>
    $("#nav li:nth-child(2)").addClass("active")
    <c:if test="${pagingVO != null}">
    if (${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>


</script>
</html>
