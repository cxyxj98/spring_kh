package test01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {

		ApplicationContext context = 
					new ClassPathXmlApplicationContext("test01/applicationContext.xml");
		
		Address hong = (Address) context.getBean("hong");
		Address lee = context.getBean("lee",Address.class);
		System.out.println(hong);
		System.out.println(lee);
		System.out.println( context.getBean("kim",Address.class));
	}

}
