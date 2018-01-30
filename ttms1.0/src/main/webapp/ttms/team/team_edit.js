$(document).ready(function(){
	//获得项目的id和名称
	doGetProjectIdAndName();
	//在模态框对应的按钮上 保存按钮上添加点击事件
	$("#modal-dialog").on("click",".ok",doSaveOrUpdateObject);
	//在模态框隐藏时，解除事件的注册
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//当模态框隐藏以后移除。ok对象的click事件
		$(this).off("click",".ok").removeData("id");
	});
//	var id = $("#modal-dialog").data("id");
//	if(id){
//		doFindObjectById(id);
//	}
});
function doFindObjectById(id){
	var url = "team/doFindObjectById.do";
	var params = {"id":id};
	$.post(url,params,function(result){
		if(result.state == 1){
			//初始化页面数据
			doInitFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}
function doInitFormData(result){
	$("#nameId").val(result.name);
	console.log(result.projectId);//projectId
//	var option = new option(result.projectId,)//#projectId
//	var option = $("#projectId").data("op");
//	console.log(option.value+":"+option.text);
//	console.log(":"+result.projectId[name]);
	$("#projectId").val(result.projectId);
	$("#noteId").html(result.note);//注意元素
	$("#editFormId input[name='valid']")
	.each(function(){
		if($(this).val() == result.valid){
			$(this).prop("checked",true);
		}
	});
}
function doSaveOrUpdateObject(){
	//1.验证表单数据（非空验证）
	if(!$("#editFormId").valid()){
		return;
	}
	//2.获得表单数据
	var params = getFormData();
	var id = $("#modal-dialog").data("id");
	if(id){
		params.id = id;
	}
	var saveUrl = "team/doSaveObject.do";
	var updateUrl = "team/doUpdateObject.do";
	var url = id?updateUrl:saveUrl ;
	//3.提交异步请求，将数据写入到服务器
	$.post(url,params,function(result){
		if(result.state == 1){
			alert(result.message);
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}
//获得表单数据
function getFormData(){
	var params = {
		"name":$("#nameId").val(),
		"projectId":$("#projectId").val(),
		"valid":$("input[name='valid']:checked").val(),
		"note":$("#noteId").val()
	};
	return params;
}
function doGetProjectIdAndName(){
	//doFindProjectIdAndName
	var url = "team/doFindProjectIdAndName.do";
	$.getJSON(url,function(result){
		if(result.state == 1){
			doInitProjectSelect(result.data);
			//修改时，等select列表 页面初始化完成 ，要根据
			//id初始化其他数据
			var id = $("#modal-dialog").data("id");
			if(id){
				doFindObjectById(id);
			}
		}else{
			alert(result.message);
		}
	});
}
/*初始化项目select（id 与name）列表*/
function doInitProjectSelect(list){
	var select = $("#projectId");
	select.append("<option value=''>==请选择==</option>");
//	for(var i in list){
//		select.append("<option value="+list[i].id+">"+list[i].name+"</option>");
//	}
	//第二种写法
//	var option = "<option value='[id]'>[name]</option>";
	for(var i in list){
		var option = new Option(list[i].name,list[i].id);
//		$("#projectId").data("op",option);
//		select.append(option.replace("[id]",list[i].id).replace("[name]",list[i].name));
		select.append(option);
//		console.log("name:"+option.text);
	}
}