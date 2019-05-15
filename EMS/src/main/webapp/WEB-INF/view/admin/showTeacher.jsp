<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/26
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师信息显示</title>
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
                        <h1 class="col-md-3">教师名单管理</h1>
                        <form class="col-md-7" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/admin/searchTeacher" id="searchTeacherForm" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" id="select" name="deptID">
                                            <option value="01">文学院</option>
                                            <option value="02">大气科学学院</option>
                                            <option value="04">海洋科学学院</option>
                                            <option value="05">电子与信息工程学院</option>
                                            <option value="06">物理学院</option>
                                            <option value="07">传媒与艺术学院</option>
                                            <option value="08">商学院</option>
                                            <option value="09">法政学院</option>
                                            <option value="10">数学与统计学院</option>
                                            <option value="11">马克思主义学院</option>
                                            <option value="12">体育学院</option>
                                            <option value="13">计算机与科学学院</option>
                                            <option value="" selected>--院系--</option>
                                        </select>
                                    </div>
                                    <input type="text" class="form-control" placeholder="请输入姓名" name="teacherName">
                                    <span class="input-group-addon btn" onclick="document.getElementById('searchTeacherForm').onsubmit" id="search">搜索</span>
                                </div>
                            </div>
                        </form>
                        <button class="btn btn-default col-md-1 col-md-offset-1" style="margin-top: 20px" onclick="location.href='${pageContext.request.contextPath}/admin/addTeacher'">
                            添加教师&nbsp;
                            <span class="fa fa-user-plus"></span>
                        </button>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>教师编号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>出生年份</th>
                        <th>院系</th>
                        <th>学位</th>
                        <th>职称</th>
                        <th>联系方式</th>
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
                            <td>${item.deptName}</td>
                            <td>${item.degree}</td>
                            <td>${item.title}</td>
                            <td>${item.phone}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/admin/updateTeacher?teacherID=${item.teacherID}'">修改</button>
                                <button class="btn btn-default btn-xs btn-danger" onclick="deleteTeacher('${item.teacherID}')">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO!=null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pagingVO.prePageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pagingVO.totalPageCount}">最后一页&raquo;</a> </li>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row"></div>
    <div class="col-md-12"></div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(3)").addClass("active")

    <c:if test="${pagingVO != null}">
    if (${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    function deleteTeacher(teacherID) {
        var msg = "您真的确定要删除该教师并删除他/她教授的课程信息吗？！";
        if (confirm(msg)==true){
            location.href="${pageContext.request.contextPath}/admin/deleteTeacher?teacherID="+teacherID;
            return true;
        }else{
            return false;
        }
    }

    function check(){
        var m = $("#msg").val();
        if (m !=""){
            alert("${deleteMessage}")
            return true;
        }
        return false;
    }

    $("#search").click(function () {
        $("#searchTeacherForm").submit();
    });
</script>
</html>
