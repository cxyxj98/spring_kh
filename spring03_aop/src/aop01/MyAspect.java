package aop01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class MyAspect implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("출근 카드를 찍는다.");
		
		try {
			invocation.proceed();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("쉬는 날이었다.");
		}finally {
			System.out.println("집에 간다.");
		}
		
		return null;
	}
}
