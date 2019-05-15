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
                        <h1 class="col-md-3">教师名单管理</h1>
                        <form class="col-md-7" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/dept/searchTeacher" id="searchTeacherForm" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入姓名" name="teacherName">
                                <input type="hidden" name="deptID">
                                <span class="input-group-addon btn" onclick="document.getElementById('searchTeacherForm').onsubmit" id="search">搜索</span>
                            </div>
                        </form>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>教师编号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>出生年份</th>
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
                            <td>${item.degree}</td>
                            <td>${item.title}</td>
                            <td>${item.phone}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/dept/searchTeacherCourse?teacherID=${item.teacherID}'">查看所授课程</button>
                                <c:if test="${item.comment==null}">
                                    <button class="btn btn-default btn-xs btn-info" data-toggle="modal" data-target="#myModal" data-tid="${item.teacherID}" data-whatever="${item.comment}">评价</button>
                                </c:if>
                                <c:if test="${item.comment!=null}">
                                    <button class="btn btn-default btn-xs btn-info" data-toggle="modal" data-target="#myModal" data-tid="${item.teacherID}" data-whatever="${item.comment}">修改评价</button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${pagingVO!=null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                <li><a href="${pageContext.request.contextPath}/dept/showTeacher?page=${pagingVO.prePageNo}">&laquo;上一页</a></li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showTeacher?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showTeacher?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showTeacher?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/dept/showTeacher?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/dept/showTeacher?page=${pagingVO.totalPageCount}">最后一页&raquo;</a> </li>
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
<!--Modal-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">教师评价</h4>
                <input type="hidden" name="tId" id="tId">
            </div>
            <div class="modal-body">
                <textarea class="comments" id="comment" name="comment" style="width: 100%;height:20%" ></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/dept/comment?teacherID='+tId.value+'&comment='+comment.value">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(2)").addClass("active")

    <c:if test="${pagingVO != null}">
    if (${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>


    $("#search").click(function () {
        $("#searchTeacherForm").submit();
    });

    $('#myModal').on('show.bs.modal',function(event){
        var button = $(event.relatedTarget)
        var tId = button.data('tid')
        var recipient = button.data('whatever')

        var modal = $(this)
        modal.find('.modal-header input').val(tId)
        modal.find('.modal-body textarea').val(recipient)
    });
</script>
</html>
