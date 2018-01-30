$(document).ready(function(){
	$("#uploadFormId").on("click",".btn-upload",doUpload);
	$("#uploadFormId").on("click",".btn-download",doDownload)
	doGetObjects();
});
function doDownload(){
	var id = $(this).parent().parent().data("id");
	var url = "attachment/doDownload.do?id="+id;
	document.location.href = url;
	
}
function doGetObjects(){
	var url = "attachment/doFindObjects.do";
	$.getJSON(url,function(result){
		if(result.state == 1){
			setTableRows(result.data);
		}else{
			alert(result.message);
		}
	});
}
function setTableRows(result){
	var tBody = $("#tbodyId");
	tBody.empty();
	for(var i in result){
		//创建tr对象
		var tr = $("<tr></tr>");
		var td = "<td><input type='checkbox' value='[id]' name='checkedItem'/></td>";
		tr.data("id",result[i].id);
		tr.append(td.replace("[id]",result[i].id));
		tr.append("<td>"+result[i].title+"</td>");
		tr.append("<td>"+result[i].fileName+"</td>");
		tr.append("<td>"+result[i].contentType+"</td>");
		tr.append("<td><input type='button' class='btn btn-default btn-download' value='下载'/></td>");
		tBody.append(tr);
	}
}
/**点击文件上传按钮执行此函数*/
function doUpload(){
	//异步提交表单（$.ajaxSubmit()为异步提交表单）
	//使用此函数，需要在页面引入（jquery.form.js）    attachment
	$("#uploadFormId").ajaxSubmit({type:"post",url:"attachment/doUpload.do",dataType:"json",
		success:function(result){
			alert(result.message);
		}
	});
	//$("#uploadFormId").restForm()
	return false;//防止表单重复提交的一种方式
}



