package cn.kane.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.ttms.common.web.JsonResult;
import cn.kane.ttms.product.entity.ProductType;
import cn.kane.ttms.product.service.ProductTypeService;

@RequestMapping("/productType/")
@Controller
public class ProductTypeController {
	@Resource
	private ProductTypeService productTypeService;
	@RequestMapping("listUI")
	public String listUI(){
		return "product/type_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/type_edit";
	}
	//doFindGridTreeObjects
	@RequestMapping("doFindGridTreeObjects")
	@ResponseBody
	public JsonResult doFindgGridTreeObjects(){
		List<Map<String,Object>> list = 
				productTypeService.findGridTreeObjects();
		return new JsonResult(list);
	}
	/*执行删除操作 @Param id 为页面上选中的id值*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		System.out.println("doDeleteObject()");
		productTypeService.deleteObject(id);
		return new JsonResult();
	}
	/**执行保存分类信息的操作*/
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType productType){
		productTypeService.saveObject(productType);
		return new JsonResult();
	}
	/**查询分类节点信息，在浏览器端以ztree形式展示*/
	@RequestMapping("doFindZtreeObjects")
	@ResponseBody
	public JsonResult doFindZtreeObjects(){
		List<Map<String,Object>> list = productTypeService.findZtreeNodes();
		return new JsonResult(list);
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String,Object> map = productTypeService.findMapById(id);
		return new JsonResult(map);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(ProductType entity){
		productTypeService.updateObject(entity);
		return new JsonResult();
	}
	

}
