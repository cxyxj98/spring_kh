package com.kh.spring.common.mail.handler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.spring.common.mail.MailTemplate;

@Controller
public class MailHandler {
	
	@PostMapping("mail")
	public String writeMailTemplate(MailTemplate template) {
		return "mail-template/"+template.getTemplateName();
		
	}
	
}
