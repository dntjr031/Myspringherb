package com.will.herb.product.model;

import java.util.List;

public interface ProductDAO {
	List<ProductVO> selectEvent(String eventName);
	List<ProductVO> selectPdByCategory(int categoryNo);
	ProductVO selectProduct(int productNo);
	public int insertProduct(ProductVO vo);
	List<ProductVO> selectProductAll(EventProductVO vo);
	public int selectTotalRecord(EventProductVO searchVo);
	public int deleteProduct(int productNo);
	int insertEventPd(EventProductVO vo);
	int selectEventByPdNo(EventProductVO vo);
}
