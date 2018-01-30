$(document).ready(function(){
	//在queryFormId对应对象的btn-serch 元素上注册村里参考事件
	$("#queryFormId").on("click",".btn-search",doQureyObjects);
	//在禁用和启用按钮上注册点击事件
	$("#queryFormId").on("click",".btn-valid,.btn-invalid",doValidById);
	//在添加按钮上注册点击事件
	$("#queryFormId").on("click",".btn-add,.btn-update",doShowEditDiaLog);
	doGetObjects();
});
/*
 * 点击添加按钮时 执行一个动作
 * 1)初始化index页面的模态框（bootstrap 框架提供）
 * 2）在模态框内显示project_edit.jsp
 */
function doShowEditDiaLog(){
	//1.在模态框对应位置异步加载url
	var url = "project/editUI.do";
	//定义模态框的标题
	var title;
	if($(this).hasClass("btn-update")){
		title = "修改项目";
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));
		//测试id是否拿到
//		title = "修改项目,id:"+$("#modal-dialog").data("id");
	}
	if($(this).hasClass("btn-add")){
		title = "添加项目";
	}
	//2.启动模态框，并加载页面
	$("#modal-dialog .modal-body").load(url,
		function(){//异步加载完成回调此函数
		//显示对话框 show hidden：隐藏对话框
		$("#myModalLabel").html(title);
		$("#modal-dialog").modal("show");
	});
	
}

/*
 * 执行禁用 启用操作 
 * 1.获得数据 （禁用或启动哪些项目信息）
 * a：id （选中的那个checked）
 * b：valid（由点击按钮决定）
 */
function doValidById(){
	var valid;
	if($(this).hasClass("btn-valid")){
		valid = 1;
	}
	if($(this).hasClass("btn-invalid")){
		valid = 0;
	}
	var checkedIds = getCheckedIds();
	
	if(checkedIds.length==0){
		alert("请至少选择一项");
		return;
	}
	var url = "project/doValidById.do";
	var params = {"checkedIds":checkedIds,"valid":valid};
	console.log("params"+JSON.stringify(params));
	//提交异步请求 更新对应记录的状态信息
	//post 为一个特殊的ajax请求，类型为post
	//安全性好，数据量大 
	$.post(url,params,function(result){
		if(result.state == 1){
			alert(result.message);
			
			doGetObjects();//重新查询
		}else{
			alert(result.message);
		}
	});
}
function getCheckedIds(){
	var checkedIds = "";
	//遍历所有的checkbox 获得选中的值checkedItem
	$("#tbodyId input[name='checkedItem']")
	.each(function(){//each函数用来迭代对象
		console.log(checkedIds);
		//判断当前对象是否被选中
		if($(this).prop("checked")){
//			console.log(checkedIds);
			if(checkedIds == ""){
				console.log("checkedIds:"+checkedIds);
				checkedIds += $(this).val();
			}else{
				console.log("else checkedIds:"+checkedIds);
				checkedIds += ","+$(this).val();
			}
		}
	});
	return checkedIds;
	
}
//       doQureyObjects
function doQureyObjects(){
	//1.修改当前页的值
	$("#pageId").data("pageCurrent",1);
	//2.执行查询操作
	doGetObjects();
}
//获取表单中的数据
function getQueryFormData(){
	//根据id获取具体对象的值，然后封装到json对象中
	var params = {
		name:$("#searchNameId").val(),
		valid:$("#searchValidId").val()
	};
	console.log(JSON.stringify("params:"+params));
	return params;
}
function doGetObjects(){
	//底层发起ajax异步请求
	var url = "project/doFindObjects.do";
	//获取当前页面值，假如没有值，默认值设置为1
	var pageCurrent = $("#pageId").data("pageCurrent");
	//undifind 是false
	if(!pageCurrent){
		pageCurrent = 1;
	}
	
	//getJSON 是一个增强版的ajax方法
	//定义一个参数，getQureyFormData
 	var params = getQueryFormData();
	//动态向params对象中添加key/value添加参数
	params.pageCurrent = pageCurrent;
	console.log("params.pageCurrent:"+params.pageCurrent);
//	var params = {"pageCurrent":pageCurrent};
//	$.getJSON(url,function(result){
	// 传入参数
	$.post(url,params,function(result){
		console.log(result);
		//将JSON对象 转换成json字符串输出
//		console.log(JSON.stringify(result));
		console.log(JSON.stringify(result.data.list))
//		console.log(paserjosn())
//		$("#tbodyId").html(data); 对于table里的内容不能这样显示
		//将json对象中的数据 填充到table的tbody中
//		setTableRows(result.data);
		if(result.state == 1){//成功
//			alert(result.message); //假如有需要 ，可以作出提示
			setTableRows(result.data.list);
			//设置分页信息
			setPagination(result.data.pageObject);
		}else{//失败，获取数据时出现异常
			alert(result.message);
		}
		
	});
}
//定义函数将json对象中的数据取出来填充到表格中
function setTableRows(data){
	//获得tbody对象 id选择器必须添加#号，切记
	var tBody = $("#tbodyId");
	//清空tbady
	tBody.empty();
	//迭代json对象
	for(var i in data){
		//创建tr对象
		var tr = $("<tr></tr>");//注意js必须var定义变量
		var firstTd = "<td><input type='checkbox' value='[id]' name='checkedItem'/></td>";
		firstId = firstTd.replace("[id]",data[i].id);
		tr.data("id",data[i].id);
		tr.append(firstId);
		tr.append("<td>"+data[i].code+"</td>");
		tr.append("<td>"+data[i].name+"</td>");
		//1.第一种转换日期的格式
//		var date = new Date(data[i].beginDate);
//		console.log("beginDate:"+date.toLocaleString());
//		var d = new Date(data[i].endDate);
//		console.log("endDate:"+d.toLocaleString());
//		tr.append("<td>"+date.toLocaleDateString()+"</td>");
//		tr.append("<td>"+d.toLocaleDateString()+"</td>");
		tr.append("<td>"+data[i].beginDate+"</td>");
		tr.append("<td>"+data[i].endDate+"</td>");
		tr.append("<td>"+(data[i].valid?"启用":"禁用")+"</td>");
		tr.append("<td><input type='button' class='btn btn-success btn-update' value='修改'/></td>");
		tBody.append(tr);
	}
}