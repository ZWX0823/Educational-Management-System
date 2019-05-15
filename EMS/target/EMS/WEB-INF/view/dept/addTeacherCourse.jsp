<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/8
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>安排教师</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">


    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>

</head>
<body>
<!--顶部-->
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
                        <form class="bs-example bs-example-form col-md-8" role="form" style="margin: 20px 0 10px 0;" id="searchTeacherForm">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <label class="form-control">${courseName}</label>
                                </div>
                                <input type="text" class="form-control" placeholder="请输入教师姓名" name="teacherName">
                                <span class="input-group-addon btn" onclick="document.getElementById('searchTeacherForm').onsubmit" id="search">搜索</span>
                                <%--<input type="hidden" id="deptID" name="deptID" value="">--%>
                            </div>
                        </form>
                        <button class="btn btn-default col-md-1" style="margin-top: 20px" onclick="arrangeTeachers();">
                            安排教师&nbsp;
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>教师编号</th>
                        <th>教师名称</th>
                        <th>性别</th>
                        <th>出生日期</th>
                        <th>学历</th>
                        <th>职称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${teacherList}" var="item">
                        <tr>
                            <td>${item.teacherID}</td>
                            <td>${item.teacherName}</td>
                            <td>${item.sex}</td>
                            <td><fmt:formatDate value="${item.birthday}" dateStyle="medium"></fmt:formatDate> </td>
                            <td>${item.degree}</td>
                            <td>${item.title}</td>
                            <td>
                                <c:if test="${item.teachOneCourse == true}">
                                    <input type="checkbox"  name="cb" value="${item.teacherID}" checked>
                                </c:if>
                                <c:if test="${item.teachOneCourse == false}">
                                    <input type="checkbox"  name="cb" value="${item.teacherID}">
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.prePageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/index?page=${pagingVO.totalPageCount}">最后一页&raquo;</a></li>
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
    $("#nav li:nth-child(1)").addClass("active")

    arrangeTeachers = function() {
        if (confirm("您确定为该课程安排教师了吗？") == true){
            var list = [];
            var c = document.getElementsByName("cb") ;
            for (var i = 0;i < c.length;i++){
                if (c[i].checked){
                    list.push(c[i].value);
                }
            }
            var obj = {
                "courseID": "${courseID}",
                "teacherIDList": list,
                "deptID": "${teacherList.get(0).deptID}"
            }
            $.ajax(
                {
                    type:'POST',
                    url: '${pageContext.request.contextPath}/dept/addTeacherCourse',
                    dataType:'json',
                    contentType:'application/json',
                    data:JSON.stringify(obj),
                    success:function(data) {
                        alert(data);
                    }
                });
        }
    }

</script>
</html>
