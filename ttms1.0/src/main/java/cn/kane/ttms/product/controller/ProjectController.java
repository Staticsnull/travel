package cn.kane.ttms.product.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.ttms.common.web.JsonResult;
import cn.kane.ttms.product.entity.Project;
import cn.kane.ttms.product.service.ProjectService;

@RequestMapping("/project/")
@Controller
public class ProjectController {
	@Resource
	private ProjectService projectService;
	@RequestMapping("listUI")
	public String listUI(){
		//此字符串返回时 会交给碎片日内该视图解析器
		return "product/project_list";//WEB-INF/pages/product/project_list.jsp
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/project_edit";
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody//jackson
	public JsonResult doFindObjects(String name,int valid,int pageCurrent){
		System.out.println("doFindObjects();");
		System.out.println("pageCurrent:"+pageCurrent);
//		List<Project> list = projectService.findObjects();
		Map<String,Object> map = projectService.findObjects(name,valid,pageCurrent);
		
		//将获得的数据封装到JsonResult对象中
		return new JsonResult(map);
	}
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		projectService.validById(checkedIds, valid);
		return new JsonResult();
	}
	/**
	 * 执行添加操作
	 * var params = {"name":"",..}
	 * @param p 对象会封装页面上传入的参数
	 * 页面上参数的名字和p对象中属性的值一致时，会实现自动注入操作
	 * 在spring获得消息请求头时，需要传入request
	 * @return
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Project p){
		projectService.insertObject(p);
		return new JsonResult();
	}
	//备注：业务作出判断逻辑，控制层不作出具体判断
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Project p = projectService.findObjectById(id);
		return new JsonResult(p);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Project p){
		//假设有用户登录 ，可以从session中获得用户信息
		projectService.updateObject(p);
		return new JsonResult();
	}
//	@RequestMapping("doFindObjectById")
//	@ResponseBody
//	public JsonResult doFindObjectById(Integer id){
//		Project p = projectService.findObjectById(id);
//		return new JsonResult(p);
//	}
	
}
