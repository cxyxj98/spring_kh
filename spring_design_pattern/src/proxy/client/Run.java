package proxy.client;

import proxy.framework.MyAspect;

public class Run {
	
	public static void main(String[] args) {
		
		new MyAspect("proxy.client.Man").develop();
		System.out.println("==============================");
		new MyAspect("proxy.client.Woman").develop();
		
	}
}
