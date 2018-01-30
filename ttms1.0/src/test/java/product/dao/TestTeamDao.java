package product.dao;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kane.ttms.product.dao.TeamDao;
import cn.kane.ttms.product.entity.Team;




public class TestTeamDao {
	private ClassPathXmlApplicationContext ac;
	private TeamDao dao;
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-pool.xml","spring-mybatis.xml");
		dao = ac.getBean("teamDao",TeamDao.class);
	}
	@Test
	public void test01(){
		Team t = new Team();
		t.setName("纽约旅游团");
		t.setProjectId(12);
		t.setNote("马拉松招募进行中。。");
		t.setCreatedUser("admin");
		t.setModifiedUser("admin");
		int i = dao.insertObject(t);
		System.out.println(i);
//		Assert.assertTrue(i==1);
		Assert.assertEquals(1, i);
	}
	@Test
	public void test02(){
		int pageSize = 2;
		int startIndex = 1;
		int valid = 1;
		int projectId = 22;
		List<Map<String,Object>> list = dao.findObjects(valid, projectId, startIndex, pageSize);
		
		System.out.println(list);
		Assert.assertNotEquals(null, list);
	}
	@Test
	public void test03(){
//		Map<String,Object> map = dao.findObjectById(2);
//		System.out.println(map);
//		Assert.assertNotEquals(null, map);
	}
	@After
	public void destory(){
		ac.close();
		dao = null;
	}
}
