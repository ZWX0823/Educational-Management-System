<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/31
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程信息显示</title>

    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
</head>
<body onload="check()">
<%--接收消息--%>
<input type="hidden" id="msg" name="msg" value="${message}">
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
                        <h1 class="col-md-5">已选课程</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>课程号</th>
                        <th>课程名称</th>
                        <th>授课教师</th>
                        <th>上课时间</th>
                        <th>上课地点</th>
                        <th>周数</th>
                        <th>课程类型</th>
                        <th>开设院系</th>
                        <th>学分</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${selectedCourseList}" var="item">
                        <%--显示没有修完的数据--%>
                        <c:if test="${!item.over}">
                            <tr>
                                <td>${item.teacherCourseExpand.courseID}</td>
                                <td>${item.courseName}</td>
                                <td>${item.teacherCourseExpand.teacherExpand.teacherName}</td>
                                <td>${item.courseTime}</td>
                                <td>${item.classroom}</td>
                                <td>${item.teacherCourseExpand.courseExpand.weeks}</td>
                                <td>${item.teacherCourseExpand.courseExpand.courseType}</td>
                                <td>${item.teacherCourseExpand.courseExpand.deptName}</td>
                                <td>${item.teacherCourseExpand.courseExpand.credit}</td>
                                <td>
                                    <button class="btn btn-default btn-xs btn-info" onclick="revokeCourse()">退课</button>
                                    <input name="TCID" id="TCID" type="hidden" value="${item.teacher_CourseID}">
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/student/selectedCourse?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a> </li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/selectedCourse?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/selectedCourse?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/selectedCourse?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/selectedCourse?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/student/selectedCourse?page=${pagingVO.totalPageCount}">最后一页&raquo;</a> </li>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </div>
        </div>
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

    function revokeCourse() {
        var msg = "您真的确定要退选该门课程吗？";
        if (confirm(msg)==true){
            var teacher_CourseID = $("#TCID").val();
            location.href="${pageContext.request.contextPath}/student/revokeCourse?teacher_CourseID="+teacher_CourseID;
            return true;
        }else{
            return false;
        }
    }
    
    function check() {
        var msg = $("#msg").val();
        if (msg != ""){
            alert("${message}");
            return true;
        }
        return false;
    }
</script>
</html>
