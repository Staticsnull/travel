package product.service;

import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kane.ttms.product.service.TeamService;

public class TestTeamService {
	private ClassPathXmlApplicationContext ac;
	private TeamService ts;
	@Before
	public void init(){                       //"spring-pool.xml","spring-mybatis.xml"
		ac = new ClassPathXmlApplicationContext("spring-pool.xml","spring-mvc.xml","spring-mybatis.xml");
		//              ProjectServiceImpl
		ts = ac.getBean("teamServiceImpl",TeamService.class);
	}
	@Test
	public void test(){
//		int valid = 1;
//		int projectId = 22;
//		int pageCurrent = 1;
//		Map<String,Object> map = ts.findObjects(null, null, 1);
//		System.out.println(map);
//		Assert.assertNotEquals(null, map);
		String ids = "22";
		ts.validById(ids, 1);
	}
	@After
	public void destory(){
		ac.close();
	}
}
