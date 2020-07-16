package com.will.herb.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.herb.cart.model.CartService;
import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;

@Controller
@RequestMapping("/shop/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	CartService cartServ;
	@Autowired
	MemberService memServ;
	
	@RequestMapping("/orderSheet.do")
	public void orderSheet(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("userid");
		logger.info("주문하기 페이지, 파라미터 userid={}",userid);
		
		MemberVO memVo = memServ.selectMember(userid);
		List<Map<String, Object>> list = cartServ.selectCartView(userid);
		
		model.addAttribute("memVo", memVo);
		model.addAttribute("list", list);
	}
}
