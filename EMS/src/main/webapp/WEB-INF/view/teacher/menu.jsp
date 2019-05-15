<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${pageContext.request.contextPath}/teacher/index">课程列表<span class="fa fa-th-list pull-right"></span></a> </li>
        <li><a href="${pageContext.request.contextPath}/teacher/myCourse">我的课程<span class="fa fa-bars pull-right"></span></a></li>
        <li><a href="${pageContext.request.contextPath}/teacher/curriculum">课程表<span class="fa fa-table pull-right"></span> </a></li>
        <li><a href="${pageContext.request.contextPath}/teacher/passwordReset">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a onclick="logout()">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
        <input type="hidden" id="pageContext" value="${pageContext.request.contextPath}">
    </ul>
</div>