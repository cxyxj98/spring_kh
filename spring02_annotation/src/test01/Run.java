package test01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("test01/applicationContext.xml");
		Wheel wheel = context.getBean("wheel",Wheel.class);
		wheel.setRadius(10000);
		System.out.println(context.getBean("wheel2"));
		System.out.println(context.getBean("car"));

	}

}
