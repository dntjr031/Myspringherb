package com.will.herb.zipcode.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.will.herb.zipcode.model.ZipcodeService;

@Controller
public class ZipcodeController {

	private static final Logger logger = LoggerFactory.getLogger(ZipcodeController.class);

	@Autowired
	ZipcodeService service;

	@RequestMapping("/zipcode/zipcode.do")
	public void zipcode() {

		/*
		 * List<ZipcodeVO> list = null;
		 * 
		 * if(dong != null && !dong.isEmpty()) { list = service.searchByDong(dong); }
		 * 
		 * 
		 * model.addAttribute("list", list);
		 */
	}

	@RequestMapping("/zipcode/ajaxZipcode.do")
	public void ajaxZipcode(HttpServletRequest req, ModelMap model, HttpServletResponse response) throws Exception {
		logger.info("ajax - 우편번호 찾기");

		// 요청변수 설정
		String currentPage = req.getParameter("currentPage"); // 요청 변수 설정 (현재 페이지. currentPage : n > 0)
		String countPerPage = req.getParameter("countPerPage"); // 요청 변수 설정 (페이지당 출력 개수. countPerPage 범위 : 0 < n <= 100)
		String confmKey = req.getParameter("confmKey"); // 요청 변수 설정 (승인키)
		String keyword = req.getParameter("dong"); // 요청 변수 설정 (키워드)
		// OPEN API 호출 URL 정보 설정
		String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=" + currentPage + "&countPerPage="
				+ countPerPage + "&keyword=" + URLEncoder.encode(keyword, "UTF-8") + "&confmKey=" + confmKey;
		URL url = new URL(apiUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer();
		String tempStr = null;

		while (true) {
			tempStr = br.readLine();
			if (tempStr == null)
				break;
			sb.append(tempStr); // 응답결과 XML 저장
		}
		br.close();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		response.getWriter().write(sb.toString()); // 응답결과 반환
	}
}
