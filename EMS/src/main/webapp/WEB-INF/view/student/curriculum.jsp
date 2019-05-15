<%--
  Created by IntelliJ IDEA.
  User: ZWX
  Date: 2019/4/9
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/dept_specialty.js"></script>
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
                        <h1 class="col-md-3">课程表显示</h1>
                        <form class="bs-example bs-example-form col-md-7" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/student/searchCurriculum" id="searchCurriculumForm" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" name="dept" id="dept"
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
                                        <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" name="specialty" id="specialty">
                                            <option value="" selected>--专业--</option>
                                        </select>
                                    </div>
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" name="grade" id="grade">
                                            <option value="2015">2015</option>
                                            <option value="2016">2016</option>
                                            <option value="2017">2017</option>
                                            <option value="2018">2018</option>
                                            <option value="2019">2019</option>
                                            <option value="" selected>--入学年级--</option>
                                        </select>
                                    </div>
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" name="year" id="year">
                                            <option value="2015">2015-2016学年</option>
                                            <option value="2016">2016-2017学年</option>
                                            <option value="2017">2017-2018学年</option>
                                            <option value="2018">2018-2019学年</option>
                                            <option value="" selected>--学年--</option>
                                        </select>
                                    </div>
                                    <div class="input-group-btn">
                                        <select class="form-control" style="width:auto;text-align: center;text-align-last: center;" name="half" id="half">
                                            <option value="0">1</option>
                                            <option value="1">2</option>
                                            <option value="" selected>--学期--</option>
                                        </select>
                                    </div>
                                    <span class="btn btn-default" onclick="document.getElementById('searchCurriculumForm').onsubmit" id="search">搜索</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <form id="scheduleForm" name="scheduleForm" action="" method="post">
                    <!-- <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="dfdfdf" style="text-align: left">
                         <tbody><tr>
                             <td class="personaddbg1" colspan="7">

                                 <div id="StudentJZ1_UpdatePanel1">
                                     <table align="left" width="800">
                                         <tbody><tr>
                                             <td style="width: 60px" class="personaddbg1">
                                                 系部</td>
                                             <td style="width: 100px" bgcolor="#ffffff">
                                                 <select name="StudentJZ1$DropDownListXY" id="StudentJZ1_DropDownListXY" style="width:150px;">
                                                     <option value="00">教务处</option>
                                                     <option value="01">大气科学学院</option>
                                                     <option value="02">环境科学与工程学院</option>
                                                     <option value="03">电子与信息工程学院</option>
                                                     <option selected="selected" value="04">文学院</option>
                                                     <option value="05">数学与统计学院</option>
                                                     <option value="06">信息与控制学院</option>
                                                     <option value="07">计算机与软件学院</option>
                                                     <option value="08">体育部</option>
                                                     <option value="09">管理工程学院</option>
                                                     <option value="10">应用气象学院</option>
                                                     <option value="11">法政学院</option>
                                                     <option value="12">地理科学学院</option>
                                                     <option value="13">遥感与测绘工程学院</option>
                                                     <option value="14">传媒与艺术学院</option>
                                                     <option value="15">图书馆</option>
                                                     <option value="16">气象台</option>
                                                     <option value="17">成人教育学院</option>
                                                     <option value="18">应用技术学院</option>
                                                     <option value="19">滨江学院</option>
                                                     <option value="20">雷丁学院</option>
                                                     <option value="21">商学院</option>
                                                     <option value="22">人武部</option>
                                                     <option value="23">大学英语部</option>
                                                     <option value="24">马克思主义学院</option>
                                                     <option value="25">团委</option>
                                                     <option value="26">学工处</option>
                                                     <option value="27">大气物理学院</option>
                                                     <option value="28">高教所</option>
                                                     <option value="29">宣传部</option>
                                                     <option value="30">水文与水资源工程学院</option>
                                                     <option value="31">物理与光电工程学院</option>
                                                     <option value="32">海洋科学学院</option>
                                                     <option value="33">长望学院</option>
                                                     <option value="34">龙山书院</option>
                                                     <option value="35">大气与环境实验教学中心</option>
                                                     <option value="36">自动化学院</option>
                                                     <option value=" "> </option>

                                                 </select></td>
                                             <td style="width: 60px" class="personaddbg1">
                                                 专业</td>
                                             <td style="width: 100px" bgcolor="#ffffff">
                                                 <select name="StudentJZ1$DropDownListZYMC"  id="StudentJZ1_DropDownListZYMC" style="width:200px;">
                                                     <option selected="selected" value="1364">1364_对外汉语</option>
                                                     <option value="1354">1354_对外汉语（汉日双语方向）</option>
                                                     <option value="1353">1353_对外汉语（汉英双语方向）</option>
                                                     <option value="1374">1374_翻译</option>
                                                     <option value="1386">1386_汉语国际教育</option>
                                                     <option value="1392">1392_汉语国际教育(汉英双语方向)</option>
                                                     <option value="1313">1313_汉语言文学</option>
                                                     <option value="1355">1355_汉语言文学（高级文秘方向）</option>
                                                     <option value="1360">1360_汉语言文学（文化创意与设计方向）</option>
                                                     <option value="1322">1322_日语</option>
                                                     <option value="1310">1310_英语</option>
                                                     <option value="1351">1351_英语（国际商务方向）</option>
                                                     <option value="1352">1352_英语（实用翻译方向）</option>
                                                     <option value=" "> </option>

                                                 </select></td>
                                             <td style="width: 60px" class="personaddbg1">
                                                 年级</td>
                                             <td style="width: 100px" bgcolor="#ffffff">
                                                 <select name="StudentJZ1$DropDownListDQSZJ" id="StudentJZ1_DropDownListDQSZJ" style="width:100px;">
                                                     <option value="2018">2018</option>
                                                     <option value="2016">2016</option>
                                                     <option value="2014">2014</option>
                                                     <option value="2017">2017</option>
                                                     <option value="2010">2010</option>
                                                     <option value="2011">2011</option>
                                                     <option value="2002">2002</option>
                                                     <option value="2013">2013</option>
                                                     <option value="2004">2004</option>
                                                     <option value="2005">2005</option>
                                                     <option value="2006">2006</option>
                                                     <option value="2007">2007</option>
                                                     <option value="2008">2008</option>
                                                     <option value="2009">2009</option>
                                                     <option value="2012">2012</option>
                                                     <option value="2015">2015</option>
                                                     <option selected="selected" value=" "> </option>

                                                 </select></td>
                                             <td style="width: 60px" class="personaddbg1">
                                                 班级</td>
                                             <td style="width: 100px" bgcolor="#ffffff">
                                                 <select name="StudentJZ1$DropDownListXZB" id="StudentJZ1_DropDownListXZB" style="width:150px;">
                                                     <option selected="selected" value="汉日11(1)班">汉日11(1)班</option>
                                                     <option value="汉日12(1)班">汉日12(1)班</option>
                                                     <option value="汉韩09(1)班">汉韩09(1)班</option>
                                                     <option value="汉日09(1)班">汉日09(1)班</option>
                                                     <option value="汉日10(1)班">汉日10(1)班</option>
                                                     <option value=" "> </option>

                                                 </select></td>
                                         </tr>
                                         </tbody></table>
                                 </div>

                             </td>
                         </tr>
                         <tr>
                             <td class="personaddbg1">
                                 学年</td>
                             <td bgcolor="#ffffff" class="trbg2" height="27">
                                 <select name="XN1$DropDownListXN" id="XN1_DropDownListXN" style="width:110px;">
                                     <option value="2005-2006">2005-2006</option>
                                     <option value="2006-2007">2006-2007</option>
                                     <option value="2007-2008">2007-2008</option>
                                     <option value="2008-2009">2008-2009</option>
                                     <option value="2009-2010">2009-2010</option>
                                     <option value="2010-2011">2010-2011</option>
                                     <option value="2011-2012">2011-2012</option>
                                     <option value="2012-2013">2012-2013</option>
                                     <option value="2013-2014">2013-2014</option>
                                     <option value="2014-2015">2014-2015</option>
                                     <option value="2015-2016">2015-2016</option>
                                     <option selected="selected" value="2016-2017">2016-2017</option>
                                     <option value="2018-2019">2018-2019</option>
                                     <option value="2019-2020">2019-2020</option>
                                     <option value=" "> </option>

                                 </select>

                             </td>
                             <td bgcolor="#ffffff" class="personaddbg1" height="27">
                                 学期</td>
                             <td bgcolor="#ffffff" class="trbg2" height="27">
                                 <select name="XQ1$DropDownListXQ" id="XQ1_DropDownListXQ" style="width:74px;">
                                     <option selected="selected" value="1">1</option>
                                     <option value="2">2</option>
                                     <option value=" "> </option>

                                 </select>

                             </td>
                             <td bgcolor="#ffffff" class="personaddbg1" colspan="3" height="27">
                                 <input type="submit" name="Button1" value="查询" id="Button1" style="width:72px;">
                                 <input type="submit" name="ButtonList" value="班级列表模式" id="ButtonList">
                                 <input type="submit" name="btnExport" value="课表输出" id="btnExport">
                             </td>
                         </tr>
                         </tbody></table>-->
                    <table id="TABLE1" width="100%" height="697" border="1" align="center" cellpadding="0" cellspacing="1" bgcolor="dfdfdf">
                        <tbody><tr bgcolor="#6699CC">
                            <td width="60" height="40" valign="middle"><div align="center" style="color: #FFFFFF">时间</div></td>
                            <td width="184" align="center" valign="middle"><span class="STYLE1" style="color: #FFFFFF">星期一</span></td>
                            <td align="center" valign="middle"><span class="STYLE1" style="color: #FFFFFF">星期二</span></td>
                            <td align="center" valign="middle"><span class="STYLE1" style="color: #FFFFFF">星期三</span></td>
                            <td align="center" valign="middle"><span class="STYLE1" style="color: #FFFFFF">星期四</span></td>
                            <td align="center" valign="middle"><span class="STYLE1" style="color: #FFFFFF">星期五</span></td>
                            <td align="center" valign="middle"><span class="STYLE1" style="color: #FFFFFF">星期六</span></td>
                            <td align="center" valign="middle"><span class="STYLE1" style="color: #FFFFFF">星期日</span></td>
                        </tr>
                        <tr>
                            <td width="60" align="center" valign="middle" bgcolor="#E9F1F3" class="personaddbg1"><p>上午</p>
                                <p> 1~2节</p></td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('11')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('21')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('31')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('41')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('51')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="60" align="center" valign="middle" bgcolor="#E9F1F3" class="personaddbg1"><p>上午</p>
                                <p> 3~4节</p></td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('12')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('22')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('32')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('42')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('52')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="60" align="center" valign="middle" bgcolor="#E9F1F3" class="personaddbg1"><p>下午</p>
                                <p> 5~6节</p></td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('13')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('23')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('33')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('43')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('53')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="60" align="center" valign="middle" bgcolor="#E9F1F3" class="personaddbg1"><p>下午</p>
                                <p> 7~8节</p></td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('14')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('24')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('34')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('44')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;
                                <c:forEach items="${curriculumList}" var="item">
                                    <c:if test="${item.time.equals('54')}">
                                        ${item.teacherName},${item.courseName},${item.classroomName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="60" align="center" valign="middle" bgcolor="#E9F1F3" class="personaddbg1" style="height: 64px"><p>晚上</p>
                                <p> 9~10节</p></td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="60" align="center" valign="middle" bgcolor="#E9F1F3" class="personaddbg1" style="height: 64px"> 晚上<br>
                                11~12节</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF" style="height: 64px">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF" style="height: 64px">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF" style="height: 64px">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF" style="height: 64px">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF" style="height: 64px">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF" style="height: 64px">&nbsp;</td>
                            <td width="184" align="left" valign="top" bgcolor="#FFFFFF" style="height: 64px">&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="37" colspan="8" bgcolor="#FFFFFF">&nbsp; 备注:</td>
                        </tr>
                        </tbody></table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    $("#nav li:nth-child(4)").addClass("active")

    $("#search").click(function () {
        $("#searchCurriculumForm").submit();
    });
</script>
</html>


