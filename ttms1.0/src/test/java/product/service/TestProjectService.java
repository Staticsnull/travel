package product.service;

import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kane.ttms.product.service.ProjectService;

public class TestProjectService {
	private ClassPathXmlApplicationContext ac;
	private ProjectService ps;
	@Before
	public void init(){                       //"spring-pool.xml","spring-mybatis.xml"
		ac = new ClassPathXmlApplicationContext("spring-pool.xml","spring-mvc.xml","spring-mybatis.xml");
		//              ProjectServiceImpl
		ps = ac.getBean("projectServiceImpl",ProjectService.class);
	}
	@Test
	public void testFindObjects(){
//		List<Project> list = ps.findObjects();
//		for(Project p:list){
//			System.out.println(p);
        int valid = 1;
		String name = "环球";
		int pageCurrent = 3;
		Map<String,Object> map = ps.findObjects(name,valid,pageCurrent);
//		Assert.assertNotEquals(unexpected, actual);
		//unexpected:不期望 actual：实际
		//验证list集合是否不等于空
		System.out.println(map.get("list"));
		System.out.println(map.get("pageObject"));
		Assert.assertNotEquals(null, map);
	}
	@After
	public void destory(){
		ac.close();
	}
}
