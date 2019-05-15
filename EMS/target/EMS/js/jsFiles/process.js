/**
 * 
 */

$(function(){
	$("#test-table").bootstrapTable('destroy');
	$('#test-table').bootstrapTable({
		method : 'GET', //默认是post,不允许对静态文件访问
		url: "activiti/definition.action",
		cache : false,
		striped : true,// 隔行加亮
		pagination : true, //开启分页功能    在表格底部显示分页工具栏
		pageSize : 5, //默认每页条数
		pageNumber : 1, //默认分页
		pageList : [ 10, 20, 50, 100, 200, 500 ],//分页数
		showColumns : false, //显示隐藏列
		showRefresh : false, //显示刷新按钮
		toolbar:"#toolbar",
		singleselect : true,
		//minimumCountColumns: 2,// 设置最少显示列个数
        clickToSelect: true, // 单击行即可以选中
		search : false,//显示搜素表单
		silent : true, //刷新事件必须设置
		sidePagination : "server", //表示服务端请求  
		columns : [{
			field : 'key',
			title : '流程定义key',
			width : 100,
			align : 'center'
		},{
			field : 'id',
			title : '流程定义ID',
			width : 100,
			align : 'center'
		}, {
			field : 'deployment_id',
			title : '部署对象ID',
			width : 150,
			align : 'center'
		},{
			field : 'pro_defi_name',
			title : '流程定义名称',
			width : 150,
			align : 'center'
		},{
			field : 'pro_devl_name',
			title : '流程名称',
			width : 100,
			align : 'center'
		},{
			field : 'resourcename',
			title : 'png文件名称',
			width : 150,
			align : 'center'
		},{
			field : 'version',
			title : '流程定义版本',
			width : 100,
			align : 'center'
		},{
			field : 'pro_devl_time',
			title : '流程部署时间',
			width : 150,
			align : 'center'
		}, {
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
    return ['<button type="button" class=" btn btn-primary" onclick="getImg('+row.deployment_id+')">查看流程图</button>&nbsp;&nbsp;&nbsp;',
        '&nbsp;&nbsp;&nbsp;<button class=" btn btn-danger" type="button" onclick="delAct('+row.deployment_id+')">删除</button>'
        ].join('');
}

//打开  部署流程
function addAct(){
	$("#ActDlg").modal('show');
}

//关闭
function closeActDlg(){
	$("#ActDlg").modal('hide');
}
//保存流程
function saveAct(){
	$.ajax({
		url:'activiti/saveAct.action',
		dataType:'json',
		type:'post',
		data:new FormData($("#actForm")[0]),
		contentType: false, //必须false才会避开jQuery对 formdata 的默认处理 
        processData: false, //必须false才会自动加上正确的Content-Type
        success:function(data){
        	if(data){
        		alert("部署成功！");
        	}else{
        		alert("部署失败！");
        	}
        	closeActDlg();
        	$("#test-table").bootstrapTable("refresh");
        },
        error:function(){
        	alert("请求失败！");
        }
	});
}
//删除流程
function delAct(id){
	if(confirm("你确定要删除这个流程吗？删除后将无法回复！")){
		$.ajax({
			url:'activiti/delAct.action',
			dataType:'json',
			type:'post',
			data:{
				deployment_id:id
				},
	        success:function(data){
	        	if(data){
	        		alert("删除成功！");
	        	}
	        	$("#test-table").bootstrapTable("refresh");
	        },
	        error:function(){
	        	alert("请求失败！");
	        }
		});
	}else{
		return false;
	}
}
//查看流程图
function getImg(id){
	//location.href="activiti/img.action?deployment_id="+id;
	$.ajax({
		url:'activiti/img.action',
		dataType:'json',
		type:'post',
		data:{
			deployment_id:id
			},
        success:function(data){
        	$("#imgSpan").empty();	
        	$("#imgSpan").append("<img alt='图片不见了,请查看tomcat配置' src="+data.msg+"  width='75%' height='60%'>");
        	$("#imgDlg").modal('show');
        },
        error:function(){
        	alert("请求失败！");
        }
	});
}
//关闭
function closeImgDlg(){
	$("#imgDlg").modal('hide');
}