package com.kh.spring.admin.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.spring.member.model.dto.Member;

@Mapper
public interface AdminMemberRepository {

	@Select("select * from member")
	List<Member> selectAllMembers();
	
}
