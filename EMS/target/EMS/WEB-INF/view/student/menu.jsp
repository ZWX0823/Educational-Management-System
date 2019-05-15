<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/28
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${pageContext.request.contextPath}/student/index">所有课程<span class="fa fa-list pull-right"></span> </a></li>
        <li><a href="${pageContext.request.contextPath}/student/selectedCourse">已选课程<span class="fa fa-check-square-o pull-right"></span> </a></li>
        <li><a href="${pageContext.request.contextPath}/student/finishedCourse">已修课程<span class="fa fa-check-square pull-right"></span> </a></li>
        <li><a href="${pageContext.request.contextPath}/student/curriculum">我的课程表<span class="fa fa-table pull-right"></span> </a> </li>
        <li><a href="${pageContext.request.contextPath}/student/passwordReset">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a onclick="logout()">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
        <input type="hidden" id="pageContext" value="${pageContext.request.contextPath}">
    </ul>
</div>
