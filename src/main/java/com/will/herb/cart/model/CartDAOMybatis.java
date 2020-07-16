package com.will.herb.cart.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAOMybatis implements CartDAO{
	private static final String tablespace = "config.mybatis.mapper.oracle.cart.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int selectPdCount(CartVO vo) {
		return sqlSession.selectOne(tablespace+"selectPdCount", vo);
	}

	@Override
	public int insertCart(CartVO vo) {
		return sqlSession.insert(tablespace+"insertCart",vo);
	}

	@Override
	public int updatePdQty(CartVO vo) {
		return sqlSession.update(tablespace+"updatePdQty",vo);
	}

	@Override
	public List<Map<String, Object>> selectCartView(String customerId) {
		return sqlSession.selectList(tablespace+"selectCartView", customerId);
	}

	@Override
	public int deleteCart(int cartNo) {
		return sqlSession.delete(tablespace+"deleteCart", cartNo);
	}

	@Override
	public int updateCart(CartVO vo) {
		return sqlSession.update(tablespace+"updateCart",vo);
	}
	
	
}
