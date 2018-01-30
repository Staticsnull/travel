package product.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kane.ttms.product.dao.ProjectDao;
import cn.kane.ttms.product.entity.Project;



public class TestProjectDao {
	private ClassPathXmlApplicationContext ac;
	private ProjectDao dao;
	@Before
	public void init(){
		ac = new ClassPathXmlApplicationContext("spring-pool.xml","spring-mybatis.xml");
		dao = ac.getBean("projectDao",ProjectDao.class);
	}
	@Test
	public void test(){
		Project p = dao.findObjectById(11);
		System.out.println(p);
	}
	@Test
	public void test01(){
		List<Map<String,Object>> list = dao.findProjectIdAndName();
		System.out.println(list);
	}
	@Test
	public void testFindObjects(){
		//假设现在要取第二页的数，没页显示2条数据
		int current = 1;//当前页
		int size = 2;//每页显示的多少条数据
		int startIndex = (current-1)*size;
		int valid = 1;
		String name = "环球";
		List<Project> list = dao.findObjects(name, valid,startIndex,size);
		for(Project p:list){
			System.out.println(p);
		}
		//测试集合是否不等于空
		Assert.assertNotEquals(null, list);
	}
	@Test
	public void testGetRowCount(){
		int valid = 1;
		String name = "环球";
		int rowCount = dao.getRowCount(name,valid);
		System.out.println(rowCount);
		Assert.assertNotEquals(null, rowCount);
	}
	@Test
	public void testInsertObject() throws ParseException{
		Project p = new Project();
		p.setName("北京马拉松");
		p.setCode("tt-20170802-CN-BJ-002");
		String begin = "2017/08/12";
		String end = "2017/08/19";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		p.setBeginDate(sdf.parse(begin));
		p.setEndDate(sdf.parse(end));
		p.setValid(1);
		p.setNote("北京马拉松进行中。。");
		p.setCreatedUser("admin");
		p.setModifiedUser("Admin");
		int rows = dao.insertObject(p);
		System.out.println("rows:"+rows);
		Assert.assertEquals(1, rows);
	}
	
	@Test
	public void testValidById(){
		String str = "11,12,13";
		String[] array = str.split(",");
		int valid = 1;
		int i = dao.validById(array, valid);
		System.out.println(i);
		Assert.assertNotEquals(null, i);
	}
	@After
	public void destory(){
		ac.close();
		dao = null;
	}
}
