package com.kh.spring.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;

@Mapper
public interface MybatisRepository {
	
	@Select("select password from member where user_id = #{userId}")
	String selectPasswordByUserId(String userId);
	
	@Select("select * from member where user_id = #{userId}")
	Member selectMemberByUserId(String userId);
	
	@Select("select * from member inner join rent_master using(user_id) where user_id = #{userId}")
	List<Map<String,Object>> selectRentAndMemberByUserId(String userId);
	
	List<Map<String,Object>> selectRentBookByUserId(String userId);//인터페이스에 메서드 선언만하고 매퍼에 sql문 작성
	
	//List<Map<String,Object>> dynamicIf(String keyword);
	
	//List<Map<String,Object>> dynamicChoose(String keyword);
	
	
}
