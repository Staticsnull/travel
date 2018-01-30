package product.controller;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.kane.ttms.common.controller.IndexController;

public class TestController {
	@Test
	public void testIndex(){
		//1.获得ApplicationContext对象
		ApplicationContext  ctx=
		new ClassPathXmlApplicationContext("spring-mvc.xml");
        IndexController c=(IndexController)
        ctx.getBean("indexController");
        System.out.println("c="+c);
	}
}
