package com.will.herb.product.model;

import java.util.List;

public interface ProductService {
	List<ProductVO> selectEvent(String eventName);
	List<ProductVO> selectPdByCategory(int categoryNo);
	ProductVO selectProduct(int productNo);
	public int insertProduct(ProductVO vo);
	List<ProductVO> selectProductAll(EventProductVO vo);
	public int selectTotalRecord(EventProductVO searchVo);
	public int deleteMulti(List<ProductVO> pdList);
	int EventMulti(List<EventProductVO> list);
}
