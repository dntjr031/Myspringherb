package com.will.herb.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.will.herb.category.model.CategoryService;
import com.will.herb.category.model.CategoryVO;
import com.will.herb.common.FileUploadUtil;
import com.will.herb.common.PaginationInfo;
import com.will.herb.common.Utility;
import com.will.herb.product.model.EventProductVO;
import com.will.herb.product.model.ProductService;
import com.will.herb.product.model.ProductVO;
import com.will.herb.product.model.ProductVOList;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	private static final Logger logger
		=LoggerFactory.getLogger(AdminProductController.class);

	@Autowired private CategoryService categoryService;
	@Autowired private FileUploadUtil fileUploadUtil;
	@Autowired private ProductService productService;
	
	@RequestMapping(value="/productWrite.do", method = RequestMethod.GET)
	public void write_get(Model model) {
		logger.info("상품 등록 화면 보여주기");

		List<CategoryVO> list=categoryService.selectCategory();
		logger.info("카테고리 조회 결과, list.size={}", list.size());

		model.addAttribute("catList", list);		
	}
	
	@RequestMapping(value="/productWrite.do", method=RequestMethod.POST)
	public String write_post(@ModelAttribute ProductVO vo,
			HttpServletRequest request, Model model) {
		logger.info("상품 등록 파라미터, vo={}", vo);
		
		//파일 업로드 처리
		List<Map<String, Object>> fileList
		=fileUploadUtil.fileUpload(request, FileUploadUtil.PATH_PD_IMAGE);
		
		String imageURL="";
		for(Map<String, Object> map : fileList) {
			imageURL=(String) map.get("fileName");
		}
		vo.setImageURL(imageURL);
		
		//db작업
		int cnt = productService.insertProduct(vo);
		String msg="상품 등록 실패", url="/admin/product/productWrite.do";
		if(cnt>0) {
			msg="상품 등록되었습니다.";
			url="/admin/product/productList.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/productList.do")
	public String pdList(@ModelAttribute EventProductVO searchVo, Model model) {
		logger.info("상품 목록, 파라미터 searchVo={}", searchVo);
		
		//paging처리 관련
		// PaginationInfo 값 넣기
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCKSIZE);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		
		// searchVo 값 넣기
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		
		//2. db작업
		List<ProductVO> list = productService.selectProductAll(searchVo);
		logger.info("상품 목록 조회 결과 list.size={}",list.size());
		int totalRecord = productService.selectTotalRecord(searchVo);
		logger.info("상품 목록 레코드 개수 totalRecord={}",totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		
		return "admin/product/productList";
				
	}
	
	@RequestMapping("deleteMulti.do")
	public String multiDel(@ModelAttribute ProductVOList pdListVo, HttpServletRequest request,
			Model model) {
		//1
		logger.info("선택한 상품 삭제 처리, 파라미터 pdListVo={}",pdListVo);
		
		List<ProductVO> list = pdListVo.getPdItems();
		
		//2 db
		int cnt = productService.deleteMulti(list);
		logger.info("선택한 상품 삭제 결과, cnt={}",cnt);
		
		String msg="", url = "/admin/product/productList.do";
		if(cnt > 0) {
			msg = "선택한 상품들을 삭제하였습니다.";
			
			int i = 0;
			for (ProductVO vo : list) {
				logger.info("i={}",i++);
				logger.info("productNo={}, imageURL={}",vo.getProductNo(),vo.getImageURL());
				
				//파일 삭제
				if(vo.getProductNo() != 0) { //체크한 상품만 파일 삭제
					String path = fileUploadUtil.getUploadPath(request, FileUploadUtil.PATH_PD_IMAGE);
					File file = new File(path, vo.getImageURL());
					if(file.exists()) {
						boolean bool = file.delete();
						logger.info("이미지 삭제여부 bool={}",bool);
					}
				}
			}
		}else{
			msg = "선택한 상품들 삭제시 에러 발생!";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/eventMulti.do")
	public String eventMulti(@ModelAttribute ProductVOList pdListVo, @RequestParam String eventSel,
			Model model) {
		logger.info("이벤트 등록 처리, 파라미터 eventSel={}, pdListVo={}",eventSel,pdListVo);
		
		List<ProductVO> list = pdListVo.getPdItems();
		
		List<EventProductVO> eventList = new ArrayList<EventProductVO>();
		for (ProductVO vo : list) {
			if(vo.getProductNo() != 0) {
				EventProductVO eventVo = new EventProductVO();
				eventVo.setEventName(eventSel);
				eventVo.setProductNo(vo.getProductNo());
				eventList.add(eventVo);
			}
		}
		
		int cnt = productService.EventMulti(eventList);
		logger.info("이벤트 등록 처리 결과, cnt={}",cnt);
		
		String msg = "", url = "/admin/product/productList.do";
		if(cnt >= 0) {
			msg = "선택된 상품 "+eventSel+"등록 처리 성공";
		}else {
			msg = "이벤트 등록 처리 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
}









