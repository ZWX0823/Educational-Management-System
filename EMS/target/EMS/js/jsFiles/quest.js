/**
 * 
 */

$(function(){
	formValidator();
	$("#test-table").bootstrapTable('destroy');
	$('#test-table').bootstrapTable({
		method : 'GET', //默认是post,不允许对静态文件访问
		url: "activiti/getMyReq.action",
		cache : false,
		striped : true,// 隔行加亮
		pagination : true, //开启分页功能    在表格底部显示分页工具栏
		pageSize : 5, //默认每页条数
		pageNumber : 1, //默认分页
		pageList : [ 10, 20, 50, 100, 200, 500 ],//分页数
		toolbar:"#toolbar",
		singleselect : true,
         clickToSelect: true, // 单击行即可以选中
		search : false,//显示搜素表单
		silent : true, //刷新事件必须设置
		sidePagination : "server", //表示服务端请求  
		columns : [ {
			field : "bill_id",
			title : "请假单编号",
			align : "center",
			width : 100,
			valign : "middle",
			sortable : "true"
		},{
			field : "taskId",
			title : "任务编号",
			align : "center",
			width : 100,
			valign : "middle",
			sortable : "true"
		} , {
			field : "name",
			title : "请假人",
			align : "center",
			width : 120,
			valign : "middle",
			sortable : "true"
		}, {
			field : "type",
			title : "请假类型",
			width : 120,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "reason",
			title : "请假原因",
			width : 200,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "startTime",
			title : "开始时间",
			width : 200,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "endTime",
			title : "结束时间",
			width : 200,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "optTime",
			title : "申请时间",
			width : 200,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "optName",
			title : "审核人",
			width : 200,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "status",
			title : "状态",
			align : "center",
			width : 100,
			valign : "middle",
			sortable : "true"
		},{
            field: 'operate',
            title: '操作',
           class : 'col-md-2',
            align: 'center',
            valign: 'middle',
           formatter: operateFormatter,
        }],
		queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                // searchText: params.searchText
            };
            return param;
        },
		formatLoadingMessage : function() {
			return "请稍等，正在加载中...";
		},

		formatNoMatches : function() {
			return '无符合条件的记录';
		}
	});
}); 

function operateFormatter(value, row, index) {
    return ['<button type="button" class=" btn btn-warning" onclick="submitMyReq('+row.taskId+')">提交申请</button>&nbsp;&nbsp;&nbsp;',
        '<button type="button" class=" btn btn-info" onclick="getTaskProgress('+row.bill_id+')">查看进度</button>',
        '&nbsp;&nbsp;&nbsp;<button class=" btn btn-danger" type="button" onclick="delMyReq('+row.bill_id+')">删除</button>'
        ].join('');
}
//关闭
function closeprogressDlg(){
	$("#progressDlg").modal('hide');
}
//查看进程图
function getTaskProgress(bid){
	//location.href="activiti/queryProPlan.action?bid="+bid;
	$.ajax({
		url:'activiti/queryProPlan.action',
		dataType:'json',
		type:'post',
		data:{
			bid:bid
			},
        success:function(data){
        	if(data.flag){
        		$("#imgSpan").empty();	
            	$("#imgSpan").append("<img alt='图片不见了,请查看tomcat配置' src="+data.msg+"  width='65%' height='50	%'>");
            	$("#progressDlg").modal('show');
        	}else{
        		alert(data.msg);
        	}
        	
        },
        error:function(){
        	alert("请求失败！");
        }
	});
}

//删除请假单
function delMyReq(bill_id){
		if(confirm("您确定要删除这条数据吗？")){
			$.ajax({
				url:'activiti/delMyReq.action',
				dataType:'json',
				type:'post',
				data:{
					bid:bill_id
				},
				success:function(data){
					if(data.flag){
						alert("删除成功!");
					}else{
						alert(data.msg);
					}
					$("#test-table").bootstrapTable('refresh');
				},
				error:function(){
					alert("请求失败！");
				}
			});
		}
	
}
//提交申请
function submitMyReq(taskId){
	if(taskId!=null){
		if(confirm("您确定要提交请求吗")){
			$.ajax({
				url:'activiti/submitMyReq.action',
				dataType:'json',
				type:'post',
				data:{
					taskId:taskId
				},
				success:function(data){
					if(data){
						alert("提交成功!");
					}else{
						alert("保存失败!");
					}
					$("#test-table").bootstrapTable('refresh');
				},
				error:function(){
					alert("请求失败！");
				}
			});
		}
	}else{
		alert("请求已提交,请不要重复点击");
	}
}

//打开  填写请假
function addQuest(){
	$("#noteDlg").modal('show');
}


//关闭模态框
function closeNoteDlg(){
	$("#noteDlg").modal('hide');
	$("input[type=reset]").trigger("click");
	$('#noteForm').data('bootstrapValidator', null);
	formValidator();
}

//保存
function saveNote(){
	if($("#noteForm").data('bootstrapValidator').validate().isValid()){
		$.ajax({
			url:'activiti/addNote.action',
			dataType:'json',
			type:'post',
			data:$("#noteForm").serialize(),
			success:function(data){
				if(data){
					alert("保存成功!");
					closeNoteDlg();
					$("#test-table").bootstrapTable('refresh');
				}else{
					alert("保存失败!");
				}
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}
}

function formValidator(){
	$("#noteForm").bootstrapValidator({
		fields:{
			type:{
				validators:{
					notEmpty:{
						message:"请假类型不能为空"
					},
				}
			},
			reason:{
				validators:{
					notEmpty:{
						message:'原因不能为空',
					},
					stringLength:{
						max:200,
						message:'字符长度不能超过200'
					}
				}
			},
			startTime:{
				validators:{
					notEmpty:{
						message:'不能为空'
					}
				}
			},
			endTime:{
				validators:{
					notEmpty:{
						message:'不能为空'
					}
				}
			},
			reMark:{
				stringLength:{
					max:200,
					message:'字符长度不能超过200'
				}
			}
		}
	});
}