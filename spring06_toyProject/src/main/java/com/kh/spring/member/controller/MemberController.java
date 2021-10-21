package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.CookieGenerator;

import com.kh.spring.common.code.ErrorCode;
import com.kh.spring.common.exception.HandlableException;
import com.kh.spring.common.validator.ValidateResult;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.validator.JoinForm;
import com.kh.spring.member.validator.JoinFormValidator;

//1. @Controller : 해당 클래스를 applicationContext에 bean으로 등록
//				  Controller와 관련된 annotation을 사용할 수 있게 해준다.

//2. @RequestMapping : 요청 URL과 Controller의 메서드 매핑을 지원
//				클래스 위에 선언할 경우, 해당 클래스의 모든 매서드가 지정된 경로를 상위경로로 가진다.
//3. @GetMapping : Get 방식의 요청 URL과 Controller의 매서드 매핑을 지원
//4. @PostMapping : Post 방식의 요청 URL과 Controller의 매서드 매핑을 지원
//5. @RequestParam : 요청 파라미터를 컨트롤러 메서드의 매개변수에 바인드
//				단 Content-type이 application/x-www-form-urlEncoded인 경우에만 가능
//				FormHttpMessageConverter가 동작
//6. @ModelAttribute : 요청 파라미터를 setter 사용해 메서드 매개변수에 선언된 객체에 바인드,
//				Model객체의 attribute로 자동으로 저장
//7. @RequestBody : 요청 body를 읽어서 자바의 객체에 바인드
//         		application/x-www-form-urlEncoded를 지원하지 않는다.
//8. @RequestHeader : 요청 헤더를 메서드의 매개변수에 바인드
//9. @SessionAttribute : 원하는 session의 속성값을 매개변수에 바인드
//10. @CookieValue : 원하는 cookie값을 매개변수에 바인드
//11. @PathVariable : url 템플릿에 담긴 파라미터값을 매개변수에 바인드
//12. @ResponseBody : 메서드가 반환하는 값을 응답 body에 작성
//13. Servlet객체를 컨트롤러의 매개변수에 선언해 주입받을 수 있다.
// HttpServletRequest, HttpServletResponse, HttpSession

@Controller
@RequestMapping("member")
public class MemberController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MemberService memberService;
	private JoinFormValidator joinFormValidator;
	
	public MemberController(MemberService memberService, JoinFormValidator joinFormValidator) {
		super();
		this.memberService = memberService;
		this.joinFormValidator = joinFormValidator;
	}

	/* 
	 *  com.myapp.Product becomes "product"
		com.myapp.MyProduct becomes "myProduct"
		com.myapp.UKProduct becomes "UKProduct"

	 */
	@InitBinder(value="joinForm")//model의 속성 중 속성명이 joinForm인 속성이 있는 경우 initBinder 메서드를 실행
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(joinFormValidator);//데이터바인드에다가 validator를 추가
	}
	
	@GetMapping("join")
	public void joinForm(Model model) {
		model.addAttribute(new JoinForm()).addAttribute("error",new ValidateResult().getError()); //빈객체를 넘겨준다
	}
	
	
	
	/*
	 * @RequestParam(name="userId") String userId
			,@RequestParam String password
			,@RequestParam String tell
			,@RequestParam String email
			RequestParam 생략가능
			그리고 다 지우고 ModelAttribute를 받으면 객체 맵핑까지 다 알아서 해준다.
			
			@ModelAttribute 생략가능하다.
	 * */
	@PostMapping("join")
	public String join(@Validated JoinForm form,
			Errors errors //반드시 검증될 객체 바로 뒤에 작성
			, Model model
			){	
		
		ValidateResult vr = new ValidateResult();
		model.addAttribute(vr.getError());
		if(errors.hasErrors()) {
			vr.addErrors(errors);
			return "member/join";
		}
		
		memberService.insertMember(form);
		return "index";
	}
	
	@PostMapping("join-json")
	public String joinWithJson(@RequestBody Member member) {
		logger.debug(member.toString());
		return "index";
	}
	
	//로그인 페이지 이동 메서드
	@GetMapping("login")
	public void login() {
	}
	//로그인 실행 메서드
	//메서드명 :loginImpl
	//재지정할 jsp : mypage
	@PostMapping("login")
	public String loginImpl(Member member,HttpSession session,RedirectAttributes redirectAttr) {
		Member certifiedUser = memberService.authenticateUser(member);
		if(certifiedUser == null) {
			redirectAttr.addFlashAttribute("message","아이디나 비밀번호가 정확하지 않습니다.");
			return "redirect:/member/login";
		}
		session.setAttribute("authentication", certifiedUser);
		logger.debug(certifiedUser.toString());
		return "redirect:/member/mypage";
	}
	
	@GetMapping("mypage")
	public String mypage(@CookieValue(name="JSESSIONID") String sessionId
						, @SessionAttribute(name="authentication") Member member
						,HttpServletResponse response) {
		//Cookie 생성 및 응답헤더에 추가
		CookieGenerator cookieGenerator = new CookieGenerator();
		cookieGenerator.setCookieName("testCookie");
		cookieGenerator.addCookie(response, "test_cookie");
		
		
		logger.debug("@CookieValue :"+sessionId);
		logger.debug(" @SessionAttribute :"+member);
		
		return "member/mypage";
	}
	
	@GetMapping("id-check")
	@ResponseBody
	public String idCheck(String userId) {
		Member member = memberService.selectMemberByUserId(userId);
		
		if(member == null) {
			return"available";
		}else {
			return"disable";
		}
		
	}
}
