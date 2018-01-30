$(document).ready(function(){//相当于$(function(){});
	//1 文档加载完成后执行此函数 在保存按钮上注册事件
	//事件执行时 调用dosaveorupdate函数
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	// 2 模态框隐藏时的注册事件 hidden.bs.modal：固定写法
	//hidden.bs.modal：代表模态框的隐藏事件
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//当模态框隐藏以后移除。ok对象的click事件
		//移除事件 假如没有执行此操作，可能会出现表单重复提交的现象
		$(this).off("click",".ok").removeData("id");
//		$(this).removeData("id");
	});
	//3.获得模态框上绑定id
	var id = $("#modal-dialog").data("id");
	console.log("id:"+id);
	//假如id有值，根据id查找数据
	if(id){
		doFindObjectById(id);
	}
	
});
function doFindObjectById(id){//doFindObjectById
	var url = "project/doFindObjectById.do";
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
	//$("#nameId").val(result.name) 为元素赋值
	$("#nameId").val(result.name);
	$("#codeId").val(result.code);
	$("#beginDateId").val(result.beginDate);
	$("#endDateId").val(result.endDate);
	$("#noteId").html(result.note);
	$("#editFormId input[name='valid']").each(function(){
		//迭代input标签中name 为valid的dom对象 
		//加入这个对象的值等于result.valid的值，则让其选中
		if($(this).val() == result.valid){
			$(this).prop("checked",true);
		}
	});
}
/*
 * 点击模态框上的save按钮时执行此函数，通过此函数发送
 * 异步请求 将页面上的数据发送到服务器
 */
function doSaveOrUpdate(){
	if(!$("#editFormId").valid()){
		return;
	}
	//1.获得数据
	var params = getEditFormData();
	//2.将数据发送给服务器
	var id = $("#modal-dialog").data("id");
	if(id){
		//如果id有值，将id绑定参数上
		params.id = id;
	}
//	var updateUrl = "project/doUpdateObject.do";//doUpdateObject
//	var insertUrl = "project/doSaveObject.do";
	var url = id?"project/doUpdateObject.do":"project/doSaveObject.do";
//	var url ="project/doSaveObject.do";
	$.post(url,params,function(result){
		console.log("url:"+url);
		if(result.state == 1){
			alert(result.message);
			//将模态框隐藏 隐藏：hide 显示：show
			$("#modal-dialog").modal("hide");
			//重新查询（调用project_list.js中的doGetObjects函数
			//因为两个js文件都在一个网页里 index.jsp
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}
function getEditFormData(){
	//1. 获得数据， 将数据封装为Json对象
//	"valid":$(".col-md-10 input[name='valid']").val(), 注意选择对
	var params = {
		"name":$("#nameId").val(),	
		"code":$("#codeId").val(),
		"beginDate":$("#beginDateId").val(),
		"endDate":$("#endDateId").val(),
		"valid":$("input[name='valid']:checked").val(),
		"note":$("#noteId").val()
	};
	console.log(JSON.stringify(params));
	//2，返回json数据
	return params;
}
