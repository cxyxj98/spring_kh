package com.kh.spring.member;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MemberControllerTest {
   
   //MockMvc : http 요청 및 응답 상황 테스트를 위한 객체
   
   @Autowired
   WebApplicationContext wac;
   MockMvc mockMvc;
   
   @Before
   public void setup() {
      this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
   }
   
   @Test
   public void searchPassword() throws Exception {
      mockMvc
      .perform(get("/search-pw"))
      .andExpect(status().isOk())
      .andDo(print());
   }
   
   
   
   
   
   

}