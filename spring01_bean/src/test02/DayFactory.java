package test02;

import java.util.Calendar;

import javax.management.RuntimeErrorException;

public class DayFactory {

		//ApplicationContext에 bean을 등록하기 위해 사용하는 Factory method, static 이어야 함
	public static Week getInstance() {
			
		Calendar cal = Calendar.getInstance();
		int day =cal.get(Calendar.DAY_OF_WEEK);
		
		switch (day) {
		case 1:return new Sunday();
			
		case 2:return new Monday();
					
		case 3:return new Tuesday();
			
		case 4:return new Wednesday();
			
		case 5:return new Thursday();
			
		case 6:return new Friday();
			
		case 7:return new Saturday();
		
		default:throw new RuntimeException("Calendar가 잘못된 값을 반환함...");
				
			
		}
	}
}
