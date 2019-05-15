<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/9
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师主页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <!-- 引入JQuery  bootstrap.js-->
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
                        <h1 class="col-md-5">我教授的课程</h1>
                        <form id="searchCourseForm" class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/teacher/searchMyCourse" method="post">
                            <div class="input-group">
                                <input id="courseName" name="courseName" type="text" class="form-control" placeholder="请输入课程名">
                                <span id="search" class="input-group-addon btn" onclick="document.getElementById('searchCourseForm').onsubmit">搜索</span>
                            </div>
                        </form>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>课程号</th>
                            <th>课程名称</th>
                            <th>上课时间</th>
                            <th>上课地点</th>
                            <th>周数</th>
                            <th>课程类型</th>
                            <th>选课人数</th>
                            <th>学分</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schedule}" var="item">
                        <tr>
                            <td>${item.courseID}</td>
                            <td>${item.courseName}</td>
                            <td>${item.courseTime}</td>
                            <td>${item.classroom}</td>
                            <td>${item.weeks}</td>
                            <td>${item.courseType}</td>
                            <td>${item.number}</td>
                            <td>${item.credit}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/teacher/courseGrade?teacher_CourseID=${item.teacher_CourseID}'">成绩</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="/teacher/index?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a> </li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="/teacher/index?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="/teacher/index?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="/teacher/index?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+5 <= pagingVO.totalPageCount}">
                                    <li><a href="/teacher/index?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a></li>
                                </c:if>
                                <li><a href="/teacher/index?page=${pagingVO.totalPageCount}">最后一页&raquo;</a> </li>
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
    if(${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}){
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}){
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    function confirmd() {
        var msg = "您真的确定要删除吗？";
        if (confirmd(msg) == true){
            return true;
        }
        else {
            return false;
        }
    }

    $("#search").click(function () {
        $("#searchCourseForm").submit();
    })
</script>
</html>
