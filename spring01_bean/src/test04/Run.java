package test04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("test04/applicationContext.xml");
		System.out.println(context.getBean("car"));

	}

}
