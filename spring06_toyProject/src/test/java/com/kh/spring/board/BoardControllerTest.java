package com.kh.spring.board;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kh.spring.member.model.dto.Member;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class BoardControllerTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//MockMvc : http 요청 및 응답 상황 테스트를 위한 객체
	@Autowired
	WebApplicationContext wac;
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void uploadBoardTest() throws Exception {
		MockMultipartFile file1 = 
				new MockMultipartFile("files","OFN.txt",null,"firstFile".getBytes());
		MockMultipartFile file2 = 
				new MockMultipartFile("files","OFN2.txt",null,"secondFile".getBytes());
		
		Member member = new Member();
		member.setUserId("test");
		
		mockMvc.perform(multipart("/board/upload")
				.file(file1)
				.file(file2)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("title", "트랜잭션테스트")
				.param("content", "본문")
				.sessionAttr("authentication", member))
		.andExpect(status().is3xxRedirection())
		.andDo(print());
	}
	
	
	
	

}
