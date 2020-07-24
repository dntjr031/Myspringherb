package com.will.herb.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
				if(vo.getEmail2() == null || vo.getEmail2().isEmpty()) {
					vo.setEmail1("");
				}
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
	
	@RequestMapping("/ajaxDupId.do")
	@ResponseBody
	public boolean ajaxDupId(@RequestParam String userid) {
		logger.info("ajax - 아이디 중복확인, 파라미터 userid={}",userid);
		
		int check = service.dupUserid(userid);
		logger.info("ajax - 아이디 중복확인 결과, check={}",check);
		
		boolean bool = false;
		
		if (check == MemberService.NON_EXIST_ID) {
			bool = true; // 사용 가능
		}
		
		return bool;
				
	}
	
	@RequestMapping(value = "/memberEdit.do" , method = RequestMethod.GET)
	public String memberEdit(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		logger.info("회원 정보 수정 페이지, 파라미터 userid={}",userid);
		
		if(userid == null || userid.isEmpty()) {
			model.addAttribute("msg", "먼저 로그인 하세요");
			model.addAttribute("url", "/login/login.do");
			
			return "common/message";
		}
		
		MemberVO vo = service.selectMember((String)session.getAttribute("userid"));
		logger.info("회원 정보 조회 결과 vo={}",vo);
		
		String[] emailList = {"naver.com","hanmail.net","nate.com","gmail.com"};
		
		int count = 0;
		boolean isEtc = false;
		if(vo.getEmail1() != null && !vo.getEmail1().isEmpty() 
				&& vo.getEmail2() != null && !vo.getEmail2().isEmpty()) {
			
			for (String str : emailList) {
				if(str.equals(vo.getEmail2())) {
					count++;
					break;
				}
			}
			
			if(count == 0) {
				isEtc = true;
			}
			
		}
		
		model.addAttribute("memVo", vo);
		model.addAttribute("emailList", emailList);
		model.addAttribute("isEtc", isEtc);
		
		return "member/memberEdit";
	}
	
	@RequestMapping(value = "/memberEdit.do", method = RequestMethod.POST)
	public String memberEdit_post(@ModelAttribute MemberVO vo, HttpSession session, Model model) {
		
		String userid = (String)session.getAttribute("userid");
		logger.info("회원정보 수정 처리 userid={}, vo={}",userid,vo);
		
		if(userid == null || userid.isEmpty()) {
			model.addAttribute("msg", "먼저 로그인 하세요");
			model.addAttribute("url", "/login/login.do");
			
			return "common/message";
		}
		
		vo.setUserid(userid);
		
		
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
				if(vo.getEmail2() == null || vo.getEmail2().isEmpty()) {
					vo.setEmail1("");
				}
			}
		}
		
		String msg = "회원정보 수정 실패", url = "/member/memberEdit.do";
		int check = service.loginCheck(userid, vo.getPwd());
		
		if(check == MemberService.LOGIN_OK) {
			int cnt = service.updateMember(vo);
			logger.info("회원정보 수정 결과 cnt={}",cnt);
			
			if(cnt > 0) {
				msg = "회원정보 수정 완료!";
			}
			
		}else if(check == MemberService.PWD_DISAGREE) {
			msg = "비밀번호가 틀렸습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/message";
	}
	
	@RequestMapping(value = "/memberOut.do", method = RequestMethod.GET)
	public void memberOut_get(HttpSession session, Model model) {
		logger.info("회원 탈퇴 페이지");
	}
	
	@RequestMapping(value = "/memberOut.do", method = RequestMethod.POST)
	public String memberOut_post(@RequestParam String pwd, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		logger.info("회원 탈퇴 처리");
		String userid = (String)request.getSession().getAttribute("userid");
		
		int check = service.loginCheck(userid, pwd);
		
		String msg = "탈퇴 처리 실패!", url = "/member/memberOut.do";
		if(check == MemberService.LOGIN_OK) {
			
			int cnt = service.memberOut(userid);
			
			logger.info("탈퇴 처리 결과 cnt={}", cnt);
			if(cnt > 0) {
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				logger.info("쿠키 삭제 완료");
			}
			
			request.getSession().invalidate();
			logger.info("세션 초기화 완료");
			
			msg = "탈퇴되었습니다.";
			url = "/index.do";
			
		}else if(check == MemberService.PWD_DISAGREE) {
			msg = "비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
}
