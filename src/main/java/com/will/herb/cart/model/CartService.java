package com.will.herb.cart.model;

import java.util.List;
import java.util.Map;

public interface CartService {
	int addCart(CartVO vo);
	List<Map<String, Object>> selectCartView(String customerId);
	int deleteCart(int cartNo);
	int updateCart(CartVO vo);
	int deleteCartByUserid(String userid);
}
