$(document).ready(function(){
	//在pageId对应的对象上的pre，next元素上注册click事件
	$("#pageId").on('click','.first,.pre,.next,.last',jumpToPage);
});
//设置分页 Pagination:分页
function setPagination(pageObject){
 //1.初始化总页数
 $(".pageCount").html("总页数("+pageObject.pageCount+")");
 //2.初始化当前页码
 $(".pageCurrent").html("当前页("+pageObject.pageCurrent+")");
 
 //3.绑定总页数 在pageId对应的对象上绑定总页数
 //data函数用于key/value的方式在对象上绑定数据
 $("#pageId").data("pageCount",pageObject.pageCount);
 //4.绑定当前页面 在pageId对应的对象上绑定当前页数
 $("#pageId").data("pageCurrent",pageObject.pageCurrent);
 
}
//定义一个函数，通过此函数实现页面的跳转 跳转到下一页
function jumpToPage(){
//	debugger js调试强大工具
	//获得点击对象上class属性对应的值
	//根据此值判定具体点击的是哪个对象（L例如：上一页，下一页）
	var clazz=$(this).attr("class");
	//获得pageId对象上绑定的pageCurrent对应的值
	var pageCurrent = $('#pageId').data("pageCurrent");
	//获得pageId对象上绑定的pageCount对应的值
	var pageCount = $('#pageId').data("pageCount")
	//根据class属性的值 判断点击的是否是上一页
	if(clazz=='first'){
		pageCurrent = 1;
	}
	if(clazz=='pre'&&pageCurrent>1){
		pageCurrent--;
	}
	//判断点击的是否是下一页
	if(clazz=="next"&&pageCurrent<pageCount){
		pageCurrent++;
	}
	if(clazz=='last'){
		pageCurrent = pageCount;
	}
	//重写绑定pageCurrent的值,
	$('#pageId').data("pageCurrent",pageCurrent);
	//重新执行查询操作（根据pageCurrent的值）
	doGetObjects();
}









