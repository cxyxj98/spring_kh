package proxy.framework;

import java.lang.reflect.InvocationTargetException;

public class MyAspect implements Developer{
	
	private Developer developer;
	
	//자바 Reflection : 객체 또는 클래스명을 통해 타입에 대한 정보를 조사할 수 있는 기법
	
	public MyAspect(String className) {
		try {
			developer = (Developer) Class.forName(className).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void develop() {
		System.out.println("출근 카드를 찍는다.");
		
		try {
			developer.develop();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("쉬는 날이었다.");
		}finally {
			System.out.println("집에 간다.");
		}
	}

}
