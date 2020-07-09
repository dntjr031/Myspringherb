package com.will.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public void login() {
		logger.info("로그인 화면 보여주기");
		
	}
	
	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public String login_ok(@RequestParam String userid, @RequestParam String pwd, 
			@RequestParam(required = false) String saveId, HttpServletResponse response, 
			HttpServletRequest request, Model model) {
		
		logger.info("로그인 처리, 파라미터 userid={}, pwd={}",userid,pwd);
		logger.info("saveId={}",saveId);
		
		
		//로그인 처리
		
		int result = service.loginCheck(userid, pwd);
		logger.info("로그인 처리 결과, result={}",result);
		
		String msg = "로그인 처리 실패!", url = "/login/login.do";
		if(result == MemberService.LOGIN_OK) {
			
			//쿠키처리
			if("on".equals(saveId)) {
				Cookie ck1 = new Cookie("userid", userid);
				
				ck1.setMaxAge(60*60*24*7);
				
				response.addCookie(ck1);
				
			}else {
				Cookie[] cookies = request.getCookies();           

				for(int i = 0 ; i<cookies.length; i++){                     
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);       

				}

			}
			
			// 세션처리
			MemberVO vo = service.selectMember(userid);
			request.getSession().setAttribute("userid", userid);
			request.getSession().setAttribute("name", vo.getName());
			
			msg = userid + "님 로그인 되었습니다.";
			url = "/index.do";
		}else if(result == MemberService.PWD_DISAGREE) {
			msg = "비밀번호가 일치하지 않습니다.";
		}else if(result == MemberService.ID_NONE) {
			msg = "해당 아이디는 존재하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
}
