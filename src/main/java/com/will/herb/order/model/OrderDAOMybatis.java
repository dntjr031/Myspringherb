package com.will.herb.order.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.will.herb.common.DateSearchVO;

@Repository
public class OrderDAOMybatis implements OrderDAO{
	private final static String namespace = "config.mybatis.mapper.oracle.order.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertOrders(OrderVO orderVO) {
		return sqlSession.insert(namespace+"insertOrders",orderVO);
	}

	@Override
	public int insertOrderDetails(OrderVO orderVO) {
		return sqlSession.update(namespace+"insertOrderDetails",orderVO);
	}

	@Override
	public List<Map<String, Object>> selectDetailView(int orderNo) {
		return sqlSession.selectList(namespace+"selectDetailView",orderNo);
	}

	@Override
	public Map<String, Object> selectOrderView(int orderNo) {
		return sqlSession.selectOne(namespace+"selectOrderView", orderNo);
	}

	@Override
	public List<OrderAllVO> selectOrderList(DateSearchVO dateSearchVO) {
		return sqlSession.selectList(namespace+"selectOrderList", dateSearchVO);
	}

	@Override
	public int selectTotalRecord(DateSearchVO dateSearchVO) {
		return sqlSession.selectOne(namespace+"selectTotalRecord", dateSearchVO);
	}

	@Override
	public List<Map<String, Object>> selectBestPd(int productNo) {
		return sqlSession.selectList(namespace+"selectBestPd", productNo);
	}
}
