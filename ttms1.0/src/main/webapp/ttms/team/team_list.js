$(document).ready(function(){
	//在queryFormId对应对象的btn-serch搜索 元素上注册点击事件
//	$("#queryFormId").on("click",".btn-serch",doQueryObjects);
	doGetObjects();
	doGetProjectIdAndName();
//	$("#queryFormId").on("click","#searchProjectId,#searchValidId",doGetProjectId )
	$("#queryFormId").on("click",".btn-search",doQueryObjects);
	//在启用和禁用上注册单击事件
	$("#queryFormId").on("click",".btn-valid,.btn-invalid",doValidById);
	//在添加和修改按钮上 注册点击事件
	$("#queryFormId").on("click",".btn-add,.btn-update",doShowEditDiaLog);
	
});
function doShowEditDiaLog(){
	var url = "team/editUI.do";
	var title;
	if($(this).hasClass("btn-add")){
		title = "添加团信息";
	}
	if($(this).hasClass("btn-update")){
		//绑定id
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));
		title = "修改团信息,id="+$("#modal-dialog").data("id");
	}
	$("#modal-dialog .modal-body").load(url,function(){
		$("#myModalLabel").html(title);
		//显示页面
		$("#modal-dialog").modal("show");
	});
}
function getValid(obj){
	var valid;
//	if($(this).hasClass("btn-valid")){
	if(obj.hasClass("btn-valid")){
		valid = 1;//启用
	}
	if(obj.hasClass("btn-invalid")){
		valid = 0;
	}
	return valid;
}
function doValidById(){
	//1.获取用户点击的状态 //将this传入
	var valid = getValid($(this));
	//2.检查id
	var checkedIds = getCheckedIds();
	if(checkedIds == ""){
		alert("请至少选择一项");
		return;
	}
	//3.绑定参数
	var params = {
		"checkedIds":checkedIds,
		"valid":valid
	};
	//4.加载路径
	var url = "team/doValidById.do";
	//5.将数据异步提交到服务端
	$.post(url,params,function(result){
		if(result.state == 1){
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
	
}
//获取用户点击的id
function getCheckedIds(){
	var checkedIds = "";
	$("#tbodyId input[name='checkedItem']")
	.each(function(){
		//判断当前对象是否被选中
		//第二种写法：if($(this).is(":checked")){
		if($(this).prop("checked")){
			//判断用户是否选中
			if(checkedIds == ""){
				//如果没有选中任何值，直接追加
				checkedIds += $(this).val();
			}else{
				//如果有选中的值，拼接逗号 追加
				checkedIds += ","+$(this).val();
			}
		}
	});
	return checkedIds;
}
function doQueryObjects(){
	//1.初始化pageCurrent的值
	$("#pageId").data("pageCurrent",1);
	//2.执行查询工作
	doGetObjects();

//	var id = $("#modal-dialog").data("projectId");
//	var valid = $("#modal-dialog").data("valid");
//	doGetObjects(valid,id);
}
//function doGetProjectId(){
//	var id = $("#searchProjectId").val();
//	console.log("id:"+id);
//	var valid = $("#searchValidId").val();
//	$("#modal-dialog").data("projectId",id);
//	$("#modal-dialog").data("valid",valid);
//	
//}
function doGetProjectIdAndName(){
	//doFindProjectIdAndName
	var url = "team/doFindProjectIdAndName.do";
	$.getJSON(url,function(result){
		if(result.state == 1){
			doInitProjectSelect(result.data);
		}else{
			alert(result.message);
		}
	});
}
/*初始化项目select（id 与name）列表*/
function doInitProjectSelect(list){
	var select = $("#searchProjectId");
	select.append("<option value=''>选择项目名</option>");
//	for(var i in list){
//		select.append("<option value="+list[i].id+">"+list[i].name+"</option>");
//	}
	//第二种写法
	var option = "<option value='[id]'>[name]</option>";
	for(var i in list){
		select.append(option.replace("[id]",list[i].id).replace("[name]",list[i].name));
	}
}
function doGetObjects(){
	//1.通过异步请求获得服务端的团信息
	var url = "team/doFindObjects.do"
	var pageCurrent = $("#pageId").data("pageCurrent");
	if(!pageCurrent){
		pageCurrent = 1;
	}
	
//	var params = {"valid":valid,"projectId":id,
//			"pageCurrent":pageCurrent};//最先获取数据
	var params = getQueryFormData();
	params.pageCurrent = pageCurrent;
	$.post(url,params,function(result){
		if(result.state == 1){
			setTableBodyRows(result.data.list);
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	});
	//2.将团信息更新到页面的tbody位置
	//2.1记录信息
	//2.2分页信息
}
//获取表单中查询的数据
function getQueryFormData(){
	//点击的时候可以自动获取value，不需要注册单击事件
	var params = {
		"projectId":$("#searchProjectId").val(),
		"valid":$("#searchValidId").val()//searchValidId
//		name:$("#searchNameId").val(),
//		valid:$("#searchValidId").val()
	};
	console.log(JSON.stringify(params));
	return params;
}
function setTableBodyRows(list){
	var tBody = $("#tbodyId");//tbodyId
	tBody.empty();
	var td = "<td><input type='checkbox' value='[id]' name='checkedItem'/></td>";
	for(var i in list){
		var tr = $("<tr></tr>");
//		var td = "<td><input type='checkbox' value='[id]' name='checkedItem'/></td>";
		tr.data("id",list[i].id);
		tr.append(td.replace("[id]",list[i].id));
		tr.append("<td>"+list[i].name+"</td>");
		tr.append("<td>"+list[i].projectName+"</td>");
		tr.append("<td>"+(list[i].valid?"启用":"禁用")+"</td>");//default
		tr.append("<td><button type='button' class='btn btn-default btn-update'>修改</button></td>");
		tBody.append(tr);
	}
}
