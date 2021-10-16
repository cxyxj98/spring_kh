package test02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("test02/applicationContext.xml");
		Rent rent = context.getBean("rent",Rent.class);
		System.out.println(rent);
		
		Book book = context.getBean("book",Book.class);
		System.out.println(book); //보통 주입되서 사용되지 이렇게 직접 받지않는다

	}

}
