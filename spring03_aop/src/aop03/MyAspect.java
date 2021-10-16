package aop03;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	
	@Before(value = "execution(* *(..))")
	public void before() {
		System.out.println("출근 카드를 찍는다.");
	}
	
	@After(value = "execution(* *(..))")
	public void after() {
		System.out.println("집에 간다.");
	}
	
	@AfterReturning(value = "execution(* *(..))",returning = "res")
	public void afterReturning(Object res) { //매개변수 리턴타입 Object
		System.out.println(res);
		System.out.println("퇴근 카드를 찍는다.");
	}
	
	@AfterThrowing(value = "execution(* *(..))", throwing = "exception") //예외객체를 받을 메서드 매개변수명
	public void afterThrowing(Exception exception) {
		System.out.println(exception.getMessage());
		System.out.println("쉬는 날이었다.");
	}
}
