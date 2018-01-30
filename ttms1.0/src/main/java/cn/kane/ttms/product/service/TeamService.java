package cn.kane.ttms.product.service;


import java.util.List;
import java.util.Map;

import cn.kane.ttms.product.entity.Team;

public interface TeamService {
	//根据条件查询当前页的数据
	public Map<String,Object> findObjects(Integer valid,
			Integer projectId,Integer pageCurrent);
	//查询项目id和名称信息
	public List<Map<String,Object>> findProjectIdAndName();
	/**
	 * 禁用和启用动态团信息
	 * @param ids 对应1个或者多个选中的id
	 * @param valid 对应状态
	 */
	public void validById(String ids,Integer valid);
	/*保存团信息*/
	public void saveObject(Team team);
	/*修改团信息*/
	public void updateObject(Team team);
	/*根据id查找团信息*/
//	public Map<String,Object> findObjectById(Integer id);
	public Team findObjectById(Integer id);
}
