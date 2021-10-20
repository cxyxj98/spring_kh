package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	 private  MemberRepository memberRepository;
	
	public String selectPasswordByUserId() {
		
		return memberRepository.selectPasswordByUserId("DEV");
	}
}
