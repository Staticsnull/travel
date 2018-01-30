package cn.kane.ttms.product.service;

import java.util.Map;

import cn.kane.ttms.product.entity.Project;

/**
 * 项目管理模块的业务层对象 负责具体项目信息的业务处理
 * @author soft01
 *
 */

public interface ProjectService {
	//获得当前也的项目信息,以及分页信息
	//1 项目信息封装到list集合
	//2 分页信息封装到pageObjct
	//3 将项目信息和分页信息再次封装 封装到map中
	//然后统一返回
	Map<String,Object> findObjects(String name,int valid,int pageCurrent);
	void validById(String str,Integer valid);
	//向表中添加数据
	void insertObject(Project p);
	void updateObject(Project p);
	Project findObjectById(Integer id);
	

}
