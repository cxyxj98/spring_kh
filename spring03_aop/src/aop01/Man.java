package aop01;

import org.springframework.stereotype.Component;

@Component
public class Man{
	
	public void develop() {
		System.out.println("파이썬으로 개발한다.");
	}
	
	private void play() {
		System.out.println("리니지W를 플레이한다.");
	}
}
