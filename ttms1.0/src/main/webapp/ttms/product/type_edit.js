var zTree;
$(document).ready(function(){//editTypeForm
//	console.log("2");
	$("#editTypeForm").on("click",".load-product-type",doLoadZTreeNodes)
	.on("click","#btn-save",doSaveOrUpdate);
//	$("#editTypeForm").on("click","#btn-return",doBackListPage);
	$("#btn-return").click(doBack);
	$("#typeLayer").on("click",".btn-cancle",doHideZTree)
	.on("click",".btn-confirm",doSetSelectNode);
//	$("#container").removeData("id");     
	//获得id值，等修改或返回页面之后，必须将id的值移除
	var id = $("#container").data("id");//$("#container").data("id",id);
	// 加入id有值，说明是修改
	if(id){
		doFindObjectById(id);
	}
});
function doBack(){
	doClearData();
	var listUrl = "productType/listUI.do?t="+Math.random(1000);
	$("#container").load(listUrl);
}
function doBackListPage(){
	loadListPage();
}
function doFindObjectById(id){
	var url = "productType/doFindObjectById.do";
	var params = {"id":id};
	$.post(url,params,function(result){
		if(result.state == 1){
			console.log("data:"+JSON.stringify(result.data));
			//初始化表单数据
			doSetEditFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}
function doSetEditFormData(result){
//	console.log(JSON.stringify(result));
	console.log(result.parentId+":"+result.parentName)
	$("#typeNameId").val(result.name);
	$("#parentNameId").val(result.parentName);
	//绑定parentId
	$("#editTypeForm").data("parentId",result.parentId);
	$("#typeSortId").val(result.sort);
	$("#typeNoteId").val(result.note);
}
function doSaveOrUpdate(){
	//获得表单数据
	var params = getEditFormData();
//	var url = "productType/doSaveObject.do";
	var updateUrl = "productType/doUpdateObject.do";
	var saveUrl = "productType/doSaveObject.do";
	//获得页面绑定的id值
	var id = $("#container").data("id");
	if(id){
		params.id = id;
	}
	var url = id?updateUrl:saveUrl;
	$.post(url,params,function(result){
		if(result.state == 1){
			//清空编辑页面的数据，解除数据的绑定
			//加载列表页面 重新显示查询结果
			doBack();
//			loadListPage();
//			var listUrl = "productType/listUI.do?t="+Math.random(1000);
//			$("#container").load(listUrl);
//			$("#container").removeData("id");
//			$("#editTypeForm").removeData("parentId");
		}else{
			alert(result.message);
		}
	});
}
/**清空相关数据*/
function doClearData(){
	//清空所有类选择器器有dynamicClear标识的对象内容
	$(".dynamicClear").val("");
	//移除绑定的数据
	$("#container").removeData("id");
	$("#editTypeForm").removeData("parentId");
}
function loadListPage(){
	var listUrl = "productType/listUI.do?t="+Math.random(1000);
	$("#container").load(listUrl);
	$("#container").removeData("id");
	$("#editTypeForm").removeData("parentId");
}
function getEditFormData(){
	var params = {
		"name":$("#typeNameId").val(),
		"parentId":$("#editTypeForm").data("parentId"),
		"sort":$("#typeSortId").val(),
		"note":$("#typeNoteId").val()
	};
	return params;
}
function doSetSelectNode(){
	//进行非空验证 #editTypeForm
	if(!$("#editTypeForm").valid()){
		return;
	}
	//1.获得选中的节点对象 getSelectNodes:是zTree中的一个 函数，
	//返回值可能是一个数组
	var selectedNodes = zTree.getSelectedNodes();
	var node = selectedNodes[0];
	//3.通过node节点数据更新页面内容
	$("#parentNameId").val(node.name);
	//将id值绑定
	$("#editTypeForm").data("parentId",node.id);
	//4.隐藏zTree
	doHideZTree();
}
function doHideZTree(){
	$("#typeLayer").css("display","none");
}
var setting = {
		data:{
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}
/**显示ztree 以及树上的节点信息*/
function doLoadZTreeNodes(){
	//1.显示ztree（在type——edit。jsp页面上默认是隐藏的） load-product-type
	console.log(1);
	$("#typeLayer").css("display","block");
	//2.发送异步请求加载分类信息，更新ztree节点内容
	var url = "productType/doFindZtreeObjects.do";
	$.getJSON(url,function(result){
		if(result.state == 1){//初始化树
			//访问ztree中的方法，通过数据初始化节点信息
			zTree = $.fn.zTree.init(
					$("#typeTree"),//显示树的位置
					setting,//树的基本配置
					result.data);//树上要显示的数据
		}else{
			alert(result.message);
		}
	});
}