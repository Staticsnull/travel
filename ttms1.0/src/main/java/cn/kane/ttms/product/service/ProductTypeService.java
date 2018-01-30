package cn.kane.ttms.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.kane.ttms.product.entity.ProductType;

@Service
public interface ProductTypeService {
	public void insertObject(ProductType productType);
	public List<Map<String,Object>> findGridTreeObjects();
	public void deleteObject(Integer id);
	/**保存产品类型信息*/
	public void saveObject(ProductType entity);
	/**查询分类节点信息，在浏览器端以ztree形式展示*/
	public List<Map<String,Object>> findZtreeNodes();
//	public ProductType findObjectById(Integer id);
	public void updateObject(ProductType productType);
	public Map<String,Object> findMapById(Integer id);
}
