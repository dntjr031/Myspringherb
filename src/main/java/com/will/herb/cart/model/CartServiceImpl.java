package com.will.herb.cart.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO dao;
	
	@Override
	public int addCart(CartVO vo) {
		int count = dao.selectPdCount(vo);
		
		int cnt = 0;
		if(count >0) {
			cnt = dao.updatePdQty(vo);
		}else {
			cnt = dao.insertCart(vo);
		}
		
		return cnt;
	}

	@Override
	public List<Map<String, Object>> selectCartView(String customerId) {
		return dao.selectCartView(customerId);
	}

	@Override
	public int deleteCart(int cartNo) {
		return dao.deleteCart(cartNo);
	}

	@Override
	public int updateCart(CartVO vo) {
		return dao.updateCart(vo);
	}

	@Override
	public int deleteCartByUserid(String userid) {
		return dao.deleteCartByUserid(userid);
	}
	
}
