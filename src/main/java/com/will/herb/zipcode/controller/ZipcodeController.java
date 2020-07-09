package com.will.herb.zipcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.zipcode.model.ZipcodeService;
import com.will.herb.zipcode.model.ZipcodeVO;

@Controller
public class ZipcodeController {
	
	@Autowired
	ZipcodeService service;
	
	@RequestMapping("/zipcode/zipcode.do")
	public String zipcode(@RequestParam(required = false) String dong, Model model) {
		
		List<ZipcodeVO> list = null;
		
		if(dong != null && !dong.isEmpty()) {
			list = service.searchByDong(dong);
		}
		
		model.addAttribute("list", list);
		
		return "zipcode/zipcode";
	}
}
