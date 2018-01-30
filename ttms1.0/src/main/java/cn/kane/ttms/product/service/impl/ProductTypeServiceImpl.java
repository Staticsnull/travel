package cn.kane.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.kane.ttms.common.exception.ServiceException;
import cn.kane.ttms.product.dao.ProductTypeDao;
import cn.kane.ttms.product.entity.ProductType;
import cn.kane.ttms.product.service.ProductTypeService;
@Transactional
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Resource
	private ProductTypeDao productTypeDao;
	@Override
	public void insertObject(ProductType productType) {
		if(productType == null){
			throw new ServiceException("数据为空，添加失败！");
		}
		int rows = productTypeDao.insertObject(productType);
		if(rows == -1){
			throw new ServiceException("新增信息失败");
		}
	}
	//查询分类的列表信息 Grid:网格
	public List<Map<String,Object>> findGridTreeObjects(){
		List<Map<String,Object>> list = productTypeDao.findObjects();
		return list;
	}
	@Override
	public void deleteObject(Integer id) {
		if(id==null || id<=0){
			throw new ServiceException("id值无效");
		}
		int count = productTypeDao.hasChilds(id);
		if(count > 0){
			throw new ServiceException("此分类下有子元素，不能删除");
		}
		int rows = productTypeDao.deleteObject(id);
		if(rows == -1){
			throw new ServiceException("删除失败");
		}
	}
	@Override
	public void saveObject(ProductType entity) {
		if(entity == null){
			throw new ServiceException("数据为空，保存失败");
		}
		
		int rows = productTypeDao.insertObject(entity);
		if(rows == -1){
			throw new ServiceException("异常操作，保存失败！");
		}
	}
	/**查询分类节点信息，在浏览器端以ztree形式展示*/
	@Override
	public List<Map<String, Object>> findZtreeNodes() {
		
		return productTypeDao.findZtreeNodes();
	}
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Map<String,Object> findMapById(Integer id) {
		if(id==null || id<=0){
			throw new ServiceException("id无效");
		}
		Map<String,Object> map = productTypeDao.findMapById(id);
		if(map == null){
			throw new ServiceException("查询异常");
		}
		return map;
	}
	@Override
	public void updateObject(ProductType productType) {
		if(productType == null){
			throw new ServiceException("数据为空，修改失败");
		}
		if(productType.getId()==null || productType.getId()<=0){
			throw new ServiceException("id值不能为空");
		}
		findMapById(productType.getId());
		int rows = productTypeDao.updateObject(productType);
		if(rows == -1){
			throw new ServiceException("异常，修改失败");
		}
	}
	

}
