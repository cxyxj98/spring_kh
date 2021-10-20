package com.kh.spring.member.model.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

	String selectPasswordByUserId(String string);

}
