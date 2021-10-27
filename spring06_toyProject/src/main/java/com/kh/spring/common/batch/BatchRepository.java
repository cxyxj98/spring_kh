package com.kh.spring.common.batch;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BatchRepository {
	
	@Insert("insert into baseball_rank(rank,name,game,win,lose,tie)"
			+ " values(#{rank},#{name},#{game},#{win},#{lose},#{tie})")
	void insertBaseballRank(Map<String,String> commandMap);
}
