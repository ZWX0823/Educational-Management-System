<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/5/15
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理</title>
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
                        <h1 class="col-md-3">班级学生</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>班级</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>出生年份</th>
                        <th>专业</th>
                        <th>班级</th>
                        <th>入学年份</th>
                        <th>联系方式</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${studentList}" var="item">
                        <tr>
                            <td>${item.studentID}</td>
                            <td>${item.studentName}</td>
                            <td>${item.sex}</td>
                            <td><fmt:formatDate value="${item.birthday}" dateStyle="medium"></fmt:formatDate></td>
                            <td>${item.specialtyName}</td>
                            <td>${item.className}</td>
                            <td>${item.entranceYear}</td>
                            <td>${item.phone}</td>
                            <td>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/dept/getDetailInfoOfClass?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/getDetailInfoOfClass?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/getDetailInfoOfClass?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/getDetailInfoOfClass?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/getDetailInfoOfClass?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/getDetailInfoOfClass?page=${pagingVO.totalPageCount}">最后一页&raquo;</a></li>
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
    $("#nav li:nth-child(5)").addClass("active");
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
