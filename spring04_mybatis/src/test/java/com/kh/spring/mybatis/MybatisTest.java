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
		member.setPassword("ppppp");
		member.setUserId("DEV");
	
		session.update(NAMESPACE+"update",member);
	}
}
