package com.kh.spring.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring.member.model.dto.Member;

//가상으로 만들어지는 web.xml을 사용해 테스트환경을 구축
@WebAppConfiguration
//Junit을 실행할 방법
//테스트 때 사용할 가상의  applicationContext를 생성하고 관리
@RunWith(SpringJUnit4ClassRunner.class)
//가상의 applicationContext를 생성할 때 사용할 spring bean 설정파일의 위치를 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MybatisTest {
	//Junig annotation
	//@Before : 테스트 전에 실행될 메서드
	//@Test : 테스트 메서드
	//@After : 테스트 이후에 실행될 메서드
	
	//SqlSessionTemplate의 주요 메서드
	//selectOne : 단일행 select문 실행
	//selectList : 다중행 select문 실행
	//insert :메서드의 결과값은 쿼리에 의해 영향을 받은 row 수
	//update :메서드의 결과값은 쿼리에 의해 영향을 받은 row 수
	//delete :메서드의 결과값은 쿼리에 의해 영향을 받은 row 수
	//**procedure 호출은 dml 쿼리메서드 중에서 선택
	
	@Autowired
	private SqlSessionTemplate session;
	private final String NAMESPACE = "com.kh.spring.mybatis.MybatisMapper.";
	private String userId = "DEV";
	
	@Test
	public void selectOneTest() {
		session.selectOne(NAMESPACE+"selectPasswordByUserId",userId);
	}
	
	@Test
	public void selectOneAsDto() {
		Member member = session.selectOne(NAMESPACE+"selectMemberByUserId",userId);
		System.out.println(member);
	}
	
	@Test
	public void selectListAsMap() {
		List<Map<String,Object>> res = session.selectList(NAMESPACE+"selectRentAndMemberByUserId",userId);
		for(Map<String,Object> map: res) {
			System.out.println(map);
		}
	}
	
	@Test
	public void selectListUsingResultMap() {
		List<Map<String,Object>> res = session.selectList(NAMESPACE+"selectRentBookByUserId",userId);
		for(Map<String,Object> map: res) {
			System.out.println(map);
		}
	}
	
	@Test
	public void insertWithDto() {
		Member member = new Member();
		member.setUserId("mybatis");
		member.setPassword("abcdefg");
		member.setEmail("pcalss@kh.com");
		member.setTell("010-0000-1111");
		
		session.insert(NAMESPACE+"insertWithDto",member);
	}
	@Test
	public void insertWithMap() {
		Member member = new Member();
		member.setUserId("spring-easy");
		
		Map<String, Object> commandMap = new HashMap<String, Object>();
		commandMap.put("member", member);
		commandMap.put("title", "세션과 일곱 쿠키들");
		commandMap.put("rentBookCnt", 1);
		
		session.insert(NAMESPACE+"insertWithMap",commandMap);
	}
	
	@Test
	public void delete() {
		session.delete(NAMESPACE+"delete","spring-easy");
	}
	
	//session.update
	//사용자 아이디가 DEV인 회원을 찾아 비밀번호를 ppppp로 변경하시오
	@Test
	public void update() {
		Member member = new Member();
		member.setUserId("DEV");
		member.setPassword("ppppp");
		session.update(NAMESPACE+"update",member);
	}
	
	@Test
	public void procedure() {
		session.update(NAMESPACE+"procedure","100001");
	}
	//mybatis mapper escape처리
	//<![CDATA[escape 처리할 내용]]>
	//비교연산자 escape
	//&lt; &gt;
	
	 //1. 도서명 : 쿠키와 세션, 
	   //    작가   : 김영아
	   //    도서번호 : 시퀀스 사용
	   //   인 도서를 BOOK 테이블에 저장하기
	   //   메서드 이름 : test01
	   @Test
	   public void test01(){
			/*
			 * Map<String, Object> commandMap = new HashMap<String, Object>();
			 * commandMap.put("author", "김영아"); commandMap.put("title", "쿠키와 세션");
			 * session.insert(NAMESPACE+"test01",commandMap);
			 */
		   //자바 버전 11로 업데이트 하면
		session.insert(NAMESPACE+"test01",Map.of("title","쿠키와 세션","author","김영아"));
	   }
	   
	   //2. 연장횟수가 2회 이상인 모든 대출도서 정보를
	   //    연장횟수 0회로 초기화 해주세요.
	   //  메서드 이름 : test02
	   @Test
	   public void test02(){
		  session.update(NAMESPACE+"test02");
	   }
	   
	   //3. 2021년 9월 이후 10월 이전에 가입된 회원정보를 삭제
	   //  메서드 이름 : test03
	   @Test
	   public void test03(){
		   session.delete(NAMESPACE+"test03");
	   }
	   
	   //4. 대출 횟수가 가장 많은 3권의 도서를 조회
	   //  메서드 이름 : test04
	   @Test
	   public void test04(){
		   session.selectList(NAMESPACE+"test04");
	   }
	   @Test
	   public void dynamicIf() {
		   //사용자가 도서 검색필터에 info를 선택하고 검색하면, 사용자가 입력한 키워드가 info에 포함된 도서 검색
		   //사용자가 도서 검색필터에 author를 선택하고 검색하면, 사용자가 입력한 키워드가 author에 포함된 도서 검색
		   //사용자가 선택한 필터: info
		   //사용자가 입력한 키워드는 : 김애란 
		   session.selectList(NAMESPACE+"dynamicIf",Map.of("filter","info","keyword","김애란"));
	   }
	   
	   @Test
	   public void dynamicChoose() {
		   //사용자가 도서 검색필터에 info를 선택하고 검색하면, 사용자가 입력한 키워드가 info에 포함된 도서 검색
		   //사용자가 도서 검색필터에 author를 선택하고 검색하면, 사용자가 입력한 키워드가 author에 포함된 도서 검색
		  //사용자가 별도의 필터를 선택하지 않을 경우 제목으로 검색
		   //사용자가 선택한 필터: info
		   //사용자가 입력한 키워드는 : 김애란 
		   
		  session.selectList(NAMESPACE+"dynamicChoose",Map.of("keyword","사랑"));
	   }
	   
	   @Test
	   public void dynamicForeachAndWhereTag() {
		   //사용자가 검색조건을 여러개 선택할 경우
		   //해당 조건들을 or 연산하여 검색되는 도서를 반환
		   //사용자가 제목, 내용, 작가 검색조건을 선택하고
		   //키워드에 '김애란'을 입력할 경우, 제목, 작가, 내용 중에서 하나라도 김애란이 조회되면 해당도서 반환
		   String[] filters = {"title","author"};
		   session.selectList(NAMESPACE+"dynamicForeachAndWhereTag",Map.of("filters",filters,"keyword","김애란"));
		   
	   }
	   
	   @Test
	   public void test05() {
		   //사용자가 검색조건을 여러개 선택할 경우
		   //해당 조건들을 and 연산하여 검색되는 도서를 반환
		   //사용자가 제목, 내용, 작가 검색조건을 선택하고
		   //키워드에 '김애란'을 입력할 경우, 제목, 작가, 내용 중에서 하나라도 김애란이 조회되면 해당도서 반환
		   String[] filters = {"title","author"};
		   session.selectList(NAMESPACE+"test05",Map.of("filters",filters,"keyword","김애란"));
		   
	   }
	   
	   @Test
	   public void dynamicForeachWithList() {
		   //사용자가 선택한 도서명 중에서 DB에 존재하는 도서를 모두 반환
		   session.selectList(NAMESPACE+"dynamicForeachWithList",List.of("비행운","남한산성","오징어게임"));
	   }
	   
	   @Test
	   public void insertTemplate() {
		   //사용자로부터 데이터를 입력할
		   //테이블명, 컬럼명, 값을 전달받아 해당 테이블에 사용자가 원하는 데이터를 입력하는 쿼리
		   session.insert(NAMESPACE+"insertTemplate"
				   ,Map.of("tableName","member",
						   "data",Map.of("user_id","dynamic","password","1234"
						   ,"tell","010-0000-9999","email","dynamic@pclass.com")
						   )
						   );
						   
	   }
	   
	   @Test
	   public void insertTemplateBook() {
		   //사용자로부터 데이터를 입력할
		   //테이블명, 컬럼명, 값을 전달받아 해당 테이블에 사용자가 원하는 데이터를 입력하는 쿼리
		   session.insert(NAMESPACE+"insertTemplateBook"
				   ,Map.of("tableName","book"
						   ,"sec",Map.of("colName","bk_idx","val","sc_bk_idx.nextval")
						   ,"data",Map.of("title","서블릿과 스프링의 차이","author","최범균")
						   )
						   );
						   
	   }
	   
	   @Test
	   public void dynamicSet() {
		   Member member = new Member();
		   member.setUserId("DEV");
		   member.setEmail("AAA@AAA.com");
			
		   session.update(NAMESPACE+"dynamicSet",member);
	   }
}
