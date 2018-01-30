var columns = [
	{
	field : 'selectItem',
	radio : true
	},
	{
	title : '分类id',
	field : 'id',
	visible : false,
	align : 'center',
	valign : 'middle',
	width : '80px'
	},
	{
	title : '分类名称',
	field : 'name',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '180px'
	},
	{
	title : '上级分类',
	field : 'parentName',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '180px'
	},
	{
	title : '排序号',
	field : 'sort',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '100px'
	},
	{
		title : '备注',
		field : 'note',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '100px'
	}];
$(document).ready(function(){
	doGetObjects();
	$("#formHead").on("click",".btn-delete",doDeleteObject);
	$("#formHead").on("click",".btn-add,.btn-update",doLoadEditPage);
});
//加载页面到指定的位置
function doLoadEditPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title = "添加分类信息";
	}
	if($(this).hasClass("btn-update")){
		var id = getSelectedId();
		if(id == -1){
			alert("请选择");
			return;
		}
		$("#container").data("id",id);
		title = "修改分类信息,id="+id;
	}
	var url = "productType/editUI.do";
	$("#container").load(url,function(){
		$(".panel-heading").html(title);
	});
}
/**获得选中的id值*/
function getSelectedId(){
	var selections = $("#typeTable").bootstrapTreeTable("getSelections");
	console.log("selections:"+selections);
	if(selections.length==0){
//		alert("请选择");
		return -1;
	}
	//获得选中数组中 下标为0 的元素的值
	var typeId = selections[0].id;
	return typeId;
}
/**执行删除操作*/
function doDeleteObject(){
	//1.获得选中的id
//	var selections = $("#typeTable").bootstrapTreeTable("getSelections");
//	console.log("selections:"+selections);
//	if(selections.length==0){
//		alert("请先选择");
//		return;
//	}
	//var typeId = selections[0].id;
//	//获得选中数组中 下标为0 的元素的值
	var typeId = getSelectedId();
	if(typeId == -1){
		alert("请先选择");
		return;
	}
//	console.log("id:"+typeId);
	//2.发送异步请求 doDeleteObject
	var url="productType/doDeleteObject.do";
	//3.定义参数 参数要与controller中参数值一致
	var params = {"id":typeId};
	$.post(url,params,function(result){
		if(result.state == 1){
			doGetObjects();
			alert("删除完毕！")
		}else{
			alert(result.message);
		}
	});
	
}
function doGetObjects(){
	var tableId = "typeTable";//对象中的id 
	var url = "productType/doFindGridTreeObjects.do";
	//columns:按照规范去写
	var table = new TreeTable(tableId, url, columns);
	table.setIdField("id");//设置选中记录的返回id
	table.setCodeField("id");//设置级联关系的id
	table.setParentCodeField("parentId");//设置级联关系中parentId
	table.setExpandColumn(2);
	//设置是否展开
	table.setExpandAll(false);
	table.init();//初始化对象树（底层会发起异步请求）；
	
}