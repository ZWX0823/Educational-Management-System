<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/15
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>专业管理</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
</head>
<body onload="check()">

    <input type="hidden" id="addSpecialtyYearMessage" name="addSpecialtyYearMessage" value="${addSpecialtyYearMessage}">
    <input type="hidden" id="updateSpecialtyYearMessage" name="updateSpecialtyYearMessage" value="${updateSpecialtyYearMessage}">
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
                            <h1 class="col-md-3">专业管理</h1>
                            <form class="col-md-7" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/dept/searchSpecialtyYear" id="searchSpecialtyYearForm" method="post">
                                <div class="form-group">
                                    <div class="form-inline">
                                        <label class="control-label">年级</label>
                                        <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" id="entranceYear" name="entranceYear">
                                            <option value="2015">2015</option>
                                            <option value="2016">2016</option>
                                            <option value="2017">2017</option>
                                            <option value="2018">2018</option>
                                            <option value="2019">2019</option>
                                            <option value="" selected>--年级--</option>
                                        </select>
                                        <label class="control-label">专业</label>
                                        <select class="form-control control" style="width:auto;text-align: center;text-align-last: center;" id="specialty" name="specialty">
                                            <c:forEach items="${specialtyList}" var="item">
                                                <option value="${item.specialtyID}">${item.specialtyName}</option>
                                            </c:forEach>
                                            <option value="" selected>--专业--</option>
                                        </select>
                                        <div class="input-group">
                                            <span class="btn btn-default" onclick="document.getElementById('searchSpecialtyYearForm').onsubmit" id="search">搜索</span>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <button class="btn btn-default col-md-1 col-md-offset-1" style="margin-top: 20px" onclick="location.href='${pageContext.request.contextPath}/dept/addSpecialty_Year'">
                                添加年级专业&nbsp;
                                <span class="fa fa-user-plus"></span>
                            </button>
                        </div>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>年级专业</th>
                            <th>名称</th>
                            <th>人数</th>
                            <th>入学年份</th>
                            <th>是否毕业</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${specialtyYearList}" var="item">
                            <tr>
                                <td>${item.specialty_YearID}</td>
                                <td>${item.specialty_YearName}</td>
                                <td>${item.number}</td>
                                <td><fmt:formatDate value="${item.admissionDate}" dateStyle="medium"></fmt:formatDate></td>
                                <td>
                                    <c:if test="${item.finish == 0}">
                                        否
                                    </c:if>
                                    <c:if test="${item.finish == 1}">
                                        是
                                    </c:if>
                                </td>
                                <td>
                                    <button class="btn btn-default btn-xs btn-info" onclick="location.href='${pageContext.request.contextPath}/dept/updateSpecialty_Year?specialty_YearID=${item.specialty_YearID}'">修改</button>
                                    <button class="btn btn-default btn-xs btn-danger" data-toggle="modal" data-target="#myModal" onclick="addClass('${item.specialty_YearID}','${item.classNumber}')">添加班级</button>
                                    <c:if test="${item.finish == 0}">
                                        <button class="btn btn-default btn-xs btn-primary" onclick="location.href='${pageContext.request.contextPath}/dept/setFinished?specialty_YearID=${item.specialty_YearID}&finish=1'">毕业</button>
                                    </c:if>
                                    <c:if test="${item.finish == 1}">
                                        <button class="btn btn-default btn-xs btn-primary" onclick="location.href='${pageContext.request.contextPath}/dept/setFinished?specialty_YearID=${item.specialty_YearID}&finish=0'">取消</button>
                                    </c:if>
                                    <button class="btn btn-default btn-xs btn-success" onclick="location.href='${pageContext.request.contextPath}/dept/arrangeCourses?specialty_YearID=${item.specialty_YearID}&specialty=${item.specialty}'">安排课程</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="panel-footer">
                        <c:if test="${pagingVO != null}">
                            <nav style="text-align: center">
                                <ul class="pagination">
                                    <li><a href="${pageContext.request.contextPath}/dept/specialty?page=${pagingVO.prePageNo}">&laquo;上一页</a> </li>
                                    <li class="active"><a href="">${pagingVO.currentPageNo}</a></li>
                                    <c:if test="${pagingVO.currentPageNo+1 <= pagingVO.totalPageCount}">
                                        <li><a href="${pageContext.request.contextPath}/dept/specialty?page=${pagingVO.currentPageNo+1}">${pagingVO.currentPageNo+1}</a> </li>
                                    </c:if>
                                    <c:if test="${pagingVO.currentPageNo+2 <= pagingVO.totalPageCount}">
                                        <li><a href="${pageContext.request.contextPath}/dept/specialty?page=${pagingVO.currentPageNo+2}">${pagingVO.currentPageNo+2}</a> </li>
                                    </c:if>
                                    <c:if test="${pagingVO.currentPageNo+3 <= pagingVO.totalPageCount}">
                                        <li><a href="${pageContext.request.contextPath}/dept/specialty?page=${pagingVO.currentPageNo+3}">${pagingVO.currentPageNo+3}</a> </li>
                                    </c:if>
                                    <c:if test="${pagingVO.currentPageNo+4 <= pagingVO.totalPageCount}">
                                        <li><a href="${pageContext.request.contextPath}/dept/specialty?page=${pagingVO.currentPageNo+4}">${pagingVO.currentPageNo+4}</a> </li>
                                    </c:if>
                                    <li><a href="${pageContext.request.contextPath}/dept/specialty?page=${pagingVO.totalPageCount}">最后一页&raquo;</a></li>
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
                    <h4 class="modal-title" id="myModalLabel">班级安排</h4>
                    <input type="hidden" name="temp" id="temp">
                </div>
                <div class="modal-body">
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio0" value="0"> 0
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1"> 1
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="2"> 2
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="3"> 3
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio4" value="4"> 4
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio5" value="5"> 5
                    </label>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="sub()">保存</button>
                </div>
            </div>
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

    $("#search").click(function () {
        $("#searchSpecialtyYearForm").submit();
    });

    function check() {
        var addmsg = $("#addSpecialtyYearMessage").val();
        var updatemsg = $("#updateSpecialtyYearMessage").val();
        if (addmsg != ""){
            alert("${addSpecialtyYearMessage}");
            return true;
        }
        if (updatemsg != ""){
            alert("${updateSpecialtyYearMessage}");
            return true;
        }
    }

    function addClass(specialty_YearID,classNumber) {
        var c = document.getElementsByName('inlineRadioOptions');
        for (var i = 0;i < c.length;i++){
            if (c[i].value == classNumber && classNumber != ""){
                c[i].checked = true;
            }
            else if (classNumber == ""){
                c[0].checked = true;
            }
        }
        var temp = document.getElementById('temp');
        temp.value = specialty_YearID;
    }

    function sub() {
        var classNumber;
        var c = document.getElementsByName('inlineRadioOptions');
        for (var i = 0;i < c.length;i++){
            if (c[i].checked == true){
                classNumber = c[i].value;
            }
        }
        var specialty_YearID = $("#temp").val();
        location.href = "${pageContext.request.contextPath}/dept/addClass?specialty_YearID="+specialty_YearID+"&classNumber="+classNumber;
    }

</script>
</html>
