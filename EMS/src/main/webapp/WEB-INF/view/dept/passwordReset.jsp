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
<body onload="check1()">
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
					    	<h1 style="text-align: center;">修改密码</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form name="reset" class="form-horizontal" role="form" action="${pageContext.request.contextPath}/password/passwordReset" id="editfrom" method="post" onsubmit="return check()">
							  <div class="form-group">
							    <label for="oldPassword" class="col-sm-2 control-label">旧密码</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" name="oldPassword" id="oldPassword" placeholder="请输入旧密码" >
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="password" class="col-sm-2 control-label">新密码</label>
							    <div class="col-sm-10">
							      <input type="password" name="newPassword" class="form-control" id="password" placeholder="请输入密码">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="password2" class="col-sm-2 control-label">确认密码</label>
							    <div class="col-sm-10">
							      <input type="password" name="newPassword2" class="form-control" id="password2" placeholder="请再次输入密码">
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								  <input type="hidden" name="message" id="message" value="${flag}">
								<button class="btn btn-default" type="reset">重置</button>
							  </div>
						</form>
				    </div>
				    <input type="hidden" id="pageContext" value="${pageContext.request.contextPath}">
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
    $("#nav li:nth-child(8)").addClass("active")
    function check() {
        if(reset.oldPassword.value==""||reset.oldPassword.value==null)
        {alert("请输入旧密码");return false;}
        if(reset.newPassword.value==""||reset.newPassword.value==null)
        {alert("请输入新密码");return false;}
        if(reset.newPassword2.value==""||reset.newPassword2.value==null)
        {alert("请再次输入新密码");return false;}
        if(reset.newPassword.value != reset.newPassword2.value)
        {alert("两次密码不正确");return false;}
    }

    function check1() {

    	var flag = true;
		var flag2 = reset.message.value;

		if (flag2!=null){
			if (flag2 == 2 ){
				alert("${msg}");
				location.href=$("#pageContext").val()+"/logout";
			}
			else if (flag2 == 1){
				alert("${msg}");
				flag = false;
			}
		}
		return flag;
	}


</script>
</html>