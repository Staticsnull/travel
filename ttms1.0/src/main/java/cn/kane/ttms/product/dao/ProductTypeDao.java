package cn.kane.ttms.product.dao;

import java.util.List;
import java.util.Map;

import cn.kane.ttms.common.dao.BaseDao;
import cn.kane.ttms.product.entity.ProductType;
/**
 * 产品分类的持久层对象 ：ocjp scjp
 * @author soft01
 *
 */
public interface ProductTypeDao extends BaseDao<ProductType> {
	public List<Map<String,Object>> findObjects();
	//添加删除方法
	public int deleteObject(Integer id);
	//判定分类下是否还有子分类
	public int hasChilds(Integer id);//hasChilds 
	//通过html实现页面内容的输出 css通过css渲染页面的显示效果
	//了解js框架：vue。js全栈式工程师
	/**获得以ZTtree形式进行产品分类信息 
	 * 其中一个节点信息 应该包含：
	 * 1.id 2.parentId 3.name
	 * 每一条记录节点信息都要封装到一个map对象中，
	 * 多个节点信息在存储到list集合中
	 * */
	public List<Map<String,Object>> findZtreeNodes();
//	public ProductType findObjectById(Integer id);
	public Map<String,Object> findMapById(Integer id);	
}
