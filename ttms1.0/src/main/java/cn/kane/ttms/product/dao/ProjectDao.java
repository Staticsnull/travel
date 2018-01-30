package cn.kane.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.kane.ttms.common.dao.BaseDao;
import cn.kane.ttms.product.entity.Project;


/**数据持久层对象  方法名必须与mapper文件中的id 值对应*/
//@Repository("projectDao")
public interface ProjectDao extends BaseDao<Project>{
	/** 获得表中所有的记录*/
//	List<Project> findObjects();
	/**
	 * @param name 模糊查询是输入的项目
	 * @param valid 项目启用 禁用的状态值（1，0）
	 * @param starIndex 表示从哪条记录开始取数据
	 * @param pageSize 表示每一页显示多少条记录
	 * 
	 * @return 表示查询到的当前的所有记录
	 * 当方法向mapper中传递的参数多于一个值时，需要通过注解@Param声明
	 * 当方法中的参数个数多余一个时，需要使用@Param注解进行声明
	 */
	List<Project> findObjects(@Param("name")String name,
			@Param("valid")Integer valid,
			@Param("startIndex")int starIndex,
			@Param("pageSize")int pageSize);
	/**获得表中的总记录数*/
	/**
	 * @param name 模糊查询是输入的项目
	 * @param valid 项目启用 禁用的状态值（1，0）
	 */
	int getRowCount(@Param("name")String name,
			@Param("valid")Integer valid);
	/** 将对象信息写入到表中*/
	//int insertObject(Project entity);
	//根据id查找数据
	//Project findObjectById(int id);
//	/** 修改表中的记录信息*/
	//int updateObject(Project entity);
	//禁用或启动项目信息
	//String idsStr = "1,2,3"; String[] ids = idsStr.split(",");
	//@Param 要修改的id的值 @Param 要修改的状态（启用：1，禁用：0）
	int validById(@Param("ids")String[] array,@Param("valid")int valid);
	//查询所有启用项目的id以及名字 findProjectIdAndName
	List<Map<String,Object>> findProjectIdAndName();
}
