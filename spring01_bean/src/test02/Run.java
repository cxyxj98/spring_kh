package test02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("test02/applicationContext.xml");
		System.out.println("오늘은 "+ context.getBean("dayInfo",Week.class).dayInfo()+" 입니다.");
	}

}
