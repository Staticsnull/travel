package product.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kane.ttms.product.entity.ProductType;
import cn.kane.ttms.product.service.ProductTypeService;


public class TestProductTypeService {
	private ClassPathXmlApplicationContext ac;
	private ProductTypeService pts;
	@Before
	public void init(){                       //"spring-pool.xml","spring-mybatis.xml"
		ac = new ClassPathXmlApplicationContext("spring-pool.xml","spring-mvc.xml","spring-mybatis.xml");
		//              ProjectServiceImpl
		pts = ac.getBean("productTypeServiceImpl",ProductTypeService.class);
	}
	@Test
	public void test(){
		ProductType t1 = new ProductType();
		t1.setName("太空游");
		t1.setSort(1);
		t1.setNote("火星置业");
		pts.insertObject(t1);
	}
	@After
	public void destory(){
		ac.close();
		pts = null;
	}

}
