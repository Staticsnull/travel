package product.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kane.ttms.product.dao.ProductTypeDao;
import cn.kane.ttms.product.entity.ProductType;



public class TestProductTypeDao {
	private ClassPathXmlApplicationContext ac;
	private ProductTypeDao dao;
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-pool.xml","spring-mybatis.xml");
		dao = ac.getBean("productTypeDao",ProductTypeDao.class);
	}
	@Test
	public void test(){
		ProductType t1 = new ProductType();
		t1.setName("亲子游");
		t1.setSort(3);
		t1.setParentId(2);
		t1.setNote("游必有方");
		int row = dao.insertObject(t1);
		System.out.println(row);
	}
}
