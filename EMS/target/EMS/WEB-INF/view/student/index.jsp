<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/2/20
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生主页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/dept_specialty.js"></script>
</head>
 <body onload="check()">
 <input type="hidden" id="msg" value="${message}">
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
                         <h1 class="col-md-3">课程列表</h1>
                         <form class="col-md-7" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/student/searchCourse" id="searchCourseForm" method="post">
                             <div class="form-group">
                                 <div class="input-group">
                                     <div class="input-group-btn">
                                         <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" id="select" name="deptID"
                                         onchange="set_specialty(this,this.form.specialty)">
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
                                     <div class="input-group-btn">
                                         <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="specialty" name="specialty">
                                             <option value="" selected>--专业--</option>
                                         </select>
                                     </div>
                                     <div class="input-group-btn">
                                         <select class="form-control" style="width: auto;text-align: center;text-align-last: center;" id="courseType" name="courseType">
                                             <option value="公共课">公共课</option>
                                             <option value="必修课">必修课</option>
                                             <option value="选修课">选修课</option>
                                             <option value="" selected>--课程类型--</option>
                                         </select>
                                     </div>
                                     <input type="text" class="form-control" placeholder="请输入课程名" name="courseName" id="courseName">
                                     <span class="input-group-addon btn" onclick="document.getElementById('searchCourseForm').onsubmit" id="search">搜索</span>
                                 </div>
                             </div>
                         </form>
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
                         <th>学分</th>
                         <th>操作</th>
                     </tr>
                     </thead>
                     <tbody>
                     <c:forEach items="${courseList}" var="item">
                         <tr>
                             <td>${item.courseID}</td>
                             <td>${item.courseName}</td>
                             <td>${item.teacherName}</td>
                             <td>${item.courseTime}</td>
                             <td>${item.classroom}</td>
                             <td>${item.weeks}</td>
                             <td>${item.courseType}</td>
                             <td>${item.credit}</td>
                             <td>
                                 <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/student/selectCourse?teacher_CourseID=${item.teacher_CourseID}'">选课</button>
                             </td>
                         </tr>
                     </c:forEach>
                     </tbody>
                 </table>
                 <div class="panel-footer">
                     <c:if test="${pagingVO != null}">
                         <nav style="text-align: center">
                             <ul class="pagination">
                                 <li><a href="${pageContext.request.contextPath}/student/index?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                 <li class="active"><a href="">${pagingVO.currentPageNo}</a> </li>
                                 <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                     <li><a href="${pageContext.request.contextPath}/student/index?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                 </c:if>
                                 <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                     <li><a href="${pageContext.request.contextPath}/student/index?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                 </c:if>
                                 <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                     <li><a href="${pageContext.request.contextPath}/student/index?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                 </c:if>
                                 <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                     <li><a href="${pageContext.request.contextPath}/student/index?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                 </c:if>
                                 <li><a href="${pageContext.request.contextPath}/student/index?page=${pagingVO.totalPageCount}">最后一页&raquo;</a> </li>
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
    $("#nav li:nth-child(1)").addClass("active")

    <c:if test="${pagingVO != null}">
    if (${pagingVO.currentPageNo} == ${pagingVO.totalPageCount}) {
        $(".pagination li:last-child").addClass("disabled")
    };

    if (${pagingVO.currentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    };
    </c:if>

    $("#search").click(function () {
        $("#searchCourseForm").submit();
    });

    check = function () {
        var msg = $("#msg").val();
        if (msg != ""){
            alert("该门课你已经选过了");
            return true;
        }
    }

</script>
</html>