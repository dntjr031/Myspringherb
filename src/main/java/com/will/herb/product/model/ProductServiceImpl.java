package com.will.herb.product.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired private ProductDAO productDao;

	@Override
	public List<ProductVO> selectEvent(String eventName) {
		return productDao.selectEvent(eventName);
	}

	@Override
	public List<ProductVO> selectPdByCategory(int categoryNo) {
		return productDao.selectPdByCategory(categoryNo);
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		return productDao.selectProduct(productNo);
	}
	
	
}
