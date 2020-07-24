package com.will.herb.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ajaxTest")
public class AjaxTestController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxTestController.class);
	
	@RequestMapping("/ajaxTest1.do")
	public void ajaxTest1() {
		logger.info("ajaxTest1 페이지 보여주기");
	}
	
	@RequestMapping("/search.do")
	@ResponseBody
	public String search(@RequestParam String keyword, @RequestParam String id) {
		logger.info("ajax - search.do 실행");
		
		String result = id + ", " + keyword + ",sk,sbs,sm";
		
		return result;
		
	}
	
	@RequestMapping("/ajaxTest2.do")
	public void ajaxTest2() {
		logger.info("ajaxTest2 페이지 보여주기");
	}
	
	@RequestMapping("/ajaxList.do")
	@ResponseBody
	public List<MemoVO> ajaxList() {
		logger.info("ajaxList.do 실행!");
		
		MemoVO vo1 = new MemoVO(1,"홍길동","내용1");
		MemoVO vo2 = new MemoVO(2,"김길동","내용2");
		MemoVO vo3 = new MemoVO(3,"이길동","내용3");
		
		List<MemoVO> list = new ArrayList<MemoVO>();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		
		return list;
		
		//[{"no":1,"name":"홍길동","content":"내용1"},{"no":2,"name":"김길동","content":"내용2"},{"no":3,"name":"이길동","content":"내용3"}]
	}
	
	@RequestMapping("/ajaxDetail.do")
	@ResponseBody
	public MemoVO ajaxDetail(@RequestParam(defaultValue = "0") int no) {
		logger.info("ajaxDetail.do 실행!, 파라미터 no={}",no);
		
		MemoVO vo = new MemoVO(no,"김동율","재미없어요");
		
		return vo;
		//{"no":5,"name":"김동율","content":"재미없어요"}
	}
	
	@RequestMapping("/ajaxTest3.do")
	public void ajaxTest3() {
		logger.info("ajaxTest3 페이지 보여주기");
	}
	
	@RequestMapping("/ajaxView.do")
	@ResponseBody
	public MemoVO ajaxView(@RequestParam(defaultValue = "0") int no) {
		logger.info("ajaxView.do 실행, 매개변수 no={}",no);
		
		MemoVO vo = new MemoVO(no,"김동율","재미없어요");
		if(no == 0) {
			vo.setContent("");
			vo.setName("");
		}
		return vo;
		//{"no":102,"name":"김동율","content":"재미없어요"}
	}
	
	@RequestMapping("/ajaxWrite.do")
	@ResponseBody
	public ResultVO ajaxWrite(@ModelAttribute MemoVO memoVo) {
		logger.info("메모 입력 처리 페이지, 파라미터 memoVo={}",memoVo);
		
		memoVo.setNo(100);
		
		ResultVO vo = new ResultVO();
		vo.setData(memoVo);
		vo.setMessage("데이터 등록 성공");
		
		return vo;
		
	}
	
	@RequestMapping("/ajaxAll.do")
	@ResponseBody
	public List<MemoVO> ajaxAll() {
		logger.info("전체 조회 처리");
		
		List<MemoVO> list = new ArrayList<MemoVO>();
		
		for (int i = 1; i <= 5; i++) {
			MemoVO vo = new MemoVO(i, "이름"+i, "내용"+i);
			
			list.add(vo);
		}
		logger.info("전체 조회 결과 list.size={}",list.size());
		
		return list;
		//[{"no":1,"name":"이름1","content":"내용1"},{"no":2,"name":"이름2","content":"내용2"},{"no":3,"name":"이름3","content":"내용3"},{"no":4,"name":"이름4","content":"내용4"},{"no":5,"name":"이름5","content":"내용5"}]
	}
	
	@RequestMapping("/ajaxTest4.do")
	public void ajaxTest4() {
		logger.info("ajaxTest4 페이지 보여주기");
	}
}
