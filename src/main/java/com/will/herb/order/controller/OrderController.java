package com.will.herb.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.cart.model.CartService;
import com.will.herb.common.DateSearchVO;
import com.will.herb.common.PaginationInfo;
import com.will.herb.common.Utility;
import com.will.herb.member.model.MemberService;
import com.will.herb.member.model.MemberVO;
import com.will.herb.order.model.OrderAllVO;
import com.will.herb.order.model.OrderService;
import com.will.herb.order.model.OrderVO;

@Controller
@RequestMapping("/shop/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	CartService cartServ;
	@Autowired
	MemberService memServ;
	@Autowired
	OrderService orderServ;
	
	@RequestMapping(value = "/orderSheet.do", method = RequestMethod.GET)
	public void orderSheet(HttpSession session, Model model) {
		String userid = (String) session.getAttribute("userid");
		logger.info("주문하기 페이지, 파라미터 userid={}",userid);
		
		MemberVO memVo = memServ.selectMember(userid);
		List<Map<String, Object>> list = cartServ.selectCartView(userid);
		
		model.addAttribute("memVo", memVo);
		model.addAttribute("list", list);
	}
	

	@RequestMapping(value = "/orderSheet.do", method = RequestMethod.POST)
	public String orderSheetOk(HttpSession session, @ModelAttribute OrderVO orderVO,
			Model model) {
		String userid = (String) session.getAttribute("userid");
		logger.info("주문 하기, 매개변수 userid={}, orderVo={}",userid,orderVO);
		
		orderVO.setCustomerId(userid);
		
		int cnt = orderServ.insertOrder(orderVO);
		logger.info("주문하기 결과, cnt={}",cnt);
		
		return "redirect:/shop/order/orderComplete.do?orderNo="+orderVO.getOrderNo();
	}
	
	@RequestMapping("/orderComplete.do")
	public void orderComplete(@RequestParam int orderNo, Model model) {
		logger.info("주문완료 페이지, 파라미터 orderNo={}",orderNo);
		
		Map<String, Object> order = orderServ.selectOrderView(orderNo);
		logger.info("주문정보 조회 order={}",order);
		List<Map<String, Object>> list = orderServ.selectDetailView(orderNo);
		logger.info("주문상세정보 조회 list.size={}",list.size());
		
		model.addAttribute("order", order);
		model.addAttribute("list", list);
	}
	
	@RequestMapping("/orderList.do")
	public void orderList(@ModelAttribute DateSearchVO dateSearchVO, HttpSession session,
			Model model) {
		String userid = (String) session.getAttribute("userid");
		dateSearchVO.setCustomerId(userid);
		logger.info("주문내역 페이지, 파라미터 dataSearchVo={}", dateSearchVO);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCKSIZE);
		pagingInfo.setCurrentPage(dateSearchVO.getCurrentPage());
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		
		//
		dateSearchVO.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		dateSearchVO.setRecordCountPerPage(Utility.RECORD_COUNT);
		
		//현재일자에 주문이 있는 경우 조회해온다
		//=> 현재일자를 startDay, endDay,에 setting
		if(dateSearchVO.getStartDay() == null || dateSearchVO.getStartDay().isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startDay = sdf.format(new Date());
			dateSearchVO.setStartDay(startDay);
			dateSearchVO.setEndDay(startDay);
		}
		
		List<OrderAllVO> list = orderServ.selectOrderList(dateSearchVO);
		logger.info("주문내역 결과, list.size={}, dateSearchVo={}",list.size(),dateSearchVO);
		pagingInfo.setTotalRecord(orderServ.selectTotalRecord(dateSearchVO));
		logger.info("totalRecord={}",pagingInfo.getTotalRecord());
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
	}
	
	@RequestMapping("/best5.do")
	public String best5(@RequestParam(defaultValue = "0") int productNo, Model model) {
		logger.info("카테고리별 판매가 많은 상품, 파라미터 produrctNo={}",productNo);
		
		List<Map<String, Object>> list = orderServ.selectBestPd(productNo);
		logger.info("카테고리별 판매가 많은 상품조회 결과, list.size={}",list.size());
		
		model.addAttribute("list", list);
		
		return "inc/best5";
	}
	
}
