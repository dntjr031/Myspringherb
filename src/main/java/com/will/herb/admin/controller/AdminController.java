package com.will.herb.admin.controller;

import java.util.List;

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

import com.will.herb.admin.model.AuthorityService;
import com.will.herb.admin.model.AuthorityVO;
import com.will.herb.category.model.CategoryService;
import com.will.herb.common.FileUploadUtil;
import com.will.herb.manager.model.ManagerService;
import com.will.herb.manager.model.ManagerVO;
import com.will.herb.member.model.MemberService;
import com.will.herb.product.model.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AuthorityService authService;

	@Autowired
	ManagerService mgrService;

	@Autowired
	FileUploadUtil util;
	
	@Autowired
	ProductService prodService;
	
	@Autowired
	CategoryService catService;

	@RequestMapping("/adminMain.do")
	public void adminMain() {
		logger.info("관리자 메인페이지 보여주기");
	}

	@RequestMapping(value = "/member/join.do", method = RequestMethod.GET)
	public void join(Model model) {
		logger.info("관리자 등록화면 보여주기");

		List<AuthorityVO> list = authService.selectAuthority();
		logger.info("권한 조회 결과, list.size={}", list.size());

		model.addAttribute("authList", list);
	}

	@RequestMapping(value = "/member/checkUserid.do")
	public String chkUserid(@RequestParam String userid, Model model) {
		logger.info("관리자 아이디 중복확인, 파라미터 userid={}", userid);

		if (userid != null && !userid.isEmpty()) {
			int result = mgrService.selectDup(userid);
			logger.info("아이디 중복확인 결과 result={}", result);

			model.addAttribute("result", result);
		}
		model.addAttribute("EXIST_ID", MemberService.EXIST_ID);
		model.addAttribute("NON_EXIST_ID", MemberService.NON_EXIST_ID);

		return "admin/member/checkUserid";
	}

	@RequestMapping(value = "/member/join.do", method = RequestMethod.POST)
	public String joinManager(@ModelAttribute ManagerVO vo, Model model) {
		logger.info("관리자 등록 처리페이지, 파라미터 vo={}", vo);

		int cnt = mgrService.insertMgr(vo);
		logger.info("관리자 등록 결과 cnt={}", cnt);

		String msg = "관리자 등록 실패!", url = "/admin/member/join.do";
		if (cnt > 0) {
			msg = "관리자 등록 성공!";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}

	@RequestMapping(value = "/login/adminLogin.do", method = RequestMethod.GET)
	public void login() {
		logger.info("관리자 로그인 페이지 보여주기");
	}

	@RequestMapping(value = "/login/adminLogin.do", method = RequestMethod.POST)
	public String login(@RequestParam String userid, @RequestParam String pwd,
			@RequestParam(required = false) String saveId, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		logger.info("로그인 처리, 파라미터 userid={},pwd={}", userid, pwd);
		logger.info("saveId={}", saveId);

		// adminUserid, adminUserName
		// ck_admin_userid

		int result = mgrService.selectPwd(userid, pwd);
		logger.info("로그인 처리 결과 result={}", result);

		String msg = "로그인 처리 실패!", url = "/admin/login/adminLogin.do";
		if (result == MemberService.ID_NONE) {
			msg = "일치하는 아이디가 없습니다.";
		} else if (result == MemberService.PWD_DISAGREE) {
			msg = "비밀번호가 일치하지 않습니다.";
		} else if (result == MemberService.LOGIN_OK) {
			ManagerVO vo = mgrService.selectMgr(userid);
			logger.info("관리자 조회 결과 vo={}", vo);

			request.getSession().setAttribute("adminUserid", userid);
			request.getSession().setAttribute("adminUserName", vo.getName());
			request.getSession().setMaxInactiveInterval(60*60);
			logger.info("세션 저장 완료!");

			if (saveId != null && !saveId.isEmpty()) {
				Cookie ck = new Cookie("ck_admin_userid", userid);
				ck.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(ck);
				logger.info("쿠키 저장 완료!");
			} else {
				Cookie[] ckArr = request.getCookies();
				for (Cookie cookie : ckArr) {
					if (cookie.getName().equals("ck_admin_userid")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
						logger.info("쿠키 삭제 완료!");
					}
					logger.info("쿠키 없음!");
				}
			}

			msg = userid + "님 환영합니다.";
			url = "/admin/adminMain.do";

		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}

	@RequestMapping("/login/logout.do")
	public String logout(HttpSession session) {
		logger.info("관리자 로그아웃 처리");

		session.removeAttribute("adminUserid");
		session.removeAttribute("adminUserName");
		logger.info("관리자 세션 초기화 완료!");

		return "redirect:/admin/login/adminLogin.do";
	}

}
