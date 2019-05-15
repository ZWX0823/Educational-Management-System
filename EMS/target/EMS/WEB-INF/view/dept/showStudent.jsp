<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/23
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息显示</title>

    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>

</head>
<body onload="check()">
<%--接收消息--%>
<input type="hidden" id="msg" value="${deleteMessage}">
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
                        <h1 class="col-md-3">学生名单管理</h1>
                        <form class="col-md-7" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/dept/searchStudent" id="searchStudentForm" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="entranceYear" name="entranceYear">
                                            <option value="2015">2015</option>
                                            <option value="2016">2016</option>
                                            <option value="2017">2017</option>
                                            <option value="2018">2018</option>
                                            <option value="2019">2019</option>
                                            <option value="" selected>年级</option>
                                        </select>
                                    </div>
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="specialty" name="specialty">
                                            <c:forEach items="${specialtyList}" var="item">
                                                <option value="${item.specialtyID}">${item.specialtyName}</option>
                                            </c:forEach>
                                            <option value="" selected>--专业--</option>
                                        </select>
                                    </div>
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="classID" name="classID">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="" selected>--班级--</option>
                                        </select>
                                    </div>
                                    <input type="text" class="form-control" placeholder="请输入姓名" name="studentName">
                                    <span class="input-group-addon btn" onclick="document.getElementById('searchStudentForm').onsubmit" id="search">搜索</span>
                                </div>
                            </div>
                        </form>
                        <button class="btn btn-default col-md-1 col-md-offset-1" style="margin-top: 20px" onclick="location.href='${pageContext.request.contextPath}/dept/addStudent'">
                            添加学生&nbsp;
                            <span class="fa fa-user-plus"></span>
                        </button>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>学号</th>
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
                                <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/dept/updateStudent?studentID=${item.studentID}&deptID=${item.dept}'">修改</button>
                                <button class="btn btn-default btn-xs btn-danger" onclick="myconfirm('${item.studentID}')">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO != null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/dept/showStudent?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showStudent?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showStudent?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showStudent?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showStudent?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/showStudent?page=${pagingVO.totalPageCount}">最后一页&raquo;</a></li>
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
    $("#nav li:nth-child(3)").addClass("active");

    function myconfirm(studentID) {
        var msg = "您真的确定要删除该学生并删除他/她选的课程吗？！";
        if (confirm(msg)==true){
            location.href="${pageContext.request.contextPath}/dept/deleteStudent?studentID="+studentID;
            return true;
        }else{
            return false;
        }
    };

    function check(){
        var m = $("#msg").val();
        if(m != ""){
            alert("${deleteMessage}");
            return true;
        }
        return false;
    }

    $("#search").click(function () {
        $("#searchStudentForm").submit();
    });

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
