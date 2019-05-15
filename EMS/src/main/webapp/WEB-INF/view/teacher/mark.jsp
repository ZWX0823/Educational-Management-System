<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css">

	<!-- 引入JQuery  bootstrap.js-->
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsFiles/index.js"></script>

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
	<div class="container body" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 style="text-align: center;">学生打分</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form name="reset" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/teacher/mark" id="editfrom" method="post" onsubmit="return check()">
							<div class="form-group">
							    <label for="studentID" class="col-sm-2 control-label">学号</label>
							    <div class="col-sm-10">
							      <input  readonly="readonly" type="text" class="form-control" name="studentID" id="studentID" value="${studentCourse.studentID}">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="studentName" class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-10">
							      <input  readonly="readonly" type="text" name="studentName" class="form-control" id="studentName" value="${studentCourse.studentExpand.studentName}">
							    </div>
							  </div>
							<div class="form-group">
								<label for="courseName" class="col-md-2 control-label">课程编号</label>
								<div class="col-md-10">
									<input readonly="readonly" type="text" name="courseID" class="form-control" id="courseID" value="${studentCourse.teacherCourseExpand.courseID}">
								</div>
							</div>
							<div class="form-group">
								<label for="courseName" class="col-md-2 control-label">课程名</label>
								<div class="col-md-10">
									<input readonly="readonly" type="text" name="courseName" class="form-control" id="courseName" value="${studentCourse.teacherCourseExpand.courseExpand.courseName}">
								</div>
							</div>
							  <div class="form-group">
							    <label for="grade" class="col-sm-2 control-label">成绩</label>
							    <div class="col-sm-10">
							      <input type="number" name="grade" class="form-control" id="grade" placeholder="请输入成绩">
									<input type="hidden" name="teacher_CourseID" id="teacher_CourseID" value="${studentCourse.teacher_CourseID}">
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="reset">重置</button>
							  </div>
						</form>
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
<script>
    $("#nav li:nth-child(2)").addClass("active")
    function check() {
        if(reset.grade.value==""||reset.grade.value==null)
        {alert("请输入成绩");return false;}
    }
</script>
</html>