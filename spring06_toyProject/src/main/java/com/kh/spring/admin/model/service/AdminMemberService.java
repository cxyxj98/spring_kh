package com.kh.spring.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.admin.model.repository.AdminMemberRepository;
import com.kh.spring.member.model.dto.Member;

@Service
public class AdminMemberService {
	
	@Autowired
	private AdminMemberRepository adminMemberRepository;
	
	public List<Member> selectAllMembers(){
		return adminMemberRepository.selectAllMembers();
	}
}
