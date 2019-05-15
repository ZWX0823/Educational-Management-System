<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/5/14
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>安排课程</title>
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
                        <h1 class="col-md-3">安排课程</h1>
                        <form class="bs-example bs-example-form col-md-7" role="form" style="margin: 20px 0 10px 0;" id="arrangeCoursesForm">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <label class="form-control">${specialty_YearName}</label>
                                </div>
                                <input type="text" class="form-control" placeholder="请输入课程名称" name="teacherName">
                                <span class="input-group-addon btn" onclick="document.getElementById('arrangeCoursesForm').onsubmit" id="search">搜索</span>
                                <%--<input type="hidden" id="deptID" name="deptID" value="">--%>
                            </div>
                        </form>
                        <div>
                            <select class="col-md-1" style="width: auto;text-align: center;text-align-last: center;" name="time" id="time" >
                                <option value="10">大一上学期</option>
                                <option value="11">大一下学期</option>
                                <option value="20">大二上学期</option>
                                <option value="21">大二下学期</option>
                                <option value="30">大三上学期</option>
                                <option value="31">大三下学期</option>
                                <option value="40">大四上学期</option>
                                <option value="41">大四下学期</option>
                                <option value="">--学期--</option>
                            </select>
                        </div>
                        <button class="btn btn-default col-md-1" style="margin-top: 20px" onclick="arrangeCourses();">
                            安排课程&nbsp;
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
                                <c:if test="${item.studiedByOneSpecialty == true}">
                                    <input type="checkbox" disabled="disabled" name="cb" value="${item.courseID}" checked>
                                </c:if>
                                <c:if test="${item.studiedByOneSpecialty == false}">
                                    <input type="checkbox"  name="cb" value="${item.courseID}">
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
                                <li><a href="${pageContext.request.contextPath}/dept/arrangeCourses?page=${pagingVO.prePageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/arrangeCourses?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/arrangeCourses?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/arrangeCourses?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a></li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/arrangeCourses?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/arrangeCourses?page=${pagingVO.totalPageCount}">最后一页&raquo;</a></li>
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
    $("#nav li:nth-child(4)").addClass("active");

    <c:if test="${pagingVO != null}">
    if (${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    function arrangeCourses() {
        if (confirm("您确定为该专业安排课程了吗？") == true){
            var list = [];
            var c = document.getElementsByName("cb") ;
            for (var i = 0;i < c.length;i++){
                if (c[i].checked){
                    list.push(c[i].value);
                }
            }
            var obj = {
                "specialty_Year": "${specialty_Year}",
                "courseIDList": list,
                "time": $("#time").val()
            }
            $.ajax(
                {
                    type:'POST',
                    url: '${pageContext.request.contextPath}/dept/arrangeCourses',
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
