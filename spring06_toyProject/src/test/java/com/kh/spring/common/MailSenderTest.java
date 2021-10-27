package com.kh.spring.common;

import java.util.Date;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MailSenderTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	JavaMailSenderImpl mailSender;
	@Autowired
	RestTemplate http;
	@Autowired
	ObjectMapper mapper;
	
	@Test
	public void sendEmail() throws Exception{
		MimeMessage msg = mailSender.createMimeMessage();
		msg.setFrom("azimemory@gmail.com");
        msg.setRecipients(Message.RecipientType.TO, "azimemory@gmail.com");
        msg.setSubject("메일테스트");
        msg.setSentDate(new Date());
        msg.setText("<h1>Email Test</h1>","UTF-8","html");
		mailSender.send(msg);
	}
	
	@Test
	public void restTemplateGetTest() throws JsonMappingException, JsonProcessingException, RestClientException {
		
		RequestEntity<Void> request = RequestEntity
				.get("https://dapi.kakao.com/v3/search/book?query=java")
				.header("Authorization", "KakaoAK ba07be4cf7269560d62902dcfefc6894")
				.build();
		
		JsonNode root = mapper.readTree( http.exchange(request, String.class).getBody());
		
		for (JsonNode jsonNode : root.findValues("url")) {
			logger.debug(jsonNode.asText());
		}
	}
	
	@Test
	public void restTemplatePostTest() throws Exception{
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("source", "en");
		body.add("target", "ko");
		body.add("text", "Method that will try to convert value of this node to a Java int. Numbers are coerced using default Java rules; booleans convert to 0 (false) and 1 (true), and Strings are parsed using default Java language integer parsing rules.");
		
		RequestEntity<MultiValueMap<String, String>> request = 
				RequestEntity.post("https://openapi.naver.com/v1/papago/n2mt")
				.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.header("X-Naver-Client-Id", "O_PVY28f_ilEO4rr4QS6")
				.header("X-Naver-Client-Secret", "jtxlGACDlg")
				.body(body);
		
		JsonNode root = mapper.readTree( http.exchange(request, String.class).getBody());
		logger.debug(root.findValue("translatedText").asText());
	}
	
	
	
	
	
	
	
	
	
	
	

}
