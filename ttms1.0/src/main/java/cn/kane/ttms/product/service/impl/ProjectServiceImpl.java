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
import cn.kane.ttms.product.entity.Project;
import cn.kane.ttms.product.service.ProjectService;

/**
 * spring 中声明式事务处理，假如是基于注解方式 ，需要
 * 借助Trancactionnal注解，此注解可以写在类上，可以写在方法
 * 上，当某个业务类上使用了 注解时，spring默认就会通过aop
 * 为此类创建代理对象，然后通过代理对象为业务方法植入事务处理功能
 * @author soft01
 *
 */
@Transactional
@Service
public class ProjectServiceImpl implements ProjectService{
	/** Resource 注解为属性注入值时，是先按名字查周
	 * 还是是先按类型查找*/
//	@Resource(name="projectDao")
	@Resource
	private ProjectDao dao;
	//查询项目信息
	public Map<String,Object> findObjects(String name,int valid,int pageCurrent) {
		//通过dao对象获取当前也项目信息
//		List<Project> list = dao.findObjects();
		int pageSize = 2;//每一页最多显示2条数据
		//计算当前页开始查找的位置
		int startIndex = (pageCurrent-1)*pageSize;
		//查询当前页的数据
		List<Project> list = dao.findObjects(name,valid,startIndex,pageSize);
		//获得总记录数，封装总记录数
		int rowCount = dao.getRowCount(name,valid);
		//计算总页数
		int pageCount = rowCount/pageSize;
		if(rowCount%pageSize != 0){
			pageCount++;
		}
		//封装分页信息
		PageObject pageObject = new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount(pageCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		//将数据封装到map中(两个对象需要传回页面）
		Map<String,Object> map = new HashMap<String,Object>();
		//封装当前页数据
		map.put("list", list);
		//封装分页对象
		map.put("pageObject", pageObject);
		return map;
	}
	//启用或者禁用项目信息
	public void validById(String str, Integer valid) {
		if(str==null || str.equals("")){
			throw new ServiceException("至少选择一项");
		}
		if(valid!=0 && valid!=1){
			
			throw new ServiceException("valid值必须是0或者1");
		}
		//将字符串解析为数组，
		String[] array = str.split(",");
		//执行启用或者禁用的更新操作
		dao.validById(array, valid);
	}
	/**
	 * 执行写入操作， 封装用户页面输入的数据
	 */
	@Override
	public void insertObject(Project p) {
		if(p == null){
			throw new ServiceException("数据为空，添加失败");
		}
		int rows = dao.insertObject(p);
		//对结果集进行验证  对结果进行业务判定
		if(rows==-1){
			throw new ServiceException("insert error");
		}
	}
	@Override
	public void updateObject(Project p) {
		if(p == null){
			throw new ServiceException("数据为空，修改失败");
		}
		int row = dao.updateObject(p);
		if(row == -1){
			throw new ServiceException("修改失败！");
		}
	}
	@Override
	public Project findObjectById(Integer id) {
		//1.判断id的有效性
		if(id == null || id < 0){
			throw new ServiceException("id值无效");
		}
		//根据id查找对应结果
		Project p = dao.findObjectById(id);
		//判断对应的结果是否正确
		if(p == null){
			throw new ServiceException("没有对应结果");
		}
		return p;
	}

}
