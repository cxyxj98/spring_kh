package templateCallback.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Stream;

import templateCallback.framework.MemberDao;

public class Run {
	
	//자바 람다
	//Functional Interface : 추상메서드가 하나만 존재하는 인터페이스
	//						인터페이스 위에 @FunctionalInterface 어노테이션을 작성
	//Functional Interface의 추상메서드는 화살표함수 형태로 오바라이드가 가능
	
	// * 자바의 화살표함수도 매개변수의 타입을 생략 가능
	// * 화살표 함수의 메서드 body block을 생략할 경우, ; 도 생략
	// * 매개변수가 하나만 있는 경우 () 생략 가능
	// * return문 밖에 없는 경우, 메서드 body block과 return 생략
	// * 메서드 구문이 1줄인 경우 메서드 body block을 생략
	
	public static void main(String[] args) {
		String password = new MemberDao().selectPassword("DEV", () -> {
				Connection conn = null;				
				try {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bm", "1234");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return conn;
		});
		
		System.out.println("비밀번호는 " + password + " 입니다.");
	}
}
