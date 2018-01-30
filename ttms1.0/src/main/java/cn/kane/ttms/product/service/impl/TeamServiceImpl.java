package cn.kane.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.kane.ttms.common.exception.ServiceException;
import cn.kane.ttms.common.web.PageObject;
import cn.kane.ttms.product.dao.ProjectDao;
import cn.kane.ttms.product.dao.TeamDao;
import cn.kane.ttms.product.entity.Team;
import cn.kane.ttms.product.service.TeamService;
@Transactional
@Service
public class TeamServiceImpl implements TeamService {
	@Resource
	private TeamDao teamDao;
	@Resource
	private ProjectDao projectDao;
	@Transactional(readOnly = true)
	@Override
	public Map<String, Object> findObjects(Integer valid, Integer projectId, Integer pageCurrent) {
		//1.判定参数的有效性 先判断是否是空值 判断空值优先
		if(valid!=null && valid!=0 && valid!=1){
			throw new ServiceException("valid无效");
		}
		//判断空值优先
		if(projectId!=null && projectId<=0){
			throw new ServiceException("项目id无效");
		}
		if(pageCurrent <=0){
			throw new ServiceException("当前页码无效");
		}
		//2.根据pageCurrent 计算startIndex
		int pageSize = 2;
		int startIndex = (pageCurrent-1)*pageSize;
		int rowCount = teamDao.getRowCount(valid, projectId);
		int pageCount = rowCount/pageSize;
		//3.执行查询操作 （获得当前页的数据，计算分页信息）
		List<Map<String,Object>> list = 
				teamDao.findObjects(valid, projectId, startIndex, pageSize);
		if(rowCount%pageSize != 0){
			pageCount++;
		}
		//4.计算分页信息
		PageObject pageObject = new PageObject();
		pageObject.setPageCount(pageCount);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setStartIndex(startIndex);
		//5，封装数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		
		return map;
	}
	/**
	 * 查询项目id和项目名称，通过此数据初始化select列表
	 */
	@Override
	public List<Map<String, Object>> findProjectIdAndName() {
		return projectDao.findProjectIdAndName();
	}

	@Override
	public void validById(String ids, Integer valid) {
		//1.验证参数的有效性
		if(ids == null || ids.equals("")){
			throw new ServiceException("至少选择一项，修改状态");
		}
		if(valid!=0 && valid!=1){
			throw new ServiceException("状态无效");
		}
		//2.执行更新操作
		String[] id = ids.split(",");
		int rows = teamDao.validById(id, valid);
		//3.验证结果
		if(rows == -1){
			throw new ServiceException("修改信息失败");
		}
	}
	@Override
	public void saveObject(Team team) {
		//1.验证参数
		if(team == null){
			throw new ServiceException("保存的数据不能为空");
		}
		//2. 保存信息
		int rows = teamDao.insertObject(team);
		//3.验证结果
		if(rows == -1){
			throw new ServiceException("保存失败！");
		}
		
	}
	@Override
	public void updateObject(Team team) {
		//验证参数
		if(team == null){
			throw new ServiceException("数据为空，不能修改");
		}
		int rows = teamDao.updateObject(team);
		if(rows == -1){
			throw new ServiceException("修改失败！");
		}
	}
	@Override
//	public Map<String,Object> findObjectById(Integer id) {
	public Team findObjectById(Integer id){
		if(id==null || id<1){
			throw new ServiceException("id不存在");
		}
//		Map<String,Object> map = teamDao.findObjectById(id);
		Team team = teamDao.findObjectById(id);
		if(team == null){
			throw new ServiceException("查询失败，信息不存在");
		}
//		return map;
		return team;
	}

}
