/**
 * 
 */
$(function (){
	$("#test-table").bootstrapTable('destroy');
	$('#test-table').bootstrapTable({
		method : 'GET', //默认是post,不允许对静态文件访问
		url: "activiti/myTask.action",
		cache : false,
		striped : true,// 隔行加亮
		pagination : true, //开启分页功能    在表格底部显示分页工具栏
		pageSize : 5, //默认每页条数
		pageNumber : 1, //默认分页
		pageList : [ 10, 20, 50, 100, 200, 500 ],//分页数
		singleselect : true,
         clickToSelect: true, // 单击行即可以选中
		search : false,//显示搜素表单
		silent : true, //刷新事件必须设置
		sidePagination : "server", //表示服务端请求  
		columns : [ {
			field : "id",
			title : "任务编号",
			align : "center",
			width : 100,
			valign : "middle",
			sortable : "true"
		},{
			field : "name",
			title : "任务节点名称",
			align : "center",
			width : 100,
			valign : "middle",
			sortable : "true"
		} , {
			field : "processInstanceId",
			title : "流程实例id",
			align : "center",
			width : 120,
			valign : "middle",
			sortable : "true"
		}, {
			field : "processDefinitionId",
			title : "流程定义id",
			width : 120,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "excutionId",
			title : "执行对象id",
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "assignee",
			title : "任务办理人",
			width : 200,
			align : "center",
			valign : "middle",
			sortable : "true"
		},{
			field : "createtime",
			title : "任务创建时间",
			width : 200,
			align : "center",
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
    return ['<button type="button" class=" btn btn-warning" onclick="detailTask('+row.id+')">办理任务</button>&nbsp;&nbsp;&nbsp;',
        ].join('');
}
//打开模特框 请假单，按钮信息回写
function detailTask(taskId){
	$.ajax({
		url:'activiti/detailTask.action',
		dataType:'json',
		type:'post',
		data:{
			taskId:taskId
		},
		success:function(data){
			$("#comments").val("");
			$("#bill_id").val(data.note.bill_id);
			$("#type").val(data.note.type);
			$("#name").val(data.note.name);
			$("#startTime").val(data.note.startTime);
			$("#endTime").val(data.note.endTime);
			$("#reason").val(data.note.reason);
			$("#reMark").val(data.note.reMark);
			$("#bid").empty();
			$.each(data.list,function(index,items){
				$("#bid").append("<button type='button' value="+items+" class='btn btn-default' onclick='dealTask("+taskId+",this)'>"+items+"</button>&nbsp;");
			});
			$("#detailTaskDlg").modal("show");
		},
		error:function(){
			alert("请求失败！");
		}
	});
}
//处理任务
function dealTask(taskId,dom){
	var comments=$("#comments").val();
	if(confirm("您是否确定"+dom.value+"这条请求")){
		$.ajax({
			url:'activiti/dealTask.action',
			dataType:'json',
			type:'post',
			data:{
				taskId:taskId,
				comments:comments,
				buttonValue:dom.value
			},
			success:function(data){
				$("#comments").val();
				if(data){
					alert("处理成功！");
				}else{
					alert("处理失败！");
				}
				$("#test-table").bootstrapTable('refresh');
				$("#detailTaskDlg").modal("hide")
			},
			error:function(){
				alert("请求失败！");
			}
		});
	}else{
		return false;
	}
}