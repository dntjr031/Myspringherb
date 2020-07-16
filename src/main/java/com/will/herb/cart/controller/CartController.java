package com.will.herb.cart.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.cart.model.CartService;
import com.will.herb.cart.model.CartVO;

@Controller
@RequestMapping("/shop/cart")
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cartAdd.do")
	public String cartEdit(@RequestParam String mode, @ModelAttribute CartVO cartVo,
			HttpSession session) {
		logger.info("장바구니 담기, 파라미터 mode={}, cartVo={}",mode,cartVo);
	
		cartVo.setCustomerId((String)session.getAttribute("userid"));
		
		int cnt = cartService.addCart(cartVo);
		logger.info("장바구니 담기 결과, cnt={}",cnt);
		
		String page = "";
		if(mode.equals("cart")) {
			page = "/shop/cart/cartList.do";
		}else {
			page = "/shop/order/orderSheet.do";
			
		}
		
		return "redirect:" + page;
	}
	
	@RequestMapping("/cartList.do")
	public void cartList(HttpSession session, Model model) {
		logger.info("장바구니 목록 페이지");
		String userid = (String) session.getAttribute("userid");
		
		List<Map<String, Object>> list = cartService.selectCartView(userid);
		logger.info("userid={} 에 대한 장바구니 목록 조회 결과, list.size={}",userid,list.size());
		
		model.addAttribute("list", list);
	}
	
	@RequestMapping("/cartDelete.do")
	public String cartDelete(@RequestParam int cartNo) {
		logger.info("장바구니 삭제 처리, 파라미터 cartNo={}",cartNo);
		
		int cnt = cartService.deleteCart(cartNo);
		logger.info("장바구니 삭제 결과 cnt={}",cnt);
		
		return "redirect:/shop/cart/cartList.do";
	}
	
	@RequestMapping("/cartEdit.do")
	public String cartEdit(@ModelAttribute CartVO vo) {
		logger.info("장바구니 수량 변경 처리, 파라미터 vo={}",vo);
		
		int cnt = cartService.updateCart(vo);
		logger.info("변경 결과 cnt={}",cnt);
		
		return "redirect:/shop/cart/cartList.do";
	}
}
