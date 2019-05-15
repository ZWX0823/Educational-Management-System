<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/3/21
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${pageContext.request.contextPath}/admin/index">课程管理<span class="fa fa-th-list pull-right"></span> </a> </li>
        <li><a href="${pageContext.request.contextPath}/admin/showStudent">学生管理<span class="glyphicon glyphicon-user pull-right"></span> </a> </li>
        <li><a href="${pageContext.request.contextPath}/admin/showTeacher">教师管理<span class="glyphicon glyphicon-education pull-right"></span> </a> </li>
        <li><a href="${pageContext.request.contextPath}/admin/curriculum">课程表管理<span class="fa fa-table pull-right"></span></a></li>
        <li><a href="${pageContext.request.contextPath}/admin/userPasswordReset">账号密码管理<span class="glyphicon glyphicon-repeat pull-right"></span> </a> </li>
        <li><a href="${pageContext.request.contextPath}/admin/passwordReset">修改密码<span class="glyphicon glyphicon-pencil pull-right"></span> </a> </li>
        <li><a onclick="logout()">退出系统<span class="glyphicon glyphicon-log-out pull-right"></span> </a> </li>
        <li class="disabled"><a href="##">Responsive</a> </li>
        <input type="hidden" id="pageContext" value="${pageContext.request.contextPath}">
    </ul>
</div>
