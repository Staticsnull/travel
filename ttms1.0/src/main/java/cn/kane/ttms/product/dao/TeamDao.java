package cn.kane.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.kane.ttms.common.dao.BaseDao;
import cn.kane.ttms.product.entity.Team;
/**
 * 数据持久层 数据访问对象
 * @author soft01
 *
 */
public interface TeamDao extends BaseDao<Team> {
	//用于向表中写入数据
	//public int insertObject(Team t);
//	public List<Team> findObjects();
	/**查询团信息 以及团所在的项目的项目id和项目名称*/
	/**
	 * 此方法用于获得当前页面的团信息
	 * @param valid 团的禁用和启用的状态，可能根据此状态进行查询
	 * @param projectId 项目的id，可能会根据此id查询项目下的团的信息
	 * @param startIndex 表示查询当前页的起始位置
	 * @param pageSize 表示每页显示几条记录
	 * @return 用户封装查询结果，一般假如结果红的数据来自与多张表，
	 * 建议封装，可以将每条记录封装到一个Map对象，再将多个map对象封装到集合中
	 */
	public List<Map<String,Object>> findObjects(
			@Param("valid")Integer valid,
			@Param("projectId")Integer projectId,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/** 获得表中满足条件的记录总数*/
	/* @param valid 团的禁用和启用的状态，可能根据此状态进行查询
	 * @param projectId 项目的id，可能会根据此id查询项目下的团的信息
	 */
	public int getRowCount(@Param("valid")Integer valid,
			@Param("projectId") Integer projectId);
	/**
	 * 此方法用与实现记录的禁用和启用
	 * @param ids 数组中封装的是页面上选择的记录
	 * @param valid 封装的是启用或者禁用的状态
	 * @return
	 */
	public int validById(@Param("ids")String[] ids,
			@Param("valid")Integer valid);
	//public int updateObject(Team team);
//	public Map<String,Object> findObjectById(Integer id);
	//public Team findObjectById(Integer id);
}
