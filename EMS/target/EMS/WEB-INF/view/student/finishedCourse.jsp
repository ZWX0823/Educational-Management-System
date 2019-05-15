<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/1
  Time: 13:02
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
                        <h1 class="col-md-5">已修课程</h1>
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
                        <th>成绩</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${finishedCourseList}" var="item">
                        <c:if test="${item.over}">
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
                                <td style="color: red">${item.grade}</td>
                                <td>
                                    <c:if test="${item.comment==null}">
                                        <button class="btn btn-default btn-xs btn-info" data-toggle="modal" data-target="#myModal" data-tcid="${item.teacher_CourseID}" data-whatever="${item.comment}">评价</button>
                                    </c:if>
                                    <c:if test="${item.comment!=null}">
                                        <button class="btn btn-default btn-xs btn-info" data-toggle="modal" data-target="#myModal" data-tcid="${item.teacher_CourseID}" data-whatever="${item.comment}">修改评价</button>
                                    </c:if>

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
                                <li><a href="${pageContext.request.contextPath}/student/finishedCourse?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                <li class="active"><a href="">${pagingVO.currentPageNo}</a> </li>
                                <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/finishedCourse?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/finishedCourse?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/finishedCourse?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                </c:if>
                                <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                    <li><a href="${pageContext.request.contextPath}/student/finishedCourse?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/student/finishedCourse?page=${pagingVO.totalPageCount}">最后一页&raquo;</a> </li>
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
<!--Modal-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">课程评价</h4>
                <input type="hidden" name="tcId" id="tcId">
            </div>
            <div class="modal-body">
                <textarea class="comments" id="comment" name="comment" style="width: 100%;height:20%" ></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/student/comment?teacher_CourseID='+tcId.value+'&comment='+comment.value">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    <%--设置菜单中--%>
    $("#nav li:nth-child(3)").addClass("active")
    <c:if test="${pagingVO != null}">
    if(${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}){
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}){
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    $('#myModal').on('show.bs.modal',function(event){
        var button = $(event.relatedTarget)
        var tcId = button.data('tcid')
        var recipient = button.data('whatever')

        var modal = $(this)
        modal.find('.modal-header input').val(tcId)
        modal.find('.modal-body textarea').val(recipient)
    });

    <%--function sub() {--%>
    <%--    var modal = document.getElementById("myModal");--%>
    <%--    var teacher_CourseID = modal.tcId.value;--%>
    <%--    var comment = modal.comment.value;--%>

    <%--    $.ajax(--%>
    <%--        {--%>
    <%--            type:'POST',--%>
    <%--            url: '${pageContext.request.contextPath}/student/comment',--%>
    <%--            dataType:'json',--%>
    <%--            contentType:'application/json',--%>
    <%--            data:{"teacher_CourseID":teacher_CourseID,"comment":comment},--%>
    <%--            success:function(data) {--%>
    <%--                alert(data);--%>
    <%--            }--%>
    <%--        });--%>
    <%--}--%>
</script>
</html>
