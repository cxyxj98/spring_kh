package aop03;

import org.springframework.stereotype.Component;

@Component
public class Man{
	
	public void develop() {
		//int i = 1/0; 예외발생 코드 
		System.out.println("파이썬으로 개발한다.");
	}
	
	public String play() {
		return "리니지W";
	}
}
