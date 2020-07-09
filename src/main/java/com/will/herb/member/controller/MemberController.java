package com.will.herb.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	@RequestMapping("agreement.do")
	public String member() {
		logger.info("약관 동의 페이지");
		
		return "member/agreement";
	}
	
	@RequestMapping("/register.do")
	public void register() { // 매핑주소와 뷰페이지 이름이 같을 시 void가능
		logger.info("회원 가입 페이지 보여주기");
		//return "member/register";
	}
	
	@RequestMapping("/memberWrite.do")
	public String memberWrite(@ModelAttribute MemberVO vo, Model model) {
		logger.info("회원가입 처리, 파라미터 vo={}",vo);
		
		if(vo.getHp2() == null || vo.getHp3() == null || vo.getHp2().isEmpty() || vo.getHp3().isEmpty()) {
			vo.setHp1("");
			vo.setHp2("");
			vo.setHp3("");
		}
		
		if(vo.getEmail1() == null || vo.getEmail1().isEmpty()) {
			vo.setEmail2("");
		}else {
			if(vo.getEmail2().equals("etc")) {
				vo.setEmail2(vo.getEmail3());
			}
		}
		
		int cnt = service.insertMember(vo);
		logger.info("회원가입 처리 결과 cnt={}",cnt);
		
		String msg = "회원가입 실패!", url = "/member/register.do";
		if(cnt > 0) {
			msg = "회원가입 완료!";
			url = "/index.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/checkUserid.do")
	public void checkUserid(@RequestParam() String userid, Model model) {
		logger.info("아이디 중복확인, 파라미터 userid={}",userid);
		
		int result = 0;
		if(userid != null && !userid.isEmpty()) {
			result = service.dupUserid(userid);
		}
		logger.info("아이디 중복확인 결과, result={}",result);
		
		model.addAttribute("result", result);
		model.addAttribute("EXIST_ID", MemberService.EXIST_ID);
		model.addAttribute("NON_EXIST_ID", MemberService.NON_EXIST_ID);
	}
	
}
