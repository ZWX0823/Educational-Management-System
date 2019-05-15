<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/15
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生评价</title>
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
                        <h1 class="col-md-5">学生评价</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="col-sm-1">学号</th>
                        <th class="col-sm-1">姓名</th>
                        <th class="col-sm-1">专业</th>
                        <th class="col-sm-1">班级</th>
                        <th class="col-sm-1">成绩</th>
                        <th class="col-sm-5">评价</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${studentCourseList}" var="item">
                        <tr>
                            <td>${item.studentID}</td>
                            <td>${item.studentExpand.studentName}</td>
                            <td>${item.studentExpand.specialtyName}</td>
                            <td>${item.studentExpand.className}</td>
                            <td>${item.grade}</td>
                            <td>${item.comment}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/dept/seeComments?page=${pagingVO.prePageNo}&teacher_CourseID=${studentCourseList.get(0).teacher_CourseID}">&laquo;上一页</a> </li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/seeComments?page=${pagingVO.currentPageNo+1}&teacher_CourseID=${studentCourseList.get(0).teacher_CourseID}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/seeComments?page=${pagingVO.currentPageNo+2}&teacher_CourseID=${studentCourseList.get(0).teacher_CourseID}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/seeComments?page=${pagingVO.currentPageNo+3}&teacher_CourseID=${studentCourseList.get(0).teacher_CourseID}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/seeComments?page=${pagingVO.currentPageNo+4}&teacher_CourseID=${studentCourseList.get(0).teacher_CourseID}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/seeComments?page=${pagingVO.totalPageCount}&teacher_CourseID=${studentCourseList.get(0).teacher_CourseID}">最后一页&raquo;</a></li>
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
    $("#nav li:nth-child(2)").addClass("active");

</script>
</html>
