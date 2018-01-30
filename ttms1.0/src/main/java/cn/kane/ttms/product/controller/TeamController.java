package cn.kane.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.ttms.common.web.JsonResult;
import cn.kane.ttms.product.entity.Team;
import cn.kane.ttms.product.service.TeamService;

@RequestMapping("/team/")
@Controller
public class TeamController {
	@Resource
	private TeamService teamService;
	@RequestMapping("listUI")
	public String teamUI(){
		return "team/team_list";
	}
	/**
	 * 当在页面点击添加或修改按钮时 访问此方法，
	 * 通过此方法返回一个页面
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(){
		return "team/team_edit";
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Team team){
		teamService.saveObject(team);
		return new JsonResult();
	}
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
//		Map<String, Object> map = teamService.findObjectById(id);
		Team team = teamService.findObjectById(id);
		return new JsonResult(team);
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Team team){
		teamService.updateObject(team);
		return new JsonResult();
	}
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(Integer valid,Integer projectId,Integer pageCurrent){
		Map<String,Object> map = teamService.findObjects(valid, projectId, pageCurrent);
		return new JsonResult(map);
	}
	@RequestMapping("doFindProjectIdAndName")
	@ResponseBody
	public JsonResult doFindProjectIdAndName(){
		List<Map<String,Object>> list = teamService.findProjectIdAndName();
		return new JsonResult(list);
	}
	@RequestMapping("doValidById")//doValidById
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		teamService.validById(checkedIds, valid);
		return new JsonResult();
	}

}
