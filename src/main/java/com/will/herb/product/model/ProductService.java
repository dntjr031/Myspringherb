package com.will.herb.product.model;

import java.util.List;

public interface ProductService {
	List<ProductVO> selectEvent(String eventName);
	List<ProductVO> selectPdByCategory(int categoryNo);
	ProductVO selectProduct(int productNo);
}
